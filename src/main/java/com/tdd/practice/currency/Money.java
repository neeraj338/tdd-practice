package com.tdd.practice.currency;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public  class Money implements Expession{
	
	private int amount;
	
	private String currency;
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	
	public Expession times(int times) {
		return new Money(this.amount * times, this.currency);
	}
	
	public Expession plus(Expession addend) {
		return new Sum(this, addend);
	}
	
	public int reduce(Bank bank, String reduceTo) {
		
		return this.getAmount()*bank.getRate(this.currency, reduceTo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Money that = (Money) obj;
		return new EqualsBuilder().append(this.amount, that.amount)
				.append(this.currency, that.currency)
				.build();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.amount).append(this.currency).build();
	}
	public int getAmount() {
		return this.amount;
	}

	public static Money doller(int amount) {
		return new Money(amount, "USD");
	}
	public static Money franc(int amount) {
		return new Money(amount, "CHF");
	}

	public String getCurrency() {
		return this.currency;
	}

}
