package org.wahlzeit.model.location.coordinate;

import org.wahlzeit.model.NullCoordinateException;

/**
 * The Coordinates of a Photo, with Latitude and Longitude.
 * 
 * Implemented as Multiton-Pattern
 * 
 * @author ThomasDeinlein
 * @version: 5.0
 * 
 * @Pattern (name = “Abstract Factory”   
 *           participants = { “AbstractProduct”, “ConcreteProduct”   } )
 *           
 * @Pattern ( name = "Multiton" )
 */
public class SphericCoordinate extends AbstractCoordinate {

	public static final double EARTH_RADIUS_IN_KM = 6371.0;

	private double radius = EARTH_RADIUS_IN_KM;
	private double latitude;
	private double longitude;

	/**
	 * 
	 * Constructor to set latitude and longitude in decimal-degree and radius in
	 * km.
	 * 
	 * @param latitude
	 * @param longitude
	 * @methodtype constructor
	 */
	protected SphericCoordinate(double latitude, double longitude, double radius) {

		assertDoubleNaN(latitude);
		assertDoubleNaN(longitude);
		assertDoubleNaN(radius);

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;

		assertClassInvariants();
	}

	/**
	 * @return value of latitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 */
	public double getLatitude() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return latitude;
	}

	/**
	 * @param latitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public SphericCoordinate setLatitude(double latitude) {

		// Preconditions
		assertDoubleNaN(latitude);
		assertIsLatitudeValueValid(latitude);

		SphericCoordinate result = (SphericCoordinate) getInstance(latitude, getLongitude(), getRadius());

		// Postconditions
		assertParameterNotNull(result);
		assertCreatedSphericCoordinate(result, latitude, getLongitude());
		assertClassInvariants();

		return result;
	}

	/**
	 * @return value of longitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 * 
	 */
	public double getLongitude() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return longitude;
	}

	/**
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public SphericCoordinate setLongitude(double longitude) {

		// Preconditions
		assertDoubleNaN(longitude);
		assertIsLongitudeValueValid(longitude);

		SphericCoordinate result = (SphericCoordinate) getInstance(getLatitude(), longitude, getRadius());

		// Postconditions
		assertParameterNotNull(result);
		assertCreatedSphericCoordinate(result, getLatitude(), longitude);
		assertClassInvariants();

		return result;
	}

	/**
	 * @methodtype get
	 */
	public double getRadius() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return radius;
	}

	/**
	 * @methodtype set
	 */
	public SphericCoordinate setRadius(double radius) {

		// Preconditions
		assertDoubleNaN(radius);
		assertRadiusIsPositive(radius);

		SphericCoordinate result = (SphericCoordinate) getInstance(getLatitude(), getLongitude(), radius);

		// Postconditions
		assertParameterNotNull(result);
		assertRadiusOfCreatedSphericCoordinate(result, radius);
		assertClassInvariants();

		return result;
	}

	/**
	 * Converts SphericCoordinates to xValue of CartesianCoordinate
	 * 
	 * return xValue
	 * 
	 * @methodtype conversion
	 * 
	 */
	@Override
	public double getXValue() {

		// Precondtions
		// none

		double x = this.getRadius() * Math.sin(Math.toRadians(this.getLongitude()))
				* Math.cos(Math.toRadians(this.getLatitude()));

		// Postconditions
		assertClassInvariants();

		return x;

	}

	/**
	 * Converts SphericCoordinates to yValue of CartesianCoordinate
	 * 
	 * return yValue
	 * 
	 * @methodtype conversion
	 * 
	 */
	@Override
	public double getYValue() {

		// Preconditions
		// none

		double y = this.getRadius() * Math.sin(Math.toRadians(this.getLongitude()))
				* Math.sin(Math.toRadians(this.getLatitude()));

		// Postconditions
		assertClassInvariants();

		return y;
	}

	/**
	 * Converts SphericCoordinates to zValue of CartesianCoordinate
	 * 
	 * return zValue
	 * 
	 * @methodtype conversion
	 * 
	 */
	@Override
	public double getZValue() {

		// Preconditions
		// none

		double z = this.getRadius() * Math.cos(Math.toRadians(this.getLongitude()));

		// Postconditions
		assertClassInvariants();

		return z;
	}

	/**
	 * @param other
	 *            the other Coordinate-Object
	 * @return distance between the latitude-values of both coordinates as
	 *         double-value
	 * 
	 *         Calculates other.latitude - this.latitude and returns the
	 *         absolute value of this value.
	 * 
	 */
	public double getLatitudinalDistance(SphericCoordinate other) throws IllegalArgumentException {

		// Precondition
		assertParameterNotNull(other);

		double result = Math.abs(other.getLatitude() - this.getLatitude());

		// Postcondition
		assertDistanceIsPositive(result);
		assertDoubleNaN(result);
		assertClassInvariants();

		return result;
	}

	/**
	 * @param other
	 *            the other Coordinate-Object
	 * @return distance between the latitude-values of both coordinates as
	 *         double-value
	 * 
	 *         Calculates other.longitude - this.longitude and returns the
	 *         absolute value of this value.
	 * 
	 */
	public double getLongitudinalDistance(SphericCoordinate other) throws IllegalArgumentException {

		// Precondition
		assertParameterNotNull(other);

		double result = Math.abs(other.getLongitude() - this.getLongitude());

		// Postcondition
		assertDistanceIsPositive(result);
		assertDoubleNaN(result);
		assertClassInvariants();

		return result;
	}

	/**
	 * Factory-Method to get an Instance of SphericCoordinate. In
	 * AbstractCoordinate is an attribute INSTANCES defined. This HashMap
	 * manages all created Coordinate Instances. This method checks first,
	 * whether a coordinate-Instance with the parameter-values has been already
	 * created. If it has been created, the Instance from the HashMap will be returned.
	 * Otherwise the privat Constructor will be executed an the new Instance will be saved in INSTANCES.
	 * 
	 * @methodtype factory
	 */
	static synchronized Coordinate getInstance(double latitude, double longitude, double radius) {

		// preconditions
		assertDoubleNaN(latitude);
		assertDoubleNaN(longitude);
		assertDoubleNaN(radius);

		String keyString = doCreateKeyString(latitude, longitude, radius, SphericCoordinate.class.getCanonicalName());
		SphericCoordinate result = (SphericCoordinate) INSTANCES.get(keyString);
		if (result == null) {
		
				result = (SphericCoordinate) INSTANCES.get(keyString);
				if (result == null) {
					result = new SphericCoordinate(latitude, longitude, radius);
					INSTANCES.put(keyString, result);
				}
			
		}

		// Postconditions
		assertParameterNotNull(result);
		assertINSTANCESNotEmpty(INSTANCES);

		return result;
	}

	/**
	 * Returns this SphericCoordinate represented as a CartesianCoordinate.
	 * Formula adapted from
	 * {@link http://keisan.casio.com/exec/system/1359534351#googlemap}
	 * 
	 * @return this SphericCoordinate represented as a CartesianCoordiante
	 * 
	 * @methodtype converter Query
	 */
	public CartesianCoordinate asCartesianCoordinate() {

		// Preconditions
		// none

		double lat = Math.toRadians(this.getLatitude());
		double lon = Math.toRadians(this.getLongitude());

		double x = this.getRadius() * Math.sin(lon) * Math.cos(lat);
		double y = this.getRadius() * Math.sin(lon) * Math.sin(lat);
		double z = this.getRadius() * Math.cos(lon);

		CartesianCoordinate result = CoordinateFactory.getInstance().getCartesianCoordinate(x, y, z);

		// Postconditions
		assertParameterIsInstanceOfCartesianCoordinate(result);
		assertClassInvariants();

		return result;
	}

	/**
	 * @return the distance between two sphericCoordinates in km
	 * @methodtype helper
	 * @methodtye primitive
	 */
	public double calculateDistanceBetweenTwoSphericCoordinates(SphericCoordinate other) {

		// Postconditions
		assertParameterNotNull(other);
		assertParameterIsInstanceOfSphericCoordinate(other);

		double lat1AsRadiant = Math.toRadians(this.getLatitude());
		double lat2AsRadiant = Math.toRadians(other.getLatitude());
		double deltaLongAsRadiant = Math.toRadians(this.getLongitudinalDistance(other));

		double distance = Math.sin(lat1AsRadiant) * Math.sin(lat2AsRadiant)
				+ Math.cos(lat1AsRadiant) * Math.cos(lat2AsRadiant) * Math.cos(deltaLongAsRadiant);

		double returnValue = this.getRadius() * Math.acos(distance);

		// Postconditions
		assertDistanceIsPositive(returnValue);
		assertDoubleNaN(returnValue);
		assertClassInvariants();

		return returnValue;
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		assertIsLatitudeValueValid(this.latitude);
		assertIsLongitudeValueValid(this.longitude);
		assertIsRadiusValueValid(this.radius);
		assertDoubleNaN(this.latitude);
		assertDoubleNaN(this.longitude);
		assertDoubleNaN(this.radius);
	}

	/**
	 * 
	 * @param latitude
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	protected static void assertIsLatitudeValueValid(double latitude) throws IllegalArgumentException {
		if (latitude > 90.0 || latitude < -90.0) {
			throw new IllegalArgumentException("Invalid Value. Latitue is between 0 - 90 or 0 - (-90) degrees.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperies primitive
	 */
	protected static void assertIsRadiusValueValid(double radius) throws IllegalArgumentException {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Invalid Value. Radius has to be positive.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperies primitive
	 */
	protected static void assertRadiusIsPositive(double radius) throws IllegalArgumentException {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Value of Radius not valid. Only positive Values are allowed.");
		}
	}

	/**
	 * Checks, whether the double-value for the longitude is valid. |
	 * 
	 * @param longitude
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	protected static void assertIsLongitudeValueValid(double longitude) {
		if (longitude > 180.0 || longitude <= -180.0) {
			throw new IllegalArgumentException("Invalid Value. Latitue is between 0 - 180 or 0 - (-180) degrees.");
		}
	}

	/**
	 * @param result
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected static void assertParameterIsInstanceOfCartesianCoordinate(CartesianCoordinate result) {
		if (!(result instanceof CartesianCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Cartesian Coordinate.");
		}
	}

	/**
	 * @param result
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected static void assertParameterIsInstanceOfSphericCoordinate(SphericCoordinate other) {
		if (!(other instanceof SphericCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Cartesian Coordinate.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertCreatedSphericCoordinate(SphericCoordinate result, double latitude, double longitude) {
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
	 * @methodproperty primitive
	 */
	protected static void assertRadiusOfCreatedSphericCoordinate(SphericCoordinate result, double radius) {
		if (result.getRadius() != radius) {
			throw new IllegalStateException("Error while creating SphericCoordinate. The Radius in the Coordinate is "
					+ result.getRadius() + ", the original radius-Value as parameter is " + radius + ".");
		}
	}

	/**
	 * @methodtype conversion
	 * @methodproperty primitive
	 */
	protected String asString() {
		
		//Preconditions
		//none

		String result = "SphericCoordinate with latitude: " + getLatitude() + ", longitude: " + getLongitude()
				+ " and radius: " + getRadius() + ".";

		//postconditions
		assertStringNotEmpty(result);
		assertParameterNotNull(result);
		
		return result;
	}

	@Override
	public int hashCode() {
		return asString().hashCode();
	}

}
