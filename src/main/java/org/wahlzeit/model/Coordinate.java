package org.wahlzeit.model;

/**
 * The Coordinates of a Photo, with Latitude and Longitude.
 * 
 * @author ThomasDeinlein
 *
 */
public class Coordinate extends AbstractCoordinate {

	public Coordinate(double latitude, double longitude) {
		super(latitude, longitude);
	}

	/**
	 * @param other
	 *            the other Coordinate-Object
	 * @return the calculated distance between the coordinates as a double-value
	 * 
	 *         Calculates the distance of two coordinates with the Pythagoras'
	 *         theorem.
	 */
	public double getDistance(Coordinate other) throws CoordinateNullException {

		double latitudeDistanceSquare = Math.pow(this.getLatitudinalDistance(other), 2);
		double longitudeDistanceSquare = Math.pow(this.getLongitudinalDistance(other), 2);

		return Math.sqrt((latitudeDistanceSquare + longitudeDistanceSquare));
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
	public double getLatitudinalDistance(Coordinate other) throws CoordinateNullException {
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
	public double getLongitudinalDistance(Coordinate other) throws CoordinateNullException {

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
	public double getRealDistance(Coordinate other) throws CoordinateNullException {

		int radius = 6371;

		double lat = Math.toRadians(other.getLatitude() - this.getLatitude());
		double lon = Math.toRadians(other.getLongitude() - this.getLongitude());

		double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
				* Math.cos(Math.toRadians(other.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
		double secondeSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
		double realDistanceInKm = radius * secondeSum;

		return realDistanceInKm;
	}

}
