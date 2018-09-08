package com.abc.rangeorganiser.exception;

/**
 * Custom exception class for invalid number range.
 * @author rakesh.kumar
 */
public class InvaliNumberRangeException extends Exception {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -4080288457487804155L;
	private final  String message ;
	public InvaliNumberRangeException(String exceptionMessage) {
		super();
		this.message = exceptionMessage;
	}
	public String getMessage() {
		return message;
	}
	
	

}
