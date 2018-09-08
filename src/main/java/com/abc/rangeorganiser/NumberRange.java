package com.abc.rangeorganiser;

/**
 * 
 * @author rakesh.kumar
 * Class representing an individual range of numbers
 */
public class NumberRange{

	private Integer upperLimit;
	private Integer lowerLimit;
	
	// Default constructor
	public NumberRange() {
		
	}
	
	// Parameterized constructor
	public NumberRange(Integer lowerLimit, Integer upperLimit) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}
	
	public int getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(final Integer upperLimit) {
		this.upperLimit = upperLimit;
	}
	public Integer getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(final Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

    @Override
    public String toString() {
        return  "[" + lowerLimit.intValue() + "," + upperLimit.intValue() + "]";
    }
}
