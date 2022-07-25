package cryptoTrader.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.app.PerformTrades;
import cryptoTrader.app.Trade;
import cryptoTrader.broker.Broker;
import cryptoTrader.gui.MainUI;

public class DataVisualizationCreator {
	
	private HashSet<Broker> allBrokers;
	private LinkedHashSet<Trade> allTrades;
	
	public DataVisualizationCreator(HashSet<Broker> allBrokers, LinkedHashSet<Trade> allTrades ){
		this.allBrokers = allBrokers;
		this.allTrades = allTrades;
	}
	
	public void createCharts() {
		createTableOutput();
		createBar();
	}

	private void createTableOutput() {
		Object[] columnNames = {"Result","Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		Object[][] data = new Object[allTrades.size()][8];  //Create an object for all the trades
		
		int j = 0;
		for (Trade i : allTrades) {  //intialize the object with all the trades
			data [j++] = i.getTrade(); 
		}
	
		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}


	private void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Broker i : allBrokers) {	 //for each broker add each strategy value to the dataset
			dataset.setValue(i.getStrategyA(), i.name(), "Strategy-A");  //Add strategy A value for broker
			dataset.setValue(i.getStrategyB(), i.name(), "Strategy-B");
			dataset.setValue(i.getStrategyC(), i.name(), "Strategy-C");
			dataset.setValue(i.getStrategyD(), i.name(), "Strategy-D");
		}
		
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.9, 20.0));     //Changed to 0.9 instead of 1.0 
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}
