package cryptoTrader.broker;

/**
 * This class is a concrete Broker object that is a subclass of the abstract broker class
 * This class uses the Singleton design pattern to ensure that multiple instances of Broker 4 can't be created
 * @author Gleb Zvonkov
 *
 */
public class Broker4 extends Broker{
	
	private static Broker4 instance = null;  //create an instance
	
	/**
	 * Constructor method
	 * Make constructor private, so multiple instances of broker 3 can not be created
	 */
	private Broker4() {}
	
	/**
	 * This method checks to see if an instance of the Broker3 object exists
	 * @return instance
	 */
	public static Broker4 getInstance() {
		if (instance == null) {
			instance = new Broker4();
		}
		return instance;		
	}
	/**
	 * Method returns name of the concrete Broker object
	 * @return broker name 
	 */
	public String name() {
		return "Broker4";
	}
	
}
