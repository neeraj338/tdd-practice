package com.tdd.practice.shoppingcart;

import java.util.Map;

public class BuyOneGetOneOrangeFree implements Offer{

	@Override
	public double apply(Map<Product, Integer> checkedOutProduct, double total) {
		
		for(Map.Entry<Product, Integer> entry : checkedOutProduct.entrySet()) {
			
			if(entry.getKey().getProductName() == ProductName.ORANGE 
					&& entry.getValue()>0) {
				int freecount = entry.getValue()/2;
				total = total - freecount* entry.getKey().getPrice();
				int addMoreTobasket = entry.getValue()%2;
				checkedOutProduct.put(entry.getKey(), entry.getValue() + addMoreTobasket);
			}
		}
		return total;
	}

}
