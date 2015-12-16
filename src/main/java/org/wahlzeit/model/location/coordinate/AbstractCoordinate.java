package org.wahlzeit.model.location.coordinate;

import java.util.HashMap;

import org.wahlzeit.model.NullCoordinateException;

/**
 * Represents a Coordinate in decimal degree with latitude and longitude.
 * 
 * Implemented as Multiton-Pattern
 * 
 * 
 * @author ThomasDeinlein
 * @version: 5.0
 * 
 */

public abstract class AbstractCoordinate implements Coordinate {

	protected static final HashMap<String, Coordinate> INSTANCES = new HashMap<String, Coordinate>();

	/**
	 * Method to get Coordinate-Instances from the INSTANCES-Hashmap. If this
	 * method is not overwritten in subclasses and executed, an Exception will be thrown.
	 * 
	 * @methodtype command
	 */
	static synchronized Coordinate getInstance(double paramOne, double paramTwo, double paramThree) {
		throw new IllegalStateException("GetInstance Method not implemented yet.");
	}

	/**
	 * Is used in subclasse within the getInstance-method to create an unique
	 * keyString. The keyString is used to save a coordinate-instance in
	 * INSTANCES HashMap.
	 * 
	 * @methodtype factory
	 * @methodproperty primitive
	 */
	protected static String doCreateKeyString(double paramOne, double paramTwo, double paramThree, String className) {

		// Preconditions
		assertDoubleNaN(paramOne);
		assertDoubleNaN(paramTwo);
		assertDoubleNaN(paramThree);
		assertParameterNotNull(className);

		String result = "" + paramOne + " " + paramTwo + " " + paramThree + " " + className;

		// Postconditions
		assertParameterNotNull(result);
		assertStringNotEmpty(result);

		return result;
	}

	/**
	 * Calculates the distances between two coordinates. Converts both
	 * Coordinates to CartesianCoordinates and calculates distance with
	 * Pythagoras.
	 * 
	 * @methodtype template
	 */
	public double getDistance(Coordinate other) throws NullCoordinateException {

		// Preconditions
		assertParameterNotNull(other);

		double differenceOfXPow2 = Math.pow((this.getXValue() - other.getXValue()), 2);
		double differenceOfYPow2 = Math.pow((this.getYValue() - other.getYValue()), 2);
		double differenceOfZPow2 = Math.pow((this.getZValue() - other.getZValue()), 2);
		double result = Math.sqrt(differenceOfXPow2 + differenceOfYPow2 + differenceOfZPow2);

		// Postconditions
		assertDistanceIsPositive(result);
		assertClassInvariants();

		return result;
	};

	// checks the equality with comparing all values(e.g. xValues)
	// with each other (e.g. this.xvalue - other.xvalue)
	// if all values do not differ about 0.3 from each other, true
	// will be returned
	@Override
	public boolean isEqual(Coordinate other) throws NullCoordinateException {

		// preconditions
		assertParameterNotNull(other);

		double delta = 0.3;
		boolean result = false;

		double thisXValue = this.getXValue();
		double thisYValue = this.getYValue();
		double thisZValue = this.getZValue();

		double otherXValue = other.getXValue();
		double otherYValue = other.getYValue();
		double otherZValue = other.getZValue();

		if (Math.abs(thisXValue - otherXValue) < delta && Math.abs(thisYValue - otherYValue) < delta
				&& Math.abs(thisZValue - otherZValue) < delta) {
			result = true;
		}

		// postconditions
		assertClassInvariants();

		return result;

	}

	/**
	 * Checks if this == other
	 * 
	 * @return true, if both objects references same object
	 * 
	 * @methodtype comparison
	 */
	public boolean isSame(Object other) {

		// preconditions
		assertParameterNotNull(other);

		boolean result = false;
		if (this == other) {
			result = true;
		}

		// Postconditions
		assertClassInvariants();

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		//Preconditions
		assertParameterNotNull(obj);
		
		if (this.isSame(obj)) {
			return true;
		} else if (obj instanceof Coordinate) {
			try {
				return this.isEqual((Coordinate) obj);
			} catch (NullCoordinateException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks ClassInvariants throws an IllegalStateException if Invariants are
	 * invalid
	 * 
	 * @methodtype assert
	 * @methodproperty primitive
	 */
	protected abstract void assertClassInvariants() throws IllegalStateException;

	/**
	 * @param other
	 *            the parameter, which shall be null-checked
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertParameterNotNull(Object other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter is null. Invalid value.");
		}
	}

	/**
	 * @Methodtype assertion
	 * @Methoproperty primitive
	 */
	protected void assertDistanceIsPositive(double result) {
		if (result < 0) {
			throw new IllegalArgumentException("Error. Calculated Distance is negativ.");
		}
	}

	/**
	 * @Methodtype assertion
	 * @Methoproperty primitive
	 */
	protected static void assertDoubleNaN(double value) {
		if (Double.isNaN(value)) {
			throw new NumberFormatException("Error. Not a doubleValue. The Value is NaN.");
		}
	}

	/**
	 * @Methodtype assertion
	 * @Methoproperty primitive
	 */
	protected static void assertStringNotEmpty(String string) {
		if (string.isEmpty()) {
			throw new IllegalArgumentException("String may not be empty.");
		}
	}
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertINSTANCESNotEmpty(HashMap<String, Coordinate> instances) {
		if (INSTANCES.isEmpty()) {
			throw new IllegalStateException("There are no Coordinates in INSTANCES saved! Wrong state.");
		}
	}
}
