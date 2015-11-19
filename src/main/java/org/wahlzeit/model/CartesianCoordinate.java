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

}
