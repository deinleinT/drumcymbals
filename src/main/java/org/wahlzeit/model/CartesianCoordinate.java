package org.wahlzeit.model;

/**
 * Class that represents a Cartesian Coordinate with x-, y- and z-value.
 * 
 * @author ThomasDeinlein
 * 
 * * @Pattern (
 *   name = “Abstract Factory”
 *   participants = {
 *      “AbstractProduct”, 
 *     “ConcreteProduct”   } )
 *
 */

public class CartesianCoordinate extends AbstractCoordinate {

	private double xValue;
	private double yValue;
	private double zValue;

	/**
	 * @param xValue
	 * @param yValue
	 * @param zValue
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double xValue, double yValue, double zValue) {
		
		//Using setter, preconditions will be checked automatically
		setXValue(xValue);
		setYValue(yValue);
		setZValue(zValue);
		
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getXValue() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return xValue;
	}

	/**
	 * @methodtype set
	 */
	public void setXValue(double xValue) {

		// Preconditions
		assertDoubleNaN(xValue);

		this.xValue = xValue;

		// Postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getYValue() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return yValue;
	}

	/**
	 * @methodtype set
	 */
	public void setYValue(double yValue) {

		// Preconditions
		assertDoubleNaN(yValue);

		this.yValue = yValue;

		// Postconditions
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getZValue() {

		// Preconditions
		//none

		// Postconditions
		assertClassInvariants();

		return zValue;
	}

	/**
	 * @methodtype set
	 */
	public void setZValue(double zValue) {

		// Preconditions
		assertDoubleNaN(zValue);

		this.zValue = zValue;

		// Postconditions
		assertClassInvariants();
	}

	/**
	 * Returns this CartesianCoordinate represented as a SphericCoordinate.
	 * Formula adapted from
	 * {@link http://keisan.casio.com/exec/system/1359533867}
	 * 
	 * @return this CartesianCoordinate represented as a SphericCoordinate
	 * 
	 * @methodtype convenience
	 * @methodproperties hook, convenience
	 */
	public SphericCoordinate asSphericCoordinate() {

		// Preconditions
		// none

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

		SphericCoordinate result = CoordinateFactory.getInstance().createSphericCoordinate(lat, lon, radius);

		// Postconditions
		assertParameterIsInstanceOfSphericCoordinate(result);
		assertClassInvariants();

		return result;

	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitve
	 */
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		assertDoubleNaN(this.xValue);
		assertDoubleNaN(this.yValue);
		assertDoubleNaN(this.zValue);
	}

	/**
	 * @param result
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected void assertParameterIsInstanceOfSphericCoordinate(SphericCoordinate other) {
		if (!(other instanceof SphericCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Speric Coordinate.");
		}
	}

}
