package com.mm.brainatlas.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class PairTest {

	Pair<Integer, String> pis;
	Pair<Integer, Integer> pii;
	int first;
	int second;
	int third;
	String ssecond;
	Random rand = new Random(System.currentTimeMillis());
	
	@Before
	public void setUp() throws Exception {
		first = rand.nextInt();
		second = rand.nextInt();
		third = rand.nextInt();
		ssecond = "String testowy";
		pis = new Pair<Integer, String>(first, ssecond);
		pii = new Pair<Integer, Integer>(second, third);
	}

	@Test
	public void testPair() {
		List<Integer> testList = new ArrayList<>();
		int length = rand.nextInt() + 2;
		for (int i = 0; i < length; i++) {
			testList.add(i);
		}
		Pair<Boolean, List<Integer>> testPair = new Pair<>(true, testList);
		assertTrue(testPair.firstElement);
		for (int i = 0; i < length; i++) {
			assertEquals(testList.get(i), testPair.secondElement.get(i));
		}
		
	}

	@Test
	public void testToString() {
		String result = "Type one value is " + first + "\nType two value is " + ssecond + "\n";
		assertEquals(result, pis.toString());
		result = "Type one value is " + second + "\nType two value is " + third + "\n";
		assertEquals(result, pii.toString());
	}

	@Test
	public void testEqualsObject() {
		assertTrue(pii.equals(pii));
		assertFalse(pii.equals(pis));
		assertFalse(pis.equals(pii));
		assertTrue(pii.equals(new Pair<Integer, Integer>(second, third)));
		assertTrue(new Pair<Integer, String>(first, ssecond).equals(pis));
		assertFalse(pii.equals(null));
	}

}
