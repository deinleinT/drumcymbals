package org.wahlzeit.model;

/**
 * Represents a Coordinate in decimal degree with latitude and longitude.
 * 
 * 
 * @author ThomasDeinlein
 *
 */

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * Only added for Testing correct creating of instances
	 */
	private boolean nullFlag;

	/**
	 * @methodtype default-constructor
	 */
	public AbstractCoordinate() {
	}

	/**
	 * 
	 * @return
	 * @methodtype get
	 */
	protected boolean getNullFlag() {
		return nullFlag;
	}

	/**
	 * @param isNull
	 * @methodtype set
	 */
	protected void setNullFlag(boolean isNull) {
		this.nullFlag = isNull;
	}

	/**
	 * Calculates the distances between two coordinates. Converts both
	 * Coordinates to CartesianCoordinates and calculates distance with
	 * Pythagoras.
	 * 
	 * @methodtype template
	 */
	public double getDistance(Coordinate other) throws NullCoordinateException {
		assertIsParameterNull(other);
		return calculateDistance(other);
	};

	/**
	 * Calculates the distance between two coordinates. Every Coordinate will be
	 * transformed to a CartesianCoordinate and then the distance will be
	 * calculated.
	 * 
	 * @methodtype helper
	 * @methodproperties hook
	 */
	protected abstract double calculateDistance(Coordinate one)
			throws IllegalArgumentException, NullCoordinateException;

	/**
	 * @return the Coordinate represented as CartesianCoordinate
	 * @throws NullCoordinateException
	 * @methodtype conversion Query
	 */
	protected abstract CartesianCoordinate asCartesianCoordinate() throws NullCoordinateException;

	/**
	 * @param other
	 *            the parameter, which shall be null-checked
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertIsParameterNull(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter is null. Invalid value.");
		}
	}

	/**
	 * Compares two coordinates. SphericCoordinate will be transformed to a
	 * CartesianCoordinate. If the x-, y-, z-values of both Coordinates are
	 * equal, the method returns true, else false.
	 * 
	 * @param other
	 * @return true, if both coordinates are equal by values, false otherwise
	 * @throws NullCoordinateException
	 * @methodtype comparison
	 * @methodproperties hook
	 */
	protected abstract boolean compareValues(Coordinate other) throws NullCoordinateException;

	@Override
	public boolean isEqual(Coordinate other) throws NullCoordinateException {

		AbstractCoordinate thisCoordinate = (AbstractCoordinate) this;
		AbstractCoordinate otherCoordinate = (AbstractCoordinate) other;

		return thisCoordinate.compareValues(otherCoordinate);

	}
}
