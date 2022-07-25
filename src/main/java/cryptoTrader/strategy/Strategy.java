package cryptoTrader.strategy;
import cryptoTrader.broker.Broker;

/**
 * Broker Class, This is an abstract class for the Strategy
 * @author Gleb Zvonkov 
 */
public interface Strategy { //An abstract class for the Strategy. 
	
	/**
	 * This uses the strategy design pattern since different perform methods are applied depending on the context
	 * This method exists in all Strategy classes
	 * @param context
	 * @return true if successful in performing strategy and false otherwise
	 */
	public boolean perform(Broker context); //method that exists in all Strategy objects
	
	/**
	 * Getter Method that returns the coin to buy 
	 * @return Name of coin to buy 
	 */
	public String getCoin(); 
	
	/**
	 * Method to check whether we buy or sell according to the strategy
	 * @return "buy" if we buy or "sell" if we are selling
	 */
	public  String buyOrSell(); 
	
	/**
	 * Getter method that returns how many coins we buy or sell (i.e. the quantity)
	 * @return Number of coins that we buy or sell
	 */
	public  int getQuantity(); 
	
	/**
	 * Getter method that returns the price of the coin to buy
	 * @return Price of the coin to buy 
	 */
	public  int getPrice(); 

}
