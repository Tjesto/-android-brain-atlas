package com.mm.brainatlas;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mm.brainatlas.data.impl.SourceInfo;
import com.mm.brainatlas.utils.Pair;

public class SourceInfoFactoryTest {

	@Test
	public void testCreateForTest() {		
		int arguments = 0;
		Pair<SourceInfo, Integer> result = SourceInfoFactory.createForTest("TestData", 1);
		SourceInfo info = result.firstElement;
		arguments = result.secondElement;
		assertNotNull("Expected not null, received null", info);
		assertEquals("TestData", info.getTitleString());
		assertEquals(1, arguments);
		result = SourceInfoFactory.createForTest("TestData", 0);
		info = result.firstElement;
		arguments = result.secondElement;
		assertNull("Expected null recived not null",info);		
	}

}
