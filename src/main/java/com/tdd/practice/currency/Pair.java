package com.tdd.practice.currency;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Pair {
	private String from;
	private String to;

	Pair(String from, String to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(from).append(to).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair that = (Pair) obj;
		
		return new EqualsBuilder().append(this.to, that.to).append(this.from, that.from).build();
	}
	
	
}
