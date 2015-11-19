package org.wahlzeit.model;

/**
 * The Coordinates of a Photo, with Latitude and Longitude.
 * 
 * @author ThomasDeinlein
 *
 */

public class SphericCoordinate extends AbstractCoordinate {

	private static final double EARTH_RADIUS_IN_KM = 6371.0;

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
	public SphericCoordinate(double latitude, double longitude, double radius) {
		assertIsLatitudeValueValid(latitude);
		assertIsLongitudeValueValid(longitude);
		assertIsRadiusValueValid(radius);

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}

	/**
	 * Constructor to set latitude and longitude in decimal-degree. The Radius
	 * will be set to default-value of 6371 km (earth radius).
	 * 
	 * @param latitude
	 * @param longitude
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude) {
		assertIsLatitudeValueValid(latitude);
		assertIsLongitudeValueValid(longitude);
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = EARTH_RADIUS_IN_KM;
	}

	/**
	 * @return value of latitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setLatitude(double latitude) {
		assertIsLatitudeValueValid(latitude);
		this.latitude = latitude;
	}

	/**
	 * @return value of longitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 * 
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 * @throws NullCoordinateException
	 *             if this method is execute on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setLongitude(double longitude) {
		assertIsLongitudeValueValid(longitude);
		this.longitude = longitude;
	}

	/**
	 * @return radius
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 * @methodtype set
	 */
	public void setRadius(double radius) {
		assertRadiusIsPositive(radius);
		this.radius = radius;
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
		assertIsParameterNull(other);
		return Math.abs(other.getLatitude() - this.getLatitude());
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
		assertIsParameterNull(other);
		return Math.abs(other.getLongitude() - this.getLongitude());
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

		double lat = Math.toRadians(this.getLatitude());
		double lon = Math.toRadians(this.getLongitude());

		double x = this.getRadius() * Math.sin(lon) * Math.cos(lat);
		double y = this.getRadius() * Math.sin(lon) * Math.sin(lat);
		double z = this.getRadius() * Math.cos(lon);

		return CoordinateFactory.getInstance().createCartesianCoordinate(x, y, z);
	}

	/**
	 * @return the distance between two sphericCoordinates in km
	 * @methodtype helper
	 * @methodtye primitive
	 */
	public double calculateDistanceBetweenTwoSphericCoordinates(SphericCoordinate other) {

		double lat1AsRadiant = Math.toRadians(this.getLatitude());
		double lat2AsRadiant = Math.toRadians(other.getLatitude());
		double deltaLongAsRadiant = Math.toRadians(this.getLongitudinalDistance(other));

		double distance = Math.sin(lat1AsRadiant) * Math.sin(lat2AsRadiant)
				+ Math.cos(lat1AsRadiant) * Math.cos(lat2AsRadiant) * Math.cos(deltaLongAsRadiant);

		double returnValue = this.getRadius() * Math.acos(distance);

		return returnValue;
	}

	/**
	 * Checks, whether the double-value for the longitude is valid. |
	 * 
	 * @param longitude
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	private void assertIsLongitudeValueValid(double longitude) {
		if (longitude > 180.0 || longitude <= -180.0) {
			throw new IllegalArgumentException("Invalid Value. Latitue is between 0 - 180 or 0 - (-180) degrees.");
		}
	}

	/**
	 * Checks, whether the double-value for the latitude is valid. |
	 * 
	 * @param latitude
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	private void assertIsLatitudeValueValid(double latitude) {
		if (latitude > 90.0 || latitude < -90.0) {
			throw new IllegalArgumentException("Invalid Value. Latitue is between 0 - 90 or 0 - (-90) degrees.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperies primitive
	 */
	private void assertIsRadiusValueValid(double radius) {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Invalid Value. Radius has to be positive.");
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperies primitive
	 */
	private void assertRadiusIsPositive(double radius) {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Value of Radius not valid. Only positive Values are allowed.");
		}
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

		double x = this.getRadius() * Math.sin(Math.toRadians(this.getLongitude()))
				* Math.cos(Math.toRadians(this.getLatitude()));
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
		double y = this.getRadius() * Math.sin(Math.toRadians(this.getLongitude()))
				* Math.sin(Math.toRadians(this.getLatitude()));
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
		double z = this.getRadius() * Math.cos(Math.toRadians(this.getLongitude()));
		return z;
	}

}
