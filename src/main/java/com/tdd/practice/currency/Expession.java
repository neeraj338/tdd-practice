package com.tdd.practice.currency;

public interface Expession {

	int reduce(Bank bank, String reduceTo);

	Expession plus(Expession franc);

	Expession times(int times);

}
