package com.abc.rangeorganiser;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.abc.rangeorganiser.ZipCodeRangeOrganiser;

/**
 * 
 * @author rakesh.kumar 
 * Test class for the ZipCodeRangeOrganiserTest
 */
public class ZipCodeRangeOrganiserTest {

	private ZipCodeRangeOrganiser classUnderTest = new ZipCodeRangeOrganiser();

	private Logger logger = Logger.getLogger(ZipCodeRangeOrganiserTest.class.getName());

	@Test
	public void testOrganiseZipCodeMergeExpected() {
		String input = "[94133,94133] [94200,94299] [94226,94399]";
		logger.info("Input string:" + input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:" + output);
		assertTrue(output.equals("[94133,94133] [94200,94399]"));
	}

	@Test
	public void testOrganiseZipCodeMeregeNotExpected() {
		String input = "[94133,94133] [94200,94299] [94600,94699]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue(output.equals(input));
	}
}
