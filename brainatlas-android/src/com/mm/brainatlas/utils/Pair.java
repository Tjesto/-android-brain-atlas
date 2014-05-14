package com.mm.brainatlas.utils;

public class Pair<T1, T2> {
	public T1 firstElement;
	public T2 secondElement;
	
	public Pair(T1 firstElement, T2 secondElement) {
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString()).append("\n");
		builder.append("Type one value is ").append(firstElement).append("\n");
		builder.append("Type two value is ").append(secondElement).append("\n");
		
		return builder.toString();
	}
	
	//TODO: comparing
}
