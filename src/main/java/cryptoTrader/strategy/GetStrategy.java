package cryptoTrader.strategy;

/**
 * This class uses the factory method to return instances of the Strategy object
 * @author Gleb Zvonkov
 *
 */
public class GetStrategy { 
	/**
	 * This method returns new instances of the Strategy object
	 * @param strategy
	 * @return Instance of the strategy object
	 */
	public Strategy getStrategy(String strategy) { //This is the factory method which returns instances of the Strategy object

			if ( strategy.equals("Strategy-A") ){ //if Strategy A return a StrategyA object
				return new StrategyA(); 
			}
			else if ( strategy.equals("Strategy-B") ){
				return new StrategyB();
			}
			
			else if ( strategy.equals("Strategy-C") ){
				return new StrategyC();
			}
			
			else if ( strategy.equals("Strategy-D") ){
				return new StrategyD();
			}
			return null;
	}
	
	
}
