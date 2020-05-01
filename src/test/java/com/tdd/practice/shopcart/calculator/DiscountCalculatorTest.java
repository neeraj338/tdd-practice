package com.tdd.practice.shopcart.calculator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

public class DiscountCalculatorTest {
	
	private static NavigableMap<Double, Double> discountMap;
	
	private static DiscountCalculator discountCalculator;
	
	@BeforeClass
    public static void beforeAllTestMethods() {
		// setup discount range map
		discountMap = new TreeMap<Double, Double>();
		discountMap.put(5000.0, 0.0);
		discountMap.put(10000.0, 10.0);
		discountMap.put(Double.POSITIVE_INFINITY, 20.0);
		
		//instantiate calculator
		discountCalculator = new DiscountCalculator(discountMap);
    }
	
	@Test
	public void testForPurchaseAmountEqualToFiveThousand() {
		Double purchaseAmount = 5000.00;
		Double discountedAmount = discountCalculator.calculateDiscount(purchaseAmount);
		
		Assert.assertEquals(purchaseAmount, discountedAmount);
		
	}
	
	@Test
	public void testForPurchaseAmountEqualToTenThousand() {
		Double purchaseAmount = 10000.00;
		Double discountedAmount = discountCalculator.calculateDiscount(purchaseAmount);
		Double expectedDiscount = 9500.00;
		Assert.assertEquals(expectedDiscount , discountedAmount);
		
	}
	
	@Test
	public void testForPurchaseAmountEqualToFifteenThousand() {
		
		Double purchaseAmount = 15000.00;
		Double discountedAmount = discountCalculator.calculateDiscount(purchaseAmount);
		Double expectedDiscount = 13500.00;
		
		Assert.assertEquals(expectedDiscount , discountedAmount);
		
	}
	
	@Test
	public void testForPurchaseAmountEqualToTenThousandEightHundred() {
		Double purchaseAmount = 10800.00;
		Double discountedAmount = discountCalculator.calculateDiscount(purchaseAmount);
		Double expectedDiscount = 10140.00;
		Assert.assertEquals(expectedDiscount , discountedAmount);
		
	}
	
	@Test
	public void testForPurchaseAmountEqualToSixThousand() {
		Double purchaseAmount = 6000.00;
		Double discountedAmount = discountCalculator.calculateDiscount(purchaseAmount);
		Double expectedDiscount = 5900.00;
		Assert.assertEquals(expectedDiscount , discountedAmount);
		
	}

}
