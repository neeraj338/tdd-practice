package com.tdd.practice.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingCart {
	Map<Product, Integer> productBasket = new HashMap<>();
	List<Offer> offers = new ArrayList<>();
	public void addToCart(Product p, int i) {
		productBasket.put(p, i);
	}
	public int size() {
		return productBasket.size();
	}
	public void reduceProductQuantity(Product product, int by) {
		int count = productBasket.get(product);
		if(count>0) {
			productBasket.put(product, Math.max(0, count-by));
		}
	}
	public int getProductQuantity(Product product) {
		return productBasket.get(product);
	}
	public double checkout() {
		double price = 0.0;
		for(Map.Entry<Product, Integer> entry: productBasket.entrySet()) {
			price = price + (entry.getKey().getPrice()*entry.getValue());
		}
		for(Offer offer: offers) {
			price= offer.apply(productBasket, price);
		}
		return price;
	}
	public void addOffer(Offer offer) {
		this.offers.add(offer);
	}

}
