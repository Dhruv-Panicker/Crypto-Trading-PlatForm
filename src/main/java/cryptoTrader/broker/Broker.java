package cryptoTrader.broker;

import cryptoTrader.strategy.Strategy;

/**
 * This class will create an abstract broker object that will store the 
 * number of strategies successfully performed
 * @author Gleb Zvonkov
 *
 */
public abstract class Broker {
	
	double coin1; //coin value for broker
	double coin2; //coin value for broker
	
	private Strategy strategy; //strategy object associated with each broker
	
	int strategyA; //integer to store the number of times strategyA has been successfully performed
	int strategyB;
	int strategyC;
	int strategyD;
	
	/**
	 * Constructor method that will create an abstract broker object 
	 * that will store the number of strategies successfully performed
	 */
	public Broker (){ 
		strategyA = 0;  //Initialize all strategy integers to 0
		strategyB = 0;
		strategyC = 0;
		strategyD = 0;
	}
	
	/**
	 * Setter method used to set the coin values 
	 * @param coin1
	 * @param coin2
	 */
	public void setCoins(double coin1, double coin2) {  //set the coin values
		this.coin1 = coin1;
		this.coin2 = coin2;
	}
	
	/**
	 * Setter method used to set the strategy object associated with each broker
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy) { //set the strategy object
		this.strategy = strategy;
	}
	
	/**
	 * Getter method that returns the value for coin1
	 * @return coin1
	 */
	public double getCoin1() { 
		return coin1;	
	}
	
	/**
	 * Getter method that return the value for coin2
	 * @return coin2
	 */
	public double getCoin2() {
		return coin2;
	}
	
	/**
	 * Getter method that returns the number of times strategyA was successfully performed
	 * @return strategyA count
	 */
	public int getStrategyA() {//getters for the strategy integers
		return strategyA;
	}
	
	/**
	 * Getter method that returns the number of times strategyB was successfully performed
	 * @return strategyB count
	 */
	public int getStrategyB() {
		return strategyB;
	}
	
	/**
	 * Getter method that returns the number of times strategyC was successfully performed
	 * @return strategyC count
	 */
	public int getStrategyC() {
		return strategyC;
	}
	
	/**
	 * Getter method that returns the number of times strategyD was successfully performed
	 * @return strategyD count
	 */
	public int getStrategyD() {
		return strategyD;
	}
	
	/**
	 * Increment the corresponding strategy integer for the broker object
	 * @param strategy
	 */
	public void incrementStrategy(String strategy) { 
		if (strategy.equals("Strategy-A")) { //if strategy is A then increment strategy A integer
			this.strategyA++;
		}
		else if (strategy.equals("Strategy-B")) {
			this.strategyB++;
		}
		else if (strategy.equals("Strategy-C")) {
			this.strategyC++;
		}
		else if (strategy.equals("Strategy-D")) {
			this.strategyD++;	
		}
	}
	
	/**
	 * Abstract method used to return the name of the concrete Broker object
	 * @return
	 */
	public abstract String name(); //Abstract method to return the name of the concrete Broker object
	
	/**
	 * This is the strategy design pattern, where a broker executes its corresponding strategy
	 * @return "true" if the strategy is executed successfully
	 */
	public boolean execute() {  
		return strategy.perform(this); 
	}
	
}
