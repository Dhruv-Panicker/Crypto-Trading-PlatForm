package cryptoTrader.strategy;

import cryptoTrader.app.PerformTrades;
import cryptoTrader.broker.Broker;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;

/**
 * Subclass of the Strategy object, with a unique strategy to perform
 * Making use of the Strategy design pattern
 * @author Gleb Zvonkov
 * @author Dhruv Panicker
 *
 */
public class StrategyC implements Strategy {
	private double coinPrice1;
	private double coinPrice2;
	private String coinToGet;
	private String action;

	/**
	 * Method to execute Strategy C
	 * @param Broker context
	 * @return "true" if the strategy can be performed, and "false" if the strategy cannot be performed
	 */
	public boolean perform(Broker context) {
		double coin1 = context.getCoin1();
		double coin2 = context.getCoin2();
		coinPrice1 = coin1;
		coinPrice2 = coin2;
		if (coin1 + 100 < coin2 || coin1 > 15)
			return true;
		else
			return false;
	}
	
	/**
	 * Getter Method that gets the coin to buy
	 * @return Coin Name
	 */
	public String getCoin() { //get coin to buy 
		if(coinPrice1/2 >= 22) {
			coinToGet = "USD Coin";
			return coinToGet;
		} else {
			coinToGet = "Dogecoin";
			return coinToGet;
		}    	
	}
	
	/**
	 * Method to check if we buy or sell according to the strategy
	 * @return "buy" or "sell"
	 */
	public String buyOrSell() { //do we buy or sell according to the strategy
		if(coinToGet == "Dogecoin") {
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
		int amount = 0;
		amount = ((int)coinPrice1 + (int)coinPrice2) * 3;
		if(action == "buy") {
			return amount;
		} else {
			amount = amount - 36;
			if(amount <= 0) {
				amount = 13;
			}
			return amount;
		}
	}
	
	/**
	 * Getter method that returns the price of the coin to buy
	 * @return price
	 */
	public int getPrice() { //get price of coin to buy or sell
		String ID = AvailableCryptoList.getInstance().getCryptoID("Dogecoin");
		DataFetcher fetcher = new DataFetcher();
		double price = fetcher.getPriceForCoin(ID, PerformTrades.date() );  
		return (int)price;
	}

}
