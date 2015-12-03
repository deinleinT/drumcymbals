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
	 * @param latitude
	 * @param longitude
	 * @return a SphericCoordinate
	 * @throws IllegalArgumentException
	 *             if one parameter is null
	 * @methodtype factory
	 */
	public SphericCoordinate createSphericCoordinate(double latitude, double longitude) {

		// Preconditions
		// none, values of latitude and longitude will be checked through
		// instantiation in SphericCoordinate class

		SphericCoordinate result = new SphericCoordinate(latitude, longitude);

		// Postcondition
		assertCreatedSphericCoordinate(result, latitude, longitude);
		assertClassInvariants();

		return result;
	}

	/**
	 * Factory-Method to create a SphericCoordinate
	 * 
	 * @param latitude
	 * @param longitude
	 * @return a SphericCoordinate
	 * @throws IllegalArgumentException
	 *             if one parameter is null
	 * @methodtype factory
	 */
	public SphericCoordinate createSphericCoordinate(double latitude, double longitude, double radius) {

		// Preconditions
		// none, values of latitude, longitude and radius will be checked
		// through
		// instantiation in SphericCoordinate class

		SphericCoordinate result = new SphericCoordinate(latitude, longitude, radius);

		// postconditions
		assertCreatedSphericCoordinate(result, latitude, longitude);
		assertRadiusOfCreatedSphericCoordinate(result, radius);
		assertClassInvariants();

		return result;
	}

	/**
	 * @param x
	 *            xValue
	 * @param y
	 *            yValue
	 * @param z
	 *            zValue
	 * @return CartesianCoordinate
	 * @methodtype factory Helper
	 */
	public CartesianCoordinate createCartesianCoordinate(double x, double y, double z) {

		// Preconditions
		// none

		CartesianCoordinate result = new CartesianCoordinate(x, y, z);

		// Postconditions
		assertCreatedCartesianCoordinate(result, x, y, z);
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
	protected void assertCreatedCartesianCoordinate(CartesianCoordinate result, double x, double y, double z) {
		if (result.getXValue() != x) {
			throw new IllegalStateException(
					"Error while creating CartesianCoordinate. The X-Value in the Coordinate is " + result.getXValue()
							+ ", the original X-Value as parameter is " + x + ".");
		}

		if (result.getYValue() != y) {
			throw new IllegalStateException(
					"Error while creating CartesianCoordinate. The Y-Value in the Coordinate is " + result.getYValue()
							+ ", the original Y-Value as parameter is " + y + ".");
		}

		if (result.getZValue() != z) {
			throw new IllegalStateException(
					"Error while creating CartesianCoordinate. The Z-Value in the Coordinate is " + result.getZValue()
							+ ", the original Z-Value as parameter is " + z + ".");
		}
	}

}
