package com.tdd.practice.shoppingcart;

import java.util.Map;

public class ThreeAppleOfPriceofTwo implements Offer{

	@Override
	public double apply(Map<Product, Integer> checkedOutProduct, double totalCost) {
		for(Map.Entry<Product, Integer> entry : checkedOutProduct.entrySet()) {
			
			if(entry.getKey().getProductName() == ProductName.APPLE 
					&& entry.getValue()>2) {
				int freecount = entry.getValue()/3;
				totalCost = totalCost - freecount* entry.getKey().getPrice();
			}
		}
		return totalCost;
	}

}
