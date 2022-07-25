package cryptoTrader.app;

/**
 * This class will store all required information for a line in the Trader Action Table
 * @author Gleb Zvonkov
 *
 */
public class Trade {
	String result;
	String trader;
	String strategy;
	String coin;
	String action;
	int quantity;
	double price;
	String date;
	
	/**
	 * Constructor method that will store all the required information to initialize a trader object
	 * @param result
	 * @param trader
	 * @param strategy
	 * @param coin
	 * @param action
	 * @param quantity
	 * @param price
	 * @param date
	 */
	public Trade(String result,String trader,String strategy,String coin,String action,int quantity,double price,String date ) { //constructor
		this.result = result;
		this.trader = trader;
		this.strategy = strategy;
		this.coin = coin;
		this.action = action;
		this.quantity = quantity;
		this.price = price;
		this.date = date;
	}
	
	/**
	 * Getter method used to fetch a trade as an array of strings
	 * @return trade array
	 */
	public String[] getTrade(){ 
		String[] trade = {result,trader, strategy, coin, action, String.valueOf(quantity),String.valueOf((int)price),date};
		return trade;
	}
	
	
}
