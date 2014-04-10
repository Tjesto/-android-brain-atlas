package com.mm.brainatlas.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mm.brainatlas.utils.Utils;

public class UtilsTest {
	
	private String name;
	
	private String activityName;
	
	private String testName;
	
	@Before
	public void setUp() {
		name = "activity.test.name";
		activityName = "MainActivity";
		testName = "name";
	}
	
	@Test
	public void testGetNameFromTag() {
		assertEquals(testName, Utils.getNameFromTag(name));
		assertNotEquals(activityName, Utils.getNameFromTag(name));
		assertEquals(testName, Utils.getNameFromTag(testName));
	}
	
	
}
