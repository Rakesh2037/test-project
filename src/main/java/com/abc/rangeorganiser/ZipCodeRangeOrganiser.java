package com.abc.rangeorganiser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.abc.rangeorganiser.exception.InvaliNumberRangeException;

/**
 *         Problem Statement: Given a collection of 5-digit ZIP code ranges
 *         (each range includes both their upper and lower bounds), provide an
 *         algorithm that produces the minimum number of ranges required to
 *         represent the same restrictions as the input. If the input is
 *         [94133,94133] [94200,94299] [94600,94699] then The output should be
 *         [94133,94133] [94200,94299] [94600,94699] If the input is
 *         [94133,94133] [94200,94299] [94226,94399] then The output should be 
 *         [94133,94133] [94200,94399]
 *         NOTES: The ranges above are just examples, implementation should
 *         work for any set of arbitrary ranges. Ranges may be provided in
 *         arbitrary order, Ranges may or may not overlap.
 *         
 *@author rakesh.kumar
 */
public class ZipCodeRangeOrganiser {

	private static final String INVALID_NUMBER_RANGE = "Invalid Number Range";
	private static final String INVALID_INPUT = "Invalid Input";
	private Logger logger = Logger.getLogger(ZipCodeRangeOrganiser.class.getName());

	/**
	 * Method to process ZipCodeRanges
	 * @param zipCodeStrings as String
	 * @return List of NumberRanges
	 */
	public String processZipCodeRanges(String zipCodeStrings) {
		logger.info("Incoming zipCodeStrings:"+zipCodeStrings);
	
		// Essential null check for incoming zipCodestring
		if(null != zipCodeStrings && !"".equals(zipCodeStrings.trim())) {
			List<NumberRange> zipCodeRanges = new ArrayList<>();
			
			// Pattern allowing 0 or more number of characters contained in []
			Pattern textPattern = Pattern.compile("\\[(.*?)\\]");
			Matcher matcher = textPattern.matcher(zipCodeStrings);
			NumberRange numberRange;
			while (matcher.find()) { 
				String matchedGroup = matcher.group(1);
				String [] lowerUpperLimitValues = matchedGroup.split(",");
				
				// As parsing of non-numeric value can throw exception
				try {
					Integer lowerLimit = Integer.parseInt(lowerUpperLimitValues[0]);
					Integer upperLimit = Integer.parseInt(lowerUpperLimitValues[1]);
					
					numberRange = new NumberRange();
					// Business Rule: As it is given that ZIP code of 5 digits only
					if (lowerLimit < 10000 || lowerLimit > 99999  ) {
						logger.error(INVALID_INPUT);
						throw new InvaliNumberRangeException(INVALID_NUMBER_RANGE);
					} else {
						numberRange.setLowerLimit(lowerLimit);
					}
					if (upperLimit < 10000 || upperLimit > 99999) {
						logger.error(INVALID_INPUT);
						throw new InvaliNumberRangeException(INVALID_NUMBER_RANGE);
					} else {
						numberRange.setUpperLimit(upperLimit);
					}
					zipCodeRanges.add(numberRange);
				} catch (NumberFormatException | InvaliNumberRangeException e) {
					logger.error(INVALID_INPUT + matchedGroup+ ","+ e.getMessage());
					/*
					 * Can throw NumberFormatException if we want to break the processing even if a single
					 * invalid input found, currently ignoring these values and continuing to next iteration.
					 */
					continue;
				}
			}
			if (zipCodeRanges.isEmpty()) {
				logger.error(INVALID_INPUT);
			}
			List<NumberRange> organisedNumberRanges = organiseRanges(zipCodeRanges);
			return getAsString(organisedNumberRanges);
		}else {
			return "";
		}
		
	}
	
	/**
	 * To return string of test extracted form list of NumberRange
	 * @param organisedNumberRanges
	 * @return String 
	 */
	private String getAsString(final List<NumberRange> organisedNumberRanges) {
		StringBuilder builder = new StringBuilder();
		for (NumberRange nr : organisedNumberRanges) {
			builder.append(nr.toString()+" ");
		}
		// trim used as at the end there will be an empty space as added above.
		return builder.toString().trim(); 
	}

	/**
	 * Method to organize the number ranges
	 * @param numberRanges as List of NumberRange
	 * @return List of Number Ranges
	 */
	private List<NumberRange> organiseRanges(final List<NumberRange> numberRanges) {
		if (numberRanges.isEmpty()) {
			return numberRanges;
		}
		logger.info("Printing incoming string by iteration objects list:");
		for (NumberRange nr : numberRanges) {
			logger.info(nr.toString());
		}
		// Sorting of NumberRange objects in the list
		numberRanges.sort(
				(final NumberRange nr1, final NumberRange nr2) -> (nr1.getLowerLimit()).compareTo(nr2.getLowerLimit()));

		final ArrayList<NumberRange> organisedRangeList = new ArrayList<>();
		NumberRange firstNumberRange = numberRanges.get(0);
		for (NumberRange numberRange : numberRanges) {
			NumberRange nextNumberRange = numberRange;
			if (firstNumberRange.getUpperLimit() >= nextNumberRange.getLowerLimit()) {
				NumberRange newNumberange = new NumberRange(firstNumberRange.getLowerLimit(),
						Math.max(firstNumberRange.getUpperLimit(), nextNumberRange.getUpperLimit()));
				firstNumberRange = newNumberange;
			} else {
				organisedRangeList.add(firstNumberRange);
				firstNumberRange = nextNumberRange;
			}
		}
		organisedRangeList.add(firstNumberRange);
		
		return organisedRangeList;
	}
}
