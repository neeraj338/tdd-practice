package com.tdd.practice.currency;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	
	public Map<Pair, Integer> currcyPairRateMap = new HashMap<Pair, Integer>();
	public Money reduce(Expession exp, String reduceTo) {
		
		return new Money(exp.reduce(this, reduceTo), reduceTo);
	}
	public void addCurrencyPair(String from, String to, int rate) {
		currcyPairRateMap.put(new Pair(from, to), rate);
	}
	public int getRate(String from, String to) {
		if (from == to) return 1;
		else 
			return currcyPairRateMap.get(new Pair(from, to));
	}

}
