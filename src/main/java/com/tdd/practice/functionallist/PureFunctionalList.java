package com.tdd.practice.functionallist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PureFunctionalList<T extends Serializable> implements Cloneable{
	private List<T> functionalList = new ArrayList<>();
	public PureFunctionalList(List<T> list) {
		for(T ele: list) {
			functionalList.add(cloneIt(ele));
		}
	}
	public T head() {
		return functionalList.size() > 0 ? cloneIt(functionalList.get(0)) : null;
	}
	
	public List<T> tail() {
		PureFunctionalList<T> result= clone();
		result.functionalList.remove(0);
		return result.functionalList;
	}
	
	public List<T> drop(int i) {
		List<T> resultList = new ArrayList<>();
		while(i<=functionalList.size()-1) {
			resultList.add(cloneIt(functionalList.get(i)));
			i++;
		}
		return resultList;
	}
	public List<T> reverse(){
		PureFunctionalList<T> result= clone();
		java.util.Collections.reverse(result.functionalList);
		return result.functionalList;
	}
	
	public List<T> filter(Predicate<T> predicate) {
		List<T> collect = functionalList.stream()
		.filter(predicate)
		.collect(Collectors.toList());
		PureFunctionalList<T> pflist = new PureFunctionalList<T>(collect);
		return pflist.functionalList;
	}
	
	public <R extends Serializable> List<R> map(Function<T, R> mapper) {
		List<R> collect = functionalList.stream()
		.map(x->mapper.apply(x))
		.collect(Collectors.toList());
		return new PureFunctionalList<R>(collect).functionalList;
	}
	
	@Override
	public PureFunctionalList<T> clone()
	{
		return new PureFunctionalList<T>(this.functionalList);
	}
	
	private T cloneIt(T ele) {
		return org.apache.commons.lang3.SerializationUtils.clone(ele);
	}
}
