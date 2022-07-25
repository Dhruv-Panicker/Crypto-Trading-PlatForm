package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.app.PerformTrades;
import cryptoTrader.app.Trade;
import cryptoTrader.broker.Broker;
import cryptoTrader.utils.DataVisualizationCreator;

/**
 * This the main user interface where Trading Clients will perform trades, and trade actions
 * @author Dhruv Panicker 
 * @author Gleb Zvonkov 
 * 
 *
 */
public class MainUI extends JFrame implements ActionListener {
	
	private LinkedHashSet<Trade> allTrades = new LinkedHashSet<Trade>();   //A hashset for all trades performed;
	private HashSet<Broker> allBrokers = new HashSet<Broker>(); //A hashset for all brokers;
	
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;

	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;

	private JTextArea selectedTickerList;
//	private JTextArea tickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;
	
	/**
	 * This method will check whether an instance of the MainUI already exists
	 * Ensures that only one instnace of the MainUI is created 
	 * @return instance
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	
	/**
	 * This function will implement the MainUI layout
	 */
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar
		JPanel north = new JPanel();

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);
		JPanel south = new JPanel();
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		// table.setPreferredSize(new Dimension(600, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		
		Vector<String> strategyNames = new Vector<String>();
		//strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		//Add combo box for broker, so user is only able to select out of four possible brokers
		Vector<String> brokerNames = new Vector<String>();
		brokerNames.add("Broker1");
		brokerNames.add("Broker2");
		brokerNames.add("Broker3");
		brokerNames.add("Broker4");
		TableColumn strategyColumn2 = table.getColumnModel().getColumn(0);
		JComboBox comboBox2 = new JComboBox(brokerNames);
		strategyColumn2.setCellEditor(new DefaultCellEditor(comboBox2));
		
		
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(600, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
//		east.setLayout();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
//		east.add(table);
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
//		getContentPane().add(west, BorderLayout.WEST);
	}
	
	/**
	 * This method will update all stats
	 * @param component 
	 */
	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	/**
	 * This method will perform an action when the user presses a button on the MainUI
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		//Get the command that was initiated
		String command = e.getActionCommand();
		// Keeping track of the trader client name
		String traderName = "";
		Selection [] selections = new Selection[dtm.getRowCount()];
		//Storing traderNames 
		String[] traderNames = new String[4];
		//Index count for the traderNames array
		int index = 0;
		
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
				//Boolean variable that will check for duplicate trader clients 
				boolean duplicate = false;
					// Checking the strategy column 
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					traderName = traderObject.toString();
					
					// Checking if this trader already exists
					for(int i = 0; i < traderNames.length; i++) {
						// Display pop-up if a trader already exists 
						if(traderName == traderNames[i]) {
							duplicate = true;
							JOptionPane.showMessageDialog(this, "This Trading Client already exists ");							
						}
					}
					// Store trader name to traderNames array if unique  
					traderNames[index] = traderName;
					index++;
					
					//Check the coin column 
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					
					//Check the strategy column 
					String[] coinNames = coinObject.toString().split(",");
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					//System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
					
					//Call a selection object
					selections[count] = new Selection(traderName,coinNames,strategyName, duplicate); //Create a selection object for the input
					
	        }
			
			//Perform all trades, this is a facade design pattern that is implemented here
			new PerformTrades (selections, allTrades, allBrokers); 
			
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator(allBrokers, allTrades); 
			creator.createCharts();
			
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}

}
