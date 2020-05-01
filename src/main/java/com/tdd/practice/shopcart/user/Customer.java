package com.tdd.practice.shopcart.user;

public class Customer {
	
	public static enum CustomeType{
		Regular,
	}
	
	private CustomeType customerType;
	
	public Customer(CustomeType customeType) {
		this.customerType= customeType;
	}
	
	public CustomeType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomeType customerType) {
		this.customerType = customerType;
	}

}
