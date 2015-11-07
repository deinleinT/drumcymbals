package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index = true)
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

	@Override
	public boolean isEqual(Coordinate other) {

		if (other instanceof NullCoordinate) {
			return false;
		} else if (other == null) {
			return false;
		} else if (other instanceof SphericCoordinate) {
			CartesianCoordinate otherCoordinate = ((SphericCoordinate) other).asCartesianCoordinate();
			return compareValues(this, otherCoordinate);
		} else if (other instanceof CartesianCoordinate) {
			return compareValues(this, (CartesianCoordinate) other);
		} else {
			return false;
		}

	}

	/**
	 * @methodtype comparison
	 * @methodproperties primitive
	 */
	private boolean compareValues(CartesianCoordinate cartesianCoordinate, CartesianCoordinate otherCoordinate) {

		double delta = 0.3;

		if (Math.abs(this.getXValue() - otherCoordinate.getXValue()) < delta
				&& Math.abs(this.getYValue() - otherCoordinate.getYValue()) < delta
				&& Math.abs(this.getZValue() - otherCoordinate.getZValue()) < delta) {
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

}
