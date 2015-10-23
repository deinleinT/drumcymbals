package org.wahlzeit.model;

public class CoordinateNullException extends Exception {
	
	public CoordinateNullException(){
		super("No Coordinates available. Use CoordinateFactory to get a new intance of an Coordinate-Object.");
	}

}
