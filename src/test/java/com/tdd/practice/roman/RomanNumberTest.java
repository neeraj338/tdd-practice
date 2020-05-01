package com.tdd.practice.roman;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RomanNumberTest {
	
	RomanNumber roman = new RomanNumber();
	@Before
	public void setup() {
		roman.addRoma("I", 1);
		roman.addRoma("V", 5);
		roman.addRoma("IV", 4);
		roman.addRoma("V", 5);
		roman.addRoma("X", 10);
		roman.addRoma("XL", 40);
		roman.addRoma("L", 50);
		roman.addRoma("XC", 90);
		roman.addRoma("C", 100);
		roman.addRoma("CD", 400);
		roman.addRoma("D", 500);
		roman.addRoma("CM", 900);
		roman.addRoma("M", 1000);
	}
	
	@Test
	public void testallRoman() {
		Assert.assertEquals("test 1 ", 1, roman.toNumber("I"));
		Assert.assertEquals(2, roman.toNumber("II"));
		Assert.assertEquals(4, roman.toNumber("IV"));
		Assert.assertEquals(5, roman.toNumber("V"));
		Assert.assertThat(2006, Matchers.is(Matchers.equalTo(roman.toNumber("MMVI"))) ) ;
	}
	
	@Test
	public void testTwo() {
		
		Assert.assertEquals(1944, roman.toNumber("MCMXLIV"));
	}
	
	@Test
    public void testConvertMMXII() {
        assertThat( "Roman MMXII should equal to 2012" , 2012, is(equalTo(roman.toNumber("MMXII"))));
    }

}
