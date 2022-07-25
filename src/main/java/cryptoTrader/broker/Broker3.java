package cryptoTrader.broker;


/**
 * This class is a concrete Broker object that is a subclass of the abstract broker class
 * This class uses the Singleton design pattern to ensure that multiple instances of Broker 3 can't be created
 * @author Gleb Zvonkov
 *
 */
public class Broker3 extends Broker {
	
	private static Broker3 instance = null;  //create an instance
	
	/**
	 * Constructor method
	 * Make constructor private, so multiple instances of broker 3 can not be created
	 */
	private Broker3() {}
	
	/**
	 * This method checks to see if an instance of the Broker3 object exists
	 * @return instance
	 */
	public static Broker3 getInstance() {
		if (instance == null) {
			instance = new Broker3();
		}
		return instance;		
	}
	
	/**
	 * Method returns name of the concrete Broker object
	 * @return broker name 
	 */
	public String name() {
		return "Broker3";
	}
}
