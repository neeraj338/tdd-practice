package com.tdd.practice.shoppingcart;

public class Product {
	
	private ProductName productName;
	private double price;
	public Product(ProductName productName, double price) {
		this.setPrice(price);
		this.productName = productName;
	}
	public ProductName getProductName() {
		return productName;
	}
	public void setProductName(ProductName productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
