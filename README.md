# **Cryptocurrency Trade System**
User Interface that displays trades performed by trading brokers.

### **Description**
This project implements a system that defines trading brokers and allows each trading broker to select cryptocurrencies that they are interested in. Each trading broker will have a trading strategy attached to them. The UI will display a trading log which will update when a trading strategy is performed by a trading broker as well as a histogram that will visualise the actions performed by the trader so far.

### **Requirements**
The system can be ran on both Windows and MacOS platforms. A processor speed of 1.6 GHz and 4 GB of RAM is recommended to run the system. 

### **Technologies**
This system was implemented using Eclipse and the CoinGecko Rest API. All code is in Java.

### **Executing the program**
1. The cryptoTrader folder will need to be imported into Eclipse as an existing Maven Project.
2. In the src/main/java folder, the cryptoTrader gui will contain the code for the Login Panel, Main UI, and the Selection class (this object stores the information stored in the Trading Client Actions window). 
3. To run the program, the LoginPanel.java class will need to be ran as a Java application.
4. When the system is started, the Login Panel will be displayed on the screen and the user will be prompted to enter a username and password.
5. If a correct username and password pair is entered (possible pairs will be stored in a text file), the main UI will be displayed (Correct username: Group41, Correct password: cs2212).
6. If the credentials entered are not stored in the text file, the window will be terminated and a pop-up window will appear stating that the wrong credentials were entered.
7. When the main UI of the system is displayed, the user will be able to add and remove trading brokers to the Trading Client Actions table. The user will enter the Trading Client Name, their coin list, and select the Trading Strategy associated with them. The coins must be written exactly as it is in the API. The different trading strategies are stored in the strategy folder in Eclipse. All strategies take any two valid coins as arguments.
8. Once a trading broker has been added to the Trading Client Action table, the user can press the "Perform Trade" button which will attempt to perform the trade according to the strategies selected for the trading brokers. 
9. If the trading strategy cannot be applied, then a message will be displayed indicating this, and a row is added on the table in the UI indicating that it has failed.
10. If the trading strategy has been successfully invoked, the right prices are passed to the right clients, and a visual display of all trades performed will be shown in the UI through a Table and Histogram.

### **Notes**
Each trading broker can only have two valid coins in their coin list. If the strategy is performed unsuccessfully, the word unsucc is written in the result. This means the trade was executed but the strategy was not successful. If the strategy is performed successfully, the word succ is written in the result. This means the strategy was executed and the trade was successful. When a trade is failed, the required parameters for a trade were not valid (i.e., a trading broker already exists, a specific cryptocoin is not valid, correct number of coins are not in the coin list (2)). Coins must be written exactly as they are in the API in full name (e.g. Bitcoin, Cardano). All valid usernames and passwords are stored in UserData.txt file. When entering cryptocoins in the table, there must be no space between two coins and the comma between them (e.g., Bitcoin,Ethereum).

### **Authors**
Gleb Zvonkov  
Dhruv Panicker  
Daniel Herbert  
Fouzia Ahsan  