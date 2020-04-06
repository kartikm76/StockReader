package com.stockmgmt.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.stockmgmt.interfaces.InterfaceUserSettings;
import com.stockmgmt.model.User;

public class UserSettings implements InterfaceUserSettings {	
	User user = new User();
	Scanner scanObj = new Scanner(System.in);
	
	public boolean findUserLogin(String login) {
		boolean loginFound = false;
		String line = "";
		String cvsSplitBy = ",";
		
		BufferedReader br = null;
			
		 try {
		    	br = new BufferedReader (new FileReader ("./data/UserSettings.csv"));
	
			    while ((line = br.readLine()) != null) {
			    	String [] values = line.split(cvsSplitBy);
			    	
		    		for (String element: values) {
		    			if (element.equals(login)) {
		    				loginFound = true;
			    			break;
		    			}
			    	}
			    }
		 } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} 
		return loginFound;
	}
	
	public void setUserDetails() {
		System.out.println("************************");
		System.out.println("*** USER SETTINGS ***");
		System.out.println("************************");
		System.out.println("");
		System.out.println("Please enter your profile before you can buy/sell stocks");
		System.out.println("");
		
		getSetUserLogin();
		getSetUserPassword();
		getSetUserName();
		getSetUserAge();
		getSetUserPhoneNumber();
		getSetUserBalance();
		getSetUserAddress();
		getSetZipCode();
		
		updateUserSettings();
		scanObj.close();
	}

	
	/*This method sets the username for the user's account.
	 * A username must be greater than 5 characters.
	 */
	private void getSetUserLogin()
	{
		String x = "";
		int y = x.length();
		while (y <= 5)
		{
			System.out.print("1. What is your preferred username? (Must be greater than 5 characters.): ");
			x = scanObj.nextLine();
			y = x.length();
			if(y <= 5)
			{
				System.out.println("Username is not valid. Please try again.");
				System.out.println("");
			}
		}
		user.setUsername(x);
	}
	
	/* This method sets the password for the user. 
	 * The password must be greater than or equal to 10 characters.
	 */
	
	private void getSetUserPassword()
	{
		String x = "";
		int y = x.length();
		while (y < 10)
		{
			System.out.print("2. What is your preferred password? (Must be greater than or equal to 10 characters.): ");
			x = scanObj.nextLine();
			y = x.length();
			if(y < 10)
			{
				System.out.println("Password is not valid because it did not meet the required length.");
				System.out.println("The password length is necessary to secure your account and information.");
				System.out.println("Please try again");
			}
		}
		user.setPassword(x);
	}
	
	/*This method sets the name of the user */
	private void getSetUserName() {
		System.out.print("3. What is your name?: ");
		user.setName(scanObj.nextLine());
	}
	
	/*This method sets the age of the user 
	 * age cannot be less than 18
	 * */
	private void getSetUserAge() {
		/*This sets the name of the user. The user must be over 18 years old to buy stocks */
		int x = 0;
		while(x < 18)
		{
			System.out.print("4. What is your age?: ");
			x = scanObj.nextInt();
			if(x < 18)
			{
				System.out.println("You are not eligible to purchase stocks.");
			}
		}
		user.setAge(x);
	}
	
	/*This method sets the user's phone number. 
	 * A phone number must be 10 digits long.
	 */	
	private void getSetUserPhoneNumber() {
		String x = "";
		int y = x.length();
		while (y != 10)
		{
			System.out.print("5. What is your phone number? Please enter all digits.: ");
			x = scanObj.nextLine();
			y = x.length();
			if(y != 10)
			{
				System.out.println("Phone number not valid. Be sure to enter to phone number correctly and without error.");
			}
		}
		user.setPhoneNumber(x);
	}
	
	/*This method sets the balance of the user.
	 * The user must start between $1000.00 and $2000.00 inclusive.
	 */	
	private void getSetUserBalance()
	{
		double y = 0.0;
		while(y < 1000.00 || y > 20000.00)
		{
			System.out.println("6. How much money would you like to deposit in your account? You must start off between $1000.00 and $20000.00.: ");
			y = scanObj.nextDouble();
			if (y < 1000.00 || y > 20000.00)
			{
				System.out.println("Sorry, but that is not a valid amount. Please try again.");
			}
		}
		user.setBalance(y);
	}
	
	/*This method sets the address of the user */	
	private void getSetUserAddress()
	{
		System.out.print("7. Please enter your address in the form: House#, Street, City, State.: ");
		user.setAddress(scanObj.nextLine());
	}
	
	/*This method sets the zip code of the user.
	 * The zip code must be five digits long.
	 */
	private void getSetZipCode()
	{
		String x = "";
		int y = x.length();
		while (y != 5)
		{
			System.out.print("8. What is your zip code? Please enter all digits.: ");
			x = scanObj.nextLine();
			y = x.length();
			if(y != 5)
			{
				System.out.print("Be sure to enter the zip code correctly and without error.");
			}
		}
		user.setZipCode(x);
	}
	
	private void updateUserSettings() {
		System.out.println("");
		System.out.println("Congratulations! Your registration is complete!");
	}
}