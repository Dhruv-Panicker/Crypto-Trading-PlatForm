package cryptoTrader.broker;

/**
 * This class is a concrete Broker object that is a subclass of the abstract broker class
 * This class uses the Singleton design pattern to ensure that multiple instances of Broker 2 can't be created
 * @author Gleb Zvonkov
 *
 */
public class Broker2 extends Broker {
	
	private static Broker2 instance = null;  //create an instance
	
	/**
	 * Constructor method
	 * Make constructor private, so multiple instances of broker 2 can not be created
	 */
	private Broker2() {}
	
	/**
	 * This method checks to see if an instance of the Broker2 object exists
	 * @return instance
	 */
	public static Broker2 getInstance() {
		if (instance == null) {
			instance = new Broker2();
		}
		return instance;		
	}
	
	/**
	 * Method returns name of the concrete Broker object
	 * @return broker name 
	 */
	public String name() {
		return "Broker2";
	}
}
