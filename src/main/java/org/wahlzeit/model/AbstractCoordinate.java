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

		double differenceOfXPow2 = Math.pow((this.getXValue() - other.getXValue()), 2);
		double differenceOfYPow2 = Math.pow((this.getYValue() - other.getYValue()), 2);
		double differenceOfZPow2 = Math.pow((this.getZValue() - other.getZValue()), 2);

		return Math.sqrt(differenceOfXPow2 + differenceOfYPow2 + differenceOfZPow2);
	};

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

	@Override
	public boolean isEqual(Coordinate other) throws NullCoordinateException {

		double delta = 0.3;

		if (Math.abs(this.getXValue() - other.getXValue()) < delta
				&& Math.abs(this.getYValue() - other.getYValue()) < delta
				&& Math.abs(this.getZValue() - other.getZValue()) < delta) {
			return true;
		} else {
			return false;
		}

	}
}
