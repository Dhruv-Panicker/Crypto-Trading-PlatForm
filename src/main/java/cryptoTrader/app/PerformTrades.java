package cryptoTrader.app;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import cryptoTrader.broker.Broker;
import cryptoTrader.broker.GetBroker;
import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.Selection;
import cryptoTrader.strategy.GetStrategy;
import cryptoTrader.strategy.Strategy;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;

/**
 * This is a class that will initiate and execute a trade for a corresponding trading client, 
 * strategy and coin names
 * @author Gleb Zvonkov
 * @author Dhruv Panicker 
 *
 */
public class PerformTrades {  //This class contains all the methods necessary to perform trades. This is a facade. 
	
	/**
	 * Constructor for the PreformTrades class initializing all string arrays, and the coin prices
	 * HashMap
	 * @param selections
	 * @param allTrades
	 * @param allBrokers
	 */
	public PerformTrades(Selection [] selections, LinkedHashSet<Trade> allTrades, HashSet<Broker> allBrokers) { //This is a facade it combines all the method. 
		String uniqueCoins[] = getUniqueCoinNames(selections);  //Get unique coins from all the selections  
		String availableCoins[] = AvailableCryptoList.getInstance().getAvailableCryptos(); //Get available coins
		String uniqueAvailableCoins[] = findDuplicates(uniqueCoins, availableCoins); //Get unique coins that are available
		HashMap<String, Double> coinPrices = getCoinPrices (uniqueAvailableCoins);  //Get hash map with key: unique available coin and value: price of coin
		executeTrades(selections, allTrades, allBrokers,  coinPrices );   //Execute the trade using the coin price hash map
	}
	
	/**
	 * This method will print all the components of a selection object (broker name, coin prices, strategy name)
	 * @param selections
	 */
	public void printAllSelections(Selection [] selections){ //Print all the selections in the selection array
		for (int i = 0; i < selections.length; i++) {
			System.out.print( selections[i].getTraderName() + " ");   //print broker name
			System.out.print( Arrays.toString(selections[i].getCoinNames()) + " "); //print coin names
			System.out.print( selections[i].getStrategyName() ); //print strategy name
			System.out.println();
		}
	}
	/**
	 * This method will retrieve all the  possible coin names, and add those coin names to a hashmap
	 * @param selections
	 * @return hashmap key 
	 */
	// Retrieved from: https://stackoverflow.com/questions/17967114/how-to-efficiently-remove-duplicates-from-an-array-without-using-set?page=2&tab=scoredesc#tab-top
	private String[] getUniqueCoinNames(Selection [] selections) {   //get a list of unique coins names. 
		HashMap<String, Integer> Coins = new HashMap<String, Integer>();  //Hash map for all coins 
		for (int i = 0; i < selections.length; i++) {   //for all selection
			String[] coinsOfSelection = selections[i].getCoinNames(); //get array of coin names
			for (int j = 0; j < coinsOfSelection.length; j++) { //for array of coin names
				Coins.put(coinsOfSelection[j],i); //add coin name to hashmap
			}
		}
		return Coins.keySet().toArray(new String[Coins.keySet().size()]);  //return the hashmap key set since it contains only unique coins
	}
	
	/**
	 * This method will find duplicates between two Hashsets
	 * @param array1
	 * @param array2
	 * @return set2
	 */
	//Retrieved from: https://stackoverflow.com/questions/8379510/finding-duplicate-values-between-two-arrays
	private String[] findDuplicates (String array1[], String array2[] ) { //get a list of duplicates in two arrays
		Set<String> set1 = new HashSet<String>(); //hash set for one array
		Set<String> set2 = new HashSet<String>(); //hash set for duplicates
        for (String i : array1) {  //convert array1 to set1
            set1.add(i);
        }
        for (String i : array2) {  //for all element in array2
        	if (set1.contains(i)) { //check if set1 contains the element
        		set2.add(i); //if it does add it to set2
        	}
        }
        //Retrieved from https://stackoverflow.com/questions/33593371/how-to-convert-hash-set-into-array-using-toarray-if-the-method-toarray-is-not
		return Arrays.copyOf(set2.toArray(), set2.toArray().length, String[].class); // return set2 as an array
	}
	
