package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * The Coordinates of a Photo, with Latitude and Longitude.
 * 
 * @author ThomasDeinlein
 *
 */

@Subclass
public class RealCoordinate extends Coordinate {

	private static final Long serialVersionUID = 1L;
	private static final int EARTH_RADIUS_IN_KM = 6371;

	/**
	 * @param latitude
	 * @param longitude
	 * @methodtype constructor
	 */
	public RealCoordinate(double latitude, double longitude) {
		super(latitude, longitude);
	}

	/**
	 * Calculates the distance of two coordinates great-circle distance formula,
	 * see {@link https://en.wikipedia.org/wiki/Great-circle_distance} for
	 * details
	 * 
	 * @param other
	 *            the other Coordinate-Object
	 * @return The calculated distance between the coordinates as a
	 *         double-value.
	 * 
	 */
	public double getDistance(RealCoordinate other) throws IllegalArgumentException, NullCoordinateException {
		assertIsParameterNull(other);

		double lat1AsRadiant = Math.toRadians(this.latitude);
		double lat2AsRadiant = Math.toRadians(other.getLatitude());
		double deltaLongAsRadiant = Math.toRadians(this.getLongitudinalDistance(other));

		double distance = Math.sin(lat1AsRadiant) * Math.sin(lat2AsRadiant)
				+ Math.cos(lat1AsRadiant) * Math.cos(lat2AsRadiant) * Math.cos(deltaLongAsRadiant);

		return EARTH_RADIUS_IN_KM * Math.acos(distance);
	}

	/**
	 * @param other
	 *            the parameter, which shall be null-checked
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	private void assertIsParameterNull(RealCoordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter is null. Invalid value.");
		}
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
	public double getLatitudinalDistance(RealCoordinate other)
			throws IllegalArgumentException, NullCoordinateException {
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
	public double getLongitudinalDistance(RealCoordinate other)
			throws IllegalArgumentException, NullCoordinateException {
		assertIsParameterNull(other);
		return Math.abs(other.getLongitude() - this.getLongitude());
	}

	/**
	 * @param other
	 *            the other Coordinate-Object
	 * @return the real distance in km as a double-value
	 * 
	 *         Calculates with the haversine-formula the real distance between
	 *         the two coordinates.
	 * 
	 */
	public double getRealDistanceWithHaversineFormula(RealCoordinate other) throws NullCoordinateException {
		assertIsParameterNull(other);

		double lat = Math.toRadians(other.getLatitude() - this.getLatitude());
		double lon = Math.toRadians(other.getLongitude() - this.getLongitude());

		double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
				* Math.cos(Math.toRadians(other.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
		double secondeSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
		double realDistanceInKm = EARTH_RADIUS_IN_KM * secondeSum;

		return realDistanceInKm;
	}

}
