package com.mm.brainatlas.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mm.brainatlas.utils.Utils;

public class UtilsTest {
	
	private String name;
	
	private String activityName;
	
	private String testName;
	
	private String testString1;
	
	private String testString2;
	
	private String exceptionString1;
	
	private String exceptionString2;
	
	private String toNormalize1;
	private String toNormalize2;
	private String toNormalize3;
	private String normalized1;
	private String normalized2;
	private String normalized3;
	
	@Before
	public void setUp() {
		name = "activity.test.name";
		activityName = "MainActivity";
		testName = "name";
		testString1 = "This is a test text__1";
		testString2 = "this_is_a_test_text__2";
		
		exceptionString1 ="this__is__incorect__string";
		exceptionString2 ="another_incorect_string";
		
		toNormalize1 = "£añcuch do znormalizowania";
		normalized1 = "lancuch_do_znormalizowania";
		
		toNormalize2 = "a¹bcæ deêf ghi jkl³ mnñoó pqrsœ tuv  wxyz¿Ÿ";
		normalized2 = "aabcc_deef_ghi_jkll_mnnoo_pqrss_tuv__wxyzzz";
		
		toNormalize3 = "This is a normal text";
		normalized3 = "this_is_a_normal_text";
		
	}
	
	@Test
	public void testGetNameFromTag() {
		assertEquals(testName, Utils.getNameFromTag(name));
		assertNotEquals(activityName, Utils.getNameFromTag(name));
		assertEquals(testName, Utils.getNameFromTag(testName));
	}
	
	@Test
	public void testGetViewNumFromString() {
		try {
			assertEquals(1, Utils.getViewNumFromString(testString1));
			assertEquals(2, Utils.getViewNumFromString(testString2));
		} catch (IllegalStringFormatException e) {
			fail("Not expected exception");
		}
		
		try {
			Utils.getViewNumFromString(exceptionString1);
			fail("Exception expected");
		} catch (IllegalStringFormatException e) {
			assertTrue(true);
		}
		try {
			Utils.getViewNumFromString(exceptionString2);
			fail("Exception expected");
		} catch (IllegalStringFormatException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testNormalizeName() {
		assertEquals(normalized1, Utils.normalizeName(toNormalize1));
		assertEquals(normalized2, Utils.normalizeName(toNormalize2));
		assertEquals(normalized3, Utils.normalizeName(toNormalize3));
		assertEquals(normalized1, Utils.normalizeName(normalized1));
	}
	
	
}
