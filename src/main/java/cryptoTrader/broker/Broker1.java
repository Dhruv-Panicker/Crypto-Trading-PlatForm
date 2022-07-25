package cryptoTrader.broker;

/**
 * This class is a concrete Broker object that is a subclass of the abstract broker class
 * This class uses the Singleton design pattern to ensure that multiple instances of Broker 1 can't be created
 * @author Gleb Zvonkov
 *
 */
public class Broker1 extends Broker { //Concrete Broker object is a subclass of the abstract broker class, it uses the singleton design pattern 
	
	private static Broker1 instance = null;  //variable for instance of broker 1
	
	/**
	 * Constructor method
	 * make constructor private, so multiple instances of broker 1 can not be created
	 */
	private Broker1() {} 
	
	/**
	 * This method checks to see if an instance of the Broker1 object exists
	 * @return instance
	 */
	public static Broker1 getInstance() {
		if (instance == null) { //If instance does not exist then create it
			instance = new Broker1();
		}
		return instance;	//return the single instance
	}
	
	/**
	 * Method returns name of the concrete Broker object
	 * @return broker name 
	 */
	public String name() { 
		return "Broker1";
	}
	
}
