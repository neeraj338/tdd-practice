package com.tdd.practice.shopcart.calculator;

import java.util.Map.Entry;
import java.util.NavigableMap;

public class DiscountCalculator {
	
	private NavigableMap<Double, Double> discountRangeMap;
	
	public DiscountCalculator(NavigableMap<Double, Double> discountRangeMap){
		this.discountRangeMap = discountRangeMap;
	}
	
	public Double calculateDiscount(Double purchaseAmount) {
		return purchaseAmount - getTotalDiscount(purchaseAmount);
	}
	
	private Double getTotalDiscount(Double purchaseAmount) {
		Double discount = 0.0;
		while(purchaseAmount > 0) {
			Entry<Double, Double> ceilingEntry = this.discountRangeMap.ceilingEntry(purchaseAmount);
			if(null == ceilingEntry) {
				return discount;
			}else {
				Double slabAmountEligibleForDiscount = purchaseAmount;
				if(purchaseAmount > this.discountRangeMap.floorEntry(purchaseAmount).getKey()) {
					 slabAmountEligibleForDiscount = purchaseAmount - this.discountRangeMap.floorEntry(purchaseAmount).getKey();
				}else if(null != this.discountRangeMap.lowerEntry(purchaseAmount)
						&& purchaseAmount > this.discountRangeMap.lowerEntry(purchaseAmount).getKey() ) {
					slabAmountEligibleForDiscount = purchaseAmount - this.discountRangeMap.lowerEntry(purchaseAmount).getKey();
				}
				discount = discount + slabAmountEligibleForDiscount * (ceilingEntry.getValue()/100);
				purchaseAmount = purchaseAmount - slabAmountEligibleForDiscount;
			}
		}
		return discount;
	}
	
	
}
