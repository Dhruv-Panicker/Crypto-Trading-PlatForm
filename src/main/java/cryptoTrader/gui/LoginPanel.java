package cryptoTrader.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

//All imported Libraries for Login Panel 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class will verify a client's login credentials, and determine whether or not they can enter the main
 * UI panel
 * @author Dhruv Panicker
 */
public class LoginPanel extends JFrame implements ActionListener { 
	// Private instance variables for the class 
	private static JLabel userNameLabel;
	private static JTextField user_Text;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton submitButton; 
	private static JLabel success;
	private static JFrame frame;
	
	// Instance variable that will read through a data file of type Scanner 
	private static Scanner x; 
	
	/**
	 * Main method that will initiate the login frame 
	 * @param args
	 */
	public static void main (String[] args) {
		// Set new Frame and Panel
		frame = new JFrame();
		JPanel panel = new JPanel();
		
		//Dimensions for frame
		//Retrieved from :https://beginnersbook.com/2015/07/java-swing-tutorial/
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		
		panel.setLayout(null);
		
		//Set Labels for the Login panel
		//Retrieved from :https://beginnersbook.com/2015/07/java-swing-tutorial/		
		// Set UserName Label
		userNameLabel = new JLabel("Username: ");
		userNameLabel.setBounds(10, 10, 80, 25);
		panel.add(userNameLabel);
		
		// Set User Text
		user_Text = new JTextField(20);
		user_Text.setBounds(100, 20, 165, 25);
		panel.add(user_Text);
		
	
		// Set Password Label
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		// Set Password Text
	    passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		// Set Submit Button
		submitButton = new JButton("Submit! ");
		submitButton.setBounds(100, 80, 100, 25);
		panel.add(submitButton);
		
		// Set Success
		success = new JLabel(" ");
		success.setBounds(10, 110, 300, 35);
		submitButton.addActionListener(new LoginPanel());
		panel.add(success);
		
		frame.setVisible(true);
	}
	/**
	 * Method that will perform an action once a button is pressed in the login panel 
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		// Set user and password
		String user = user_Text.getText();
		String password = passwordText.getText();
		
		// Set fail as false
		boolean fail = false;
		
		// Method call that will verify a user's credentials 
		boolean flag = verifyLogin(user, password, "UserData");
		
		//Conditional if correct login credentials was entered 
		if(flag) {
			// Use of Singleton Design Pattern to create one instance of the MainUI object when logging in 
			success.setText("Successful Login!");
			JFrame frameUI = MainUI.getInstance();
			frameUI.setSize(900, 600);
			frameUI.pack();
			frameUI.setVisible(true);
			// Close the login panel when Main UI has been entered 
			frame.dispose();
		//Conditional if the login credentials were wrong
		} else {
			// Open a new pop-up tab showing error and terminate the program 
			success.setText("Unsuccessful Login");
			JFrame popUp = new JFrame();
		    JOptionPane.showMessageDialog(popUp, "Wrong credentials entered! Application will now terminate.");
		    frame.setVisible(false);
		}
	}
	
	/**
	 * This method will verify through a back-end file to determine whether a users user name and password credentials match 
	 * and are valid
	 * @param userName
	 * @param password
	 * @param filename
	 * @return found, a boolean variable determine whether the search was successful or not
	 */
	public boolean verifyLogin(String userName, String password, String filename) {
		// Set found flag to be false
		boolean found = false; 
		// Temporary Username and Password
		String tempUserName = "";
		String tempPassword = "";
		
		//Compare what the user enters to username-password pairs stored in a text file
		//Retrieved from :https://stackoverflow.com/questions/54704096/searching-for-username-and-password
		try {
			x  = new Scanner(new File(filename));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext() && !found) {
				tempUserName = x.next();
				tempPassword = x.next();
				
				if(tempUserName.trim().equals(userName.trim()) && tempPassword.trim().equals(password.trim())) {
					found = true;
				}
			}
			x.close();
		} catch(Exception FileNotFound) {
			System.out.println("Error");
		}
		//Returns boolean indicating if the right credentials were entered
		return found;
	}

}
