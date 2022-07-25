package cryptoTrader.strategy;

import cryptoTrader.app.PerformTrades;
import cryptoTrader.broker.Broker;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;

/**
 * Subclass of the Strategy object, with a unique strategy to perform
 * Making use of the Strategy design pattern
 * @author Dhruv Panicker 
 * @author Gleb Zvonkov
 *
 */
public class StrategyA implements Strategy { 
	private double coinPrice1;
	private double coinPrice2;
	private String coinToGet;
	private String action;
	/**
	 * Method to execute Strategy A
	 * @param Broker context
	 * @return "true" if the strategy can be performed, and "false" if the strategy cannot be performed
	 */
	public boolean perform(Broker context) {	
		double coin1 = context.getCoin1(); //Get first coin from Broker
		double coin2 = context.getCoin2(); //Get second coin from Broker
		coinPrice1 = coin1;
		coinPrice2 = coin2;
		if (coin1 < coin2 || coin1 > 150) //If first coin smaller then second then successful strategy so return true
			return true;
		else
			return false;
	}
	
	/**
	 * Getter Method that gets the coin to buy
	 * @return Coin Name
	 */
	public String getCoin() { 
		if(coinPrice1 > 350) {
			coinToGet = "Cardano";
			return coinToGet;
		} else {
			coinToGet = "Tether";
			return coinToGet;
		}	    	
	}
	
	/**
	 * Method to check if we buy or sell according to the strategy
	 * @return "buy" or "sell"
	 */
	public String buyOrSell() { //do we buy or sell according to the strategy
		if (coinToGet == "Cardano") {
			action = "buy";
			return action;
		} else {
			action = "sell";
			return action;
		}
	}
	
	/**
	 * Getter method that returns how many coins we buy or sell (i.e. the quantity)
	 * @return quantity
	 */
	public int getQuantity() { //get the quantity to buy or sell
		if(action == "buy") {
			return 35;
		} else {
			return 124;
		}	
	}
	
	/**
	 * Getter method that returns the price of the coin to buy
	 * @return price
	 */
	public int getPrice() { //get price of coin to buy or sell
		String ID = AvailableCryptoList.getInstance().getCryptoID(coinToGet);
		DataFetcher fetcher = new DataFetcher();
		double price = fetcher.getPriceForCoin(ID, PerformTrades.date());  
		return (int) price;
	}
	
}
