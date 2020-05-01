package com.tdd.practice.shoppingcart;

import java.util.Map;

public interface Offer {
	public double apply(Map<Product, Integer> checkedOutProduct, double total);
}
