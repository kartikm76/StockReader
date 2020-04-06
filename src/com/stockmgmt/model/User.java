package com.stockmgmt.model;

public class User 
{
	/*A userSetting object is initialized with several properties:
	 * name is a String that represents the user's name
	 * age is an int that represents the user's age
	 * phoneNumber is a String that represents the user's phone number
	 * balance is a double that represents the user's current balance
	 * address is a String that represents the user's house, street, city, and state
	 * zipCode is a String that represents the user's zip code
	 * username is a String that represents the user's username
	 * password is a String that represents the user;s password
	 */

	private String login;
	private String name;
	private int age;
	private String phoneNumber;
	private double balance;
	private String address;
	private String zipCode;
	private String username;
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}




