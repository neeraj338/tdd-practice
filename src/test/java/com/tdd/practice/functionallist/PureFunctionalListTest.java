package com.tdd.practice.functionallist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class PureFunctionalListTest {

	@Test
	public void testHeadReturnFirstElemetOfList() {
		List<Integer> intList = new ArrayList<>();
		intList.add(10);
		intList.add(5);
		intList.add(3);
		PureFunctionalList<Integer> functionalIntList = new PureFunctionalList<>(intList);
		Assert.assertThat(functionalIntList.head(), Matchers.is(10));
	}
	
	@Test
	public void testTailOflistDroppingFirstElemet() {
		List<Integer> intList = new ArrayList<>();
		intList.add(10);
		intList.add(5);
		intList.add(3);
		PureFunctionalList<Integer> functionalIntList = new PureFunctionalList<>(intList);
		Assert.assertThat(functionalIntList.tail(), Matchers.equalTo(Arrays.asList(5,3)) );
	}
	
	@Test
	public void testDropfirstNelemetandRetunremaining() {
		List<Integer> intList = new ArrayList<>();
		intList.add(10);
		intList.add(5);
		intList.add(3);
		PureFunctionalList<Integer> functionalIntList = new PureFunctionalList<>(intList);
		Assert.assertThat(functionalIntList.drop(2) , Matchers.equalTo(Arrays.asList(3)) );
	}
	
	
	@Test
	public void testReverse() {
		List<Integer> intList = new ArrayList<>();
		intList.add(10);
		intList.add(5);
		intList.add(3);
		PureFunctionalList<Integer> functionalIntList = new PureFunctionalList<>(intList);
		Assert.assertThat(functionalIntList.reverse(), Matchers.equalTo(Arrays.asList(3,5,10)) );
	}
	
	@Test
	public void testPrdedicateFilter() {
		List<Integer> intList = new ArrayList<>();
		intList.add(10);
		intList.add(5);
		intList.add(3);
		PureFunctionalList<Integer> functionalIntList = new PureFunctionalList<>(intList);
		Assert.assertThat(functionalIntList.filter(t-> t > 3), Matchers.everyItem(Matchers.greaterThan(3)) );
	}
	
	@Test
	public void testMapFunction() {
		List<Integer> intList = new ArrayList<>();
		intList.add(10);
		intList.add(5);
		intList.add(3);
		PureFunctionalList<Integer> functionalIntList = new PureFunctionalList<>(intList);
		Assert.assertThat(functionalIntList.map(x->x+"N") , Matchers.equalTo(Arrays.asList("10N", "5N", "3N")) );
	}
}
