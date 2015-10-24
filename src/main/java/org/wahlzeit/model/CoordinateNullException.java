package org.wahlzeit.model;

public class CoordinateNullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoordinateNullException(){
		super("No Coordinates available. Use CoordinateFactory to get a new intance of a Coordinate-Object.");
	}

}
