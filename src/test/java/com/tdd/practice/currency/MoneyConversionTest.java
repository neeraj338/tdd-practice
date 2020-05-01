package com.tdd.practice.currency;

import org.junit.Assert;
import org.junit.Test;

import com.tdd.practice.currency.Bank;
import com.tdd.practice.currency.Expession;
import com.tdd.practice.currency.Money;

public class MoneyConversionTest {

	@Test
	public void testDollerMultiply() {
		Money five = Money.doller(5);
		Assert.assertEquals(Money.doller(10), five.times(2));

		Assert.assertEquals(Money.doller(15), five.times(3));
	}
	
	@Test
	public void testDollerFrancEquality() {
		Assert.assertEquals(Money.doller(5), Money.doller(5));
		Assert.assertFalse(Money.doller(5).equals(Money.doller(10)));
		
		Assert.assertFalse(Money.doller(5).equals(Money.franc(10)));
		Assert.assertTrue(Money.franc(10).equals(Money.franc(10)));
	}
	
	@Test
	public void testFrancMultiply() {
		Money five = Money.franc(5);
		Assert.assertEquals(Money.franc(10), five.times(2));

		Assert.assertEquals(Money.franc(15), five.times(3));
	}
	
	@Test
	public void testCurrency() {
		Assert.assertEquals("USD", Money.doller(1).getCurrency());
		Assert.assertEquals("CHF", Money.franc(1).getCurrency());
	}
	
	@Test
	public void testAddition() {
		Expession sum = Money.doller(5).plus(Money.doller(5));
		Bank bank = new Bank();
		//bank.addCurrencyPair("CHF", "USD", 2);
		Assert.assertEquals(Money.doller(10), bank.reduce(sum, "USD"));
	}
	
	@Test
	public void testMixedCurrencyAddition() {
		Expession sum = Money.doller(5).plus(Money.franc(5));
		Bank bank = new Bank();
		bank.addCurrencyPair("CHF", "USD", 2);
		Assert.assertEquals(Money.doller(15), bank.reduce(sum, "USD"));
	}
	
	@Test
	public void testMixedCurrencyAdditionAndMultiplication() {
		Expession sum = Money.doller(5).plus(Money.franc(5));
		Expession plusAndtimeExp = sum.plus(Money.franc(10)).times(2);
		Bank bank = new Bank();
		bank.addCurrencyPair("CHF", "USD", 2);
		//Money res = bank.reduce(plusAndtimeExp, "USD");
		Assert.assertEquals(Money.doller(70), bank.reduce(plusAndtimeExp, "USD"));
	}
}
