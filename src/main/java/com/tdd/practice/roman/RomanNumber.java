package com.tdd.practice.roman;

import java.util.NavigableMap;
import java.util.TreeMap;

public class RomanNumber {
	public NavigableMap<String, Integer> romanToIntMap = new TreeMap<>();
	public int toNumber(String string) {
		int sum = 0;
		for(int i=0; i<string.length();i++) {
			if(i>0 && romanToIntMap.get(String.valueOf(string.charAt(i-1))) < romanToIntMap.get(String.valueOf(string.charAt(i))) ){
				sum = sum - romanToIntMap.get(String.valueOf(string.charAt(i-1)));
				sum = sum +romanToIntMap.get(String.format("%s%s", string.charAt(i-1), string.charAt(i)));
			}else {
				sum = sum +romanToIntMap.get(String.valueOf(string.charAt(i)));
			}
		}
		return sum;
	}
	
	public void addRoma(String romanStrng, int number) {
		romanToIntMap.put(romanStrng, number);
	}
}
