package cryptoTrader.broker;

/**
 * This class uses the factory method to return instances of the Broker object
 * @author Gleb Zvonkov
 *
 */
public class GetBroker {  //Class for factory method
	

	/**
	 * This method returns instances of the Broker object
	 * @param brokerName
	 * @return returns a specific instance of the broker object 
	 */
	public Broker getBroker(String brokerName) { //This is the factory method which returns instances of the Broker object
		
		//This is the implementation of the singleton design pattern, since we get the only existent instance of a particular broker
		if ( brokerName.equals("Broker1") ){	
			return Broker1.getInstance(); 
		}
		else if (brokerName.equals("Broker2") ){
			return Broker2.getInstance();
		}
		else if (brokerName.equals("Broker3") ){
			return Broker3.getInstance();
		}
		else if (brokerName.equals("Broker4") ){
			return Broker4.getInstance();
		}
		
		return null;	
	}

}
