package org.wahlzeit.model;

public class NullCoordinateException extends Exception {

	/**
	 * This Exception is thrown, when RealCoordinate-Method is executed on a
	 * NullCoordinate-Object
	 */
	private static final long serialVersionUID = 1L;

	public NullCoordinateException() {
		super("No Coordinates available. Use CoordinateFactory to get a new intance of a Coordinate-Object.");
	}

	public NullCoordinateException(String message) {
		super(message);
	}
}
