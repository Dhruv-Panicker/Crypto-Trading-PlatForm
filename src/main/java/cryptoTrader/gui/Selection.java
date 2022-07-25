package cryptoTrader.gui;
/**
 * This class will specify all the relevant information required when a trading client makes a selction 
 * to perform a trade
 * @author Gleb Zvonkov
 * @author Dhruv Panicker
 *
 */
public class Selection {
	
	private String traderName;  //A string for the traders name 
	private String[] coinNames; //An array of string for the coin names
	private String strategyName; //A string for the strategy name
	private boolean duplicateBroker;

	/**
	 * The constructor for the selection class, initializing a selection object
	 * @param traderName
	 * @param coinNames
	 * @param strategyName
	 */
	public Selection(String traderName,String[] coinNames,String strategyName, boolean duplicateBroker) {
		this.duplicateBroker = duplicateBroker;
		this.traderName = traderName;
		for (String i: coinNames) { //for all coins
			i = i.trim(); //remove the white space before and after the coin
		}
		this.coinNames = coinNames;
		this.strategyName = strategyName;
	}
	
	/**
	 * Getter method for a trader name
	 * @return traderName
	 */
	public String getTraderName(){
		return traderName;
	}
	
	/**
	 * Getter method for all coin names
	 * @return coinNames
	 */
	public String[] getCoinNames(){
		return coinNames; 
	}
	/**
	 * Getter method for a strategy name
	 * @return strategyName
	 */
	public String getStrategyName() {
		return strategyName;
	}
	
	/**
	 * Getter method that will find if a duplicate broker exists 
	 * @return true if there is an existing broker, false otherwise 
	 */
	public boolean getdupicateBroker() {
		return duplicateBroker;
	}
	
	

}