	/**
	 * This method will use the DataFetcher function to retrieve specific coin prices and return it to a hashmap
	 * with the key as the coin and value as the price of that coin
	 * @param uniqueAvailableCoins
	 * @return coinPrices
	 */
	private HashMap<String, Double> getCoinPrices (String uniqueAvailableCoins[]){  //get the coin prices for the coins
        String coinIDs[] = new String[uniqueAvailableCoins.length]; //an array for coin IDs
        int j = 0;	 //increment variable
        for (String i : uniqueAvailableCoins) { //for all coins 
        	coinIDs[j++] = AvailableCryptoList.getInstance().getCryptoID(i); //get coins ID
        }
        DataFetcher fetcher = new DataFetcher();  //create new data fetcher
        HashMap<String, Double> coinPrices = new HashMap<String, Double>(); //hash map with key: coin and value: price of coin
        int g = 0; //increment variable
        for (String i : coinIDs) {  //for all coins IDs
        	Double price = fetcher.getPriceForCoin(i, date());  //get the coin price
        	coinPrices.put(uniqueAvailableCoins[g++], price);  //set key: coin and value: price of coin
        }
        return coinPrices; //return hash map
	}
	
	/**
	 * The date in a formatted manner will be retreived from this method
	 * @return dateString
	 */
	//Retrieved from: https://www.javatpoint.com/java-get-current-date 
	public static String date() {   //return the current data as a string
		Date date = new Date(); //create new data
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //create formatter
        String dateString = formatter.format(date); //format the date
        return dateString; //return the string
	}
	
	
	/**
	 * This method will first check if all the requirements are met to perform a trade, and then it will execute a strategy for a particular
	 * client, and the result of this trade will be sent to the Trader Action Table and the Histogram
	 * @param selections
	 * @param allTrades
	 * @param allBrokers
	 * @param coinPrices
	 */
	private void executeTrades(Selection [] selections, HashSet<Trade> allTrades, HashSet<Broker> allBrokers, HashMap<String, Double> coinPrices  ) { //execute the trades
		  for (Selection i: selections) { //for all selections
	        	
	        	String name = i.getTraderName();   //get the broker name
	        	String strategy = i.getStrategyName();//get the strategy name
	        	boolean flag = i.getdupicateBroker(); //get true or false whether there is a dupicate broker 
	        	String[] currentCoins = i.getCoinNames();    //get coin names
	        	Broker currentBroker = new GetBroker().getBroker(name); //get broker instance from factory method
	        	Strategy currentStrategy = new GetStrategy().getStrategy(strategy); //get the strategy from factory method
	        	Trade trade; //trade object that will be the result of a single selection
	        	if(flag == true) { // If a duplicate broker exists
	        		trade = new Trade("Failed",name,strategy,"-","-", 0, 0, date());  //create a failed trade
	        	} 
	        	else if (currentCoins.length != 2) {  // if there are more or less than two arguments
	        		trade = new Trade("Failed",name,strategy,"-","-", 0, 0, date());  //create a failed trade
	        		JOptionPane.showMessageDialog(MainUI.getInstance(), "This strategy only takes in 2 coins" ); //error message
	        	}
	        	else {
	        		
	        		String [] availableCoins = AvailableCryptoList.getInstance().getAvailableCryptos(); //Get available coins
	        		String [] duplicates = findDuplicates(availableCoins,currentCoins);  //find duplicate coins between coins of selection and available coins
	        		if (duplicates.length < 2) { //If the two arguments are not valid coins
	        			trade = new Trade("Failed",name,strategy,"-","-", 0, 0, date()); //create a failed trade
	        			JOptionPane.showMessageDialog(MainUI.getInstance(), "Arguments must be valid coins" ); //error message
	        		}
	        		else {
			        	double coin1 = coinPrices.get ( currentCoins[0]); //get the first coins price
			        	double coin2 = coinPrices.get ( currentCoins[1]); //get the second coins price
			        	currentBroker.setCoins(coin1,coin2);  //set the coins for the brokers
			        	currentBroker.setStrategy(currentStrategy); //set the strategy for the broker
			        	boolean result = currentBroker.execute(); //execute the strategy in the current broker
			        	if (result == true) { //strategy is successful
			        		currentBroker.incrementStrategy(strategy);
			        		trade = new Trade("Succ",name,strategy,currentStrategy.getCoin(), currentStrategy.buyOrSell(), currentStrategy.getQuantity(), currentStrategy.getPrice(), date());
			        	} else { //strategy is unsuccessful
			        		trade = new Trade("Unsucc",name,strategy,"-","-", 0, 0, date());
			        	}
	        		}
	        	}
	        	allTrades.add(trade); //add trade to hash set of traders
	        	allBrokers.add(currentBroker); //add broker to hash set of brokers 
	        }
	}

	
}
