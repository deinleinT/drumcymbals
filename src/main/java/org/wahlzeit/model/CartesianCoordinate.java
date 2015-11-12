package org.wahlzeit.model;

/**
 * Class that represents a Cartesian Coordinate with x-, y- and z-value.
 * 
 * @author ThomasDeinlein
 *
 */

public class CartesianCoordinate extends AbstractCoordinate {

	private double xValue;
	private double yValue;
	private double zValue;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
	}

	/**
	 * @param xValue
	 * @param yValue
	 * @param zValue
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double xValue, double yValue, double zValue) {
		this.xValue = xValue;
		this.yValue = yValue;
		this.zValue = zValue;
	}

	/**
	 * @methodtype get
	 */
	public double getXValue() {
		return xValue;
	}

	/**
	 * @methodtype set
	 */
	public void setXValue(double xValue) {
		this.xValue = xValue;
	}

	/**
	 * @methodtype get
	 */
	public double getYValue() {
		return yValue;
	}

	/**
	 * @methodtype set
	 */
	public void setYValue(double yValue) {
		this.yValue = yValue;
	}

	/**
	 * @methodtype get
	 */
	public double getZValue() {
		return zValue;
	}

	/**
	 * @methodtype set
	 */
	public void setZValue(double zValue) {
		this.zValue = zValue;
	}

	/**
	 * @throws NullCoordinateException
	 * @methodtype comparison
	 * @methodproperties primitive
	 */
	@Override
	protected boolean compareValues(Coordinate otherCoordinate) throws NullCoordinateException {

		CartesianCoordinate other = ((AbstractCoordinate) otherCoordinate).asCartesianCoordinate();

		double delta = 0.3;

		if (Math.abs(this.getXValue() - other.getXValue()) < delta
				&& Math.abs(this.getYValue() - other.getYValue()) < delta
				&& Math.abs(this.getZValue() - other.getZValue()) < delta) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns this CartesianCoordinate represented as a SphericCoordinate.
	 * Formula adapted from
	 * {@link http://keisan.casio.com/exec/system/1359533867}
	 * 
	 * @return this CartesianCoordinate represented as a SphericCoordinate
	 * 
	 * @methodtype helper
	 * @methodproperties hook, convenience
	 */
	public SphericCoordinate asSphericCoordinate() {

		double radius = Math.sqrt(this.getXValue() * this.getXValue() + this.getYValue() * this.getYValue()
				+ this.getZValue() * this.getZValue());

		// THETA
		Double lat = Math.toDegrees(Math.atan(this.getYValue() / this.getXValue()));

		// PHI
		Double lon = Math.toDegrees(
				Math.atan((Math.sqrt(this.getXValue() * this.getXValue() + this.getYValue() * this.getYValue()))
						/ this.getZValue()));

		if (lat.isNaN()) {
			lat = 0.0;
		}
		if (lon.isNaN()) {
			lon = 0.0;
		}

		return CoordinateFactory.getInstance().createSphericCoordinate(lat, lon, radius);

	}

	/**
	 * @return the distance between two sphericCoordinates in km
	 * @methodtype helper
	 * @methodtye primitive
	 */
	public double calculateDistanceBetweenTwoCartesianCoordinates(CartesianCoordinate other) {

		double differenceOfXPow2 = Math.pow((this.getXValue() - other.getXValue()), 2);
		double differenceOfYPow2 = Math.pow((this.getYValue() - other.getYValue()), 2);
		double differenceOfZPow2 = Math.pow((this.getZValue() - other.getZValue()), 2);

		return Math.sqrt(differenceOfXPow2 + differenceOfYPow2 + differenceOfZPow2);
	}

	/**
	 * @methodtype helper
	 * @methodproperties hook
	 */
	@Override
	protected double calculateDistance(Coordinate othercoordinate)
			throws IllegalArgumentException, NullCoordinateException {

		CartesianCoordinate other = ((AbstractCoordinate) othercoordinate).asCartesianCoordinate();

		return this.calculateDistanceBetweenTwoCartesianCoordinates(other);
	}

	@Override
	protected CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

}
