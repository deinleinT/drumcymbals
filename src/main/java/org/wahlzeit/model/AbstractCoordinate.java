package org.wahlzeit.model;

/**
 * Represents a Coordinate in decimal degree with latitude and longitude.
 * 
 * 
 * @author ThomasDeinlein
 * @version: 3.0
 * 
 */

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * @methodtype default-constructor
	 */
	public AbstractCoordinate() {
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

	@Override
	public boolean isEqual(Coordinate other) throws NullCoordinateException {

		// preconditions
		assertParameterNotNull(other);

		double delta = 0.3;
		boolean result = false;

		if (Math.abs(this.getXValue() - other.getXValue()) < delta
				&& Math.abs(this.getYValue() - other.getYValue()) < delta
				&& Math.abs(this.getZValue() - other.getZValue()) < delta) {
			result = true;
		}

		// postconditions
		assertClassInvariants();

		return result;

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
	protected void assertParameterNotNull(Coordinate other) {
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
	protected void assertDoubleNaN(double value) {
		if (Double.isNaN(value)) {
			throw new NumberFormatException("Error. Not a doubleValue. The Value is NaN.");
		}
	}
}
