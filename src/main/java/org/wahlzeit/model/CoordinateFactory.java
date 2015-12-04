package org.wahlzeit.model;

/**
 * Factory to get a correct instance of a Coordinate
 * 
 * @author ThomasDeinlein
 * 
 * * @Pattern (
 *   name = “Abstract Factory and Factory Method ”
 *   participants = {
 *      “AbstractFactory”, 
 *     “ConcreteFactory”   } )
 *
 */
public class CoordinateFactory {

	private static CoordinateFactory singleton = new CoordinateFactory();

	/**
	 * @methodtype private default-constructor for singleton-implementation
	 */
	private CoordinateFactory() {
		// no assertClassInvariants because of private constructor
	}

	/**
	 * @return Singleton-Instance of CoordinateFactory
	 * 
	 * @methodtype factory
	 */
	public static synchronized CoordinateFactory getInstance() {

		// Preconditions
		// none

		if (singleton == null) {
			singleton = new CoordinateFactory();
		}

		// Postconditions
		assertClassInvariants();

		return singleton;
	}

	/**
	 * @methodtype initialization
	 */
	public static synchronized void initialize() {

		// preconditions
		// none

		getInstance();

		// Postconditions
		assertClassInvariants();
	}

	/**
	 * Factory-Method to create a SphericCoordinate with default-value for
	 * earth-radius (6371 km).
	 * 
	 * @methodtype factory
	 */
	public SphericCoordinate createSphericCoordinate(double latitude, double longitude) {

		// Preconditions
		assertDoubleNaN(latitude);
		assertDoubleNaN(longitude);

		SphericCoordinate result = new SphericCoordinate(latitude, longitude);

		// Postcondition
		assertCreatedSphericCoordinate(result, latitude, longitude);
		assertClassInvariants();

		return result;
	}

	/**
	 * Factory-Method to create a SphericCoordinate
	 * 
	 * @methodtype factory
	 */
	public SphericCoordinate createSphericCoordinate(double latitude, double longitude, double radius) {

		// Preconditions
		assertDoubleNaN(latitude);
		assertDoubleNaN(longitude);
		assertDoubleNaN(radius);

		SphericCoordinate result = new SphericCoordinate(latitude, longitude, radius);

		// postconditions
		assertCreatedSphericCoordinate(result, latitude, longitude);
		assertRadiusOfCreatedSphericCoordinate(result, radius);
		assertClassInvariants();

		return result;
	}

	/**
	 * @methodtype factory Helper
	 */
	public CartesianCoordinate createCartesianCoordinate(double xValue, double yValue, double zValue) {

		// Preconditions
		assertDoubleNaN(xValue);
		assertDoubleNaN(yValue);
		assertDoubleNaN(zValue);

		CartesianCoordinate result = new CartesianCoordinate(xValue, yValue, zValue);

		// Postconditions
		assertCreatedCartesianCoordinate(result, xValue, yValue, zValue);
		assertClassInvariants();

		return result;
	}

	/**
	 * Factory-method to create NullCoordinate
	 * 
	 * @return AbstractCoordinate-Object, if one parameter is null, this method
	 *         returns a CoordinateNull-Object (NullObject), if both parameter
	 *         are double-values, this method returns a Coordinate-Object
	 */
	public NullCoordinate createNullCoordinate() {

		// Preconditions
		// none

		NullCoordinate result = NullCoordinate.getInstance();

		// Postconditions
		assertCreatedNullCoordinate(result);
		assertClassInvariants();

		return result;
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertCreatedNullCoordinate(NullCoordinate result) {
		if (!(result instanceof NullCoordinate)) {
			throw new IllegalStateException("Error while creating NullCoordinate.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertClassInvariants() {
		if (singleton == null) {
			throw new IllegalStateException();
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertCreatedSphericCoordinate(SphericCoordinate result, double latitude, double longitude) {
		if (result.getLatitude() != latitude) {
			throw new IllegalStateException("Error while creating SphericCoordinate. The Latitude in the Coordinate is "
					+ result.getLatitude() + ", the original Latitude-Value as parameter is " + latitude + ".");
		}

		if (result.getLongitude() != longitude) {
			throw new IllegalStateException(
					"Error while creating SphericCoordinate. The Longitude in the Coordinate is "
							+ result.getLongitude() + ", the original Longitude-Value as parameter is " + longitude
							+ ".");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty
	 */
	protected void assertRadiusOfCreatedSphericCoordinate(SphericCoordinate result, double radius) {
		if (result.getRadius() != radius) {
			throw new IllegalStateException("Error while creating SphericCoordinate. The Radius in the Coordinate is "
					+ result.getRadius() + ", the original radius-Value as parameter is " + radius + ".");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertCreatedCartesianCoordinate(CartesianCoordinate result, double xValue, double yValue, double zValue) {
		if (result.getXValue() != xValue) {
			throw new IllegalStateException(
					"Error while creating CartesianCoordinate. The X-Value in the Coordinate is " + result.getXValue()
							+ ", the original X-Value as parameter is " + xValue + ".");
		}

		if (result.getYValue() != yValue) {
			throw new IllegalStateException(
					"Error while creating CartesianCoordinate. The Y-Value in the Coordinate is " + result.getYValue()
							+ ", the original Y-Value as parameter is " + yValue + ".");
		}

		if (result.getZValue() != zValue) {
			throw new IllegalStateException(
					"Error while creating CartesianCoordinate. The Z-Value in the Coordinate is " + result.getZValue()
							+ ", the original Z-Value as parameter is " + zValue + ".");
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
