package com.abc.rangeorganiser;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.abc.rangeorganiser.ZipCodeRangeOrganiser;

/**
 * Test class for the ZipCodeRangeOrganiser
 * @author rakesh.kumar 
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
		assertTrue("[94133,94133] [94200,94399]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeMeregeExpected2() {
		String input = "[12000,12000] [12200,12399] [12300,12333]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[12000,12000] [12200,12399]".equals(output));
	}

	@Test
	public void testOrganiseZipCodeMeregeNotExpected() {
		String input = "[94133,94133] [94200,94299] [94600,94699]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue(input.equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeMeregeNotExpected2() {
		String input = "[74200,74299] [74600,74699] [74133,74133]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		// As sorting is done so it is expected to have ordering
		assertTrue("[74133,74133] [74200,74299] [74600,74699]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange() {
		String input = "[94133,94133] [94200,94299] [abcde,94699]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94133] [94200,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange2() {
		String input = "[94133,94299] [abc,94299] [123,679]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange3() {
		String input = "[94133,94299] [111111,999] [222,666666]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange4() {
		String input = "[94133,94299] [94200,xyz] [123,679]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange5() {
		String input = "[94133,94299] [94200,xyz] [123,679]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange6() {
		String input = "[94133,94299] [11111,999999] [11111,999]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange7() {
		String input = "[94133,94299] [111111,999] [222,666666]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("[94133,94299]".equals(output));
	}
	
	@Test
	public void testOrganiseZipCodeIgnoreInvalidInputRange8() {
		String input = "[94200,xyz] [123,679]";
		logger.info("Input string:"+input);
		String output = classUnderTest.processZipCodeRanges(input);
		logger.info("Input string:"+output);
		assertTrue("".equals(output));
	}
}
