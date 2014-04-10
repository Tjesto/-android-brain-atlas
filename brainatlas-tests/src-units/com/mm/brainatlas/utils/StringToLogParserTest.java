package com.mm.brainatlas.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringToLogParserTest {
	
	String TAG1, method1, result1, result2, TAG2, method2, result3, result4;
	String issues1, issues2;
	String[] issues;
	
	@Before
	public void setUp() {
		TAG1 = "TestLog";
		method1 = "TestMessage";
		TAG2 = "test.brainatlas.messaging.Testing";
		method2 = "anotherTestMessage";
		result1 = Utils.getNameFromTag(TAG1) + " during executing method " + method1;
		result2 = Utils.getNameFromTag(TAG1) + " during executing method "
				+ method1 + " other info:\n" + issues1 + ";\n" + issues2 + ";";
		result3 = Utils.getNameFromTag(TAG2) + " during executing method " + method2;
		result4 = Utils.getNameFromTag(TAG2) + " during executing method "
				+ method2 + " other info:\n" + issues1 + ";\n" + issues2 + ";";
		issues = new String[]{issues1, issues2};
	}
	
	@Test
	public void testParseForErrorLog() {
		String r1 = "An error occured in " + result1;
		String r2 = "An error occured in " + result2;
		String r3 = "An error occured in " + result3;
		String r4 = "An error occured in " + result4;
		
		
		assertEquals(r1, StringToLogParser.parseForErrorLog(TAG1, method1, (String[]) null));
		assertEquals(r3, StringToLogParser.parseForErrorLog(TAG2, method2, (String[]) null));
		assertEquals(r2, StringToLogParser.parseForErrorLog(TAG1, method1, issues));
		assertEquals(r4, StringToLogParser.parseForErrorLog(TAG2, method2, issues1, issues2));
		
		
	}

	@Test
	public void testParseForWarningLog() {
		String r1 = "Unusual situation in class " + result1;
		String r2 = "Unusual situation in class " + result2;
		String r3 = "Unusual situation in class " + result3;
		String r4 = "Unusual situation in class " + result4;
		
		assertEquals(r1, StringToLogParser.parseForWarningLog(TAG1, method1, (String[]) null));
		assertEquals(r3, StringToLogParser.parseForWarningLog(TAG2, method2, (String[]) null));
		assertEquals(r2, StringToLogParser.parseForWarningLog(TAG1, method1, issues));
		assertEquals(r4, StringToLogParser.parseForWarningLog(TAG2, method2, issues1, issues2));
	}

}
