package org.wahlzeit.model.location.coordinate;

/**
 * Class that represents a Cartesian Coordinate with x-, y- and z-value.
 * 
 * Implemented as Multiton-Pattern
 * 
 * @author ThomasDeinlein
 * @version: 5.0
 * 
 * @Pattern (name = “Abstract Factory”  
 *     participants = {“AbstractProduct”, “ConcreteProduct”   } )
 *
 * @Pattern ( name = "Multiton" )
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
	private CartesianCoordinate(double xValue, double yValue, double zValue) {

		assertDoubleNaN(xValue);
		assertDoubleNaN(yValue);
		assertDoubleNaN(zValue);

		this.xValue = xValue;
		this.yValue = yValue;
		this.zValue = zValue;

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
	 * Returns new CartesianCoordinate with previous y and z value and new
	 * xvalue
	 * 
	 * @methodtype set
	 */
	public CartesianCoordinate setXValue(double xValue) {

		// Preconditions
		assertDoubleNaN(xValue);

		CartesianCoordinate result = (CartesianCoordinate) getInstance(xValue, getYValue(), getZValue());

		// Postconditions
		assertParameterNotNull(result);
		assertClassInvariants();

		return result;
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
	 * 
	 * Returns new CartesianCoordinate with previous x and z value and new
	 * yvalue
	 * 
	 * @methodtype set
	 */
	public CartesianCoordinate setYValue(double yValue) {

		// Preconditions
		assertDoubleNaN(yValue);

		CartesianCoordinate result = (CartesianCoordinate) getInstance(getXValue(), yValue, getZValue());

		// Postconditions
		assertParameterNotNull(result);
		assertClassInvariants();

		return result;
	}

	/**
	 * @methodtype get
	 */
	public double getZValue() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return zValue;
	}

	/**
	 * Returns new CartesianCoordinate with previous x and y value and new
	 * zvalue
	 * 
	 * @methodtype set
	 */
	public CartesianCoordinate setZValue(double zValue) {

		// Preconditions
		assertDoubleNaN(zValue);

		CartesianCoordinate result = (CartesianCoordinate) getInstance(getXValue(), getYValue(), zValue);

		// Postconditions
		assertParameterNotNull(result);
		assertClassInvariants();

		return result;
	}

	/**
	 * Factory-Method to get an Instance of CartesianCoordinate. In
	 * AbstractCoordinate is an attribute INSTANCES defined. This HashMap
	 * manages all created Coordinate Instances. This method checks first,
	 * whether a coordinate-Instance with the parameter-values has been already
	 * created. If it has been created, the Instance from the HashMap will be
	 * returned. Otherwise the privat Constructor will be executed an the new
	 * Instance will be saved in INSTANCES.
	 * 
	 * @methodtype factory
	 */
	static synchronized Coordinate getInstance(double xValue, double yValue, double zValue) {

		// preconditions
		assertDoubleNaN(xValue);
		assertDoubleNaN(yValue);
		assertDoubleNaN(zValue);

		String keyString = doCreateKeyString(xValue, yValue, zValue, CartesianCoordinate.class.getCanonicalName());
		CartesianCoordinate result = (CartesianCoordinate) INSTANCES.get(keyString);
		if (result == null) {

			result = (CartesianCoordinate) INSTANCES.get(keyString);
			if (result == null) {
				result = new CartesianCoordinate(xValue, yValue, zValue);
				INSTANCES.put(keyString, result);
			}

		}

		// Postconditions
		assertParameterNotNull(result);
		assertINSTANCESNotEmpty(INSTANCES);

		return result;
	}

	/**
	 * Returns this CartesianCoordinate represented as a SphericCoordinate.
	 * Formula adapted from
	 * {@link http://keisan.casio.com/exec/system/1359533867}
	 * 
	 * @return this CartesianCoordinate represented as a SphericCoordinate
	 * 
	 * @methodtype convenience
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

		SphericCoordinate result = CoordinateFactory.getInstance().getSphericCoordinate(lat, lon, radius);

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
		assertParameterNotNull(INSTANCES);
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected static void assertParameterIsInstanceOfSphericCoordinate(SphericCoordinate other) {
		if (!(other instanceof SphericCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Spheric Coordinate.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected static void assertParameterIsInstanceOfCartesianCoordinate(CartesianCoordinate other) {
		if (!(other instanceof CartesianCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Cartesian Coordinate.");
		}
	}

	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 */
	protected String asString() {

		// preconditons
		// none

		String result = "CartesianCoordinate with xValue: " + getXValue() + ", yValue: " + getYValue()
				+ " and  zValue: " + getZValue() + ".";

		// postconditions
		assertParameterNotNull(result);
		assertStringNotEmpty(result);

		return result;
	}

	@Override
	public int hashCode() {
		return asString().hashCode();
	}

}
