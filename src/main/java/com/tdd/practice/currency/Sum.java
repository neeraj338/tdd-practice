package com.tdd.practice.currency;

public class Sum implements Expession{
	
	private Expession augend;
	private Expession addend;
	public Sum(Expession augend, Expession addend) {
		this.augend = augend;
		this.addend = addend;
	}
	public int reduce(Bank bank, String reduceTo) {
		return augend.reduce(bank, reduceTo) + addend.reduce(bank, reduceTo);
	}
	public Expession plus(Expession expression) {
		return new Sum(this, expression);
	}
	public Expession times(int times) {
		return new Sum(augend.times(times), addend.times(times));
	}
}
