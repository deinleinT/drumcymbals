package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * Represents a Coordinate in decimal degree with latitude and longitude.
 * 
 * 
 * @author ThomasDeinlein
 *
 */
@Entity
public abstract class Coordinate extends DataObject {

	//Values important for DataStore
	@Id
	private String idLong = "coordinate";
	@Parent
	Key parent = ObjectManager.applicationRootKey;
	//

	protected boolean isNull;
	protected double latitude;
	protected double longitude;

	/**
	 * @methodtype default-constructor
	 */
	public Coordinate() {
	}

	/**
	 * Constructor to set the latitude and longitude. Checks, whether the values
	 * have valid values.
	 * 
	 * @param latitude
	 * @param longitude
	 * @methodtype constructor
	 */
	public Coordinate(double latitude, double longitude) {

		assertIsLatitudeValueValid(latitude);
		assertIsLongitudeValueValid(longitude);

		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return value of latitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 */
	public double getLatitude() throws NullCoordinateException {
		return latitude;
	}

	/**
	 * @param latitude
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setLatitude(double latitude) throws NullCoordinateException {
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
	public double getLongitude() throws NullCoordinateException {
		return longitude;
	}

	/**
	 * @param longitude
	 * @throws NullCoordinateException
	 *             if this method is execute on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setLongitude(double longitude) throws NullCoordinateException {
		assertIsLongitudeValueValid(longitude);
		this.longitude = longitude;
	}

	/**
	 * @return flag the check, whether this instance is type of NullCoordinate
	 *         or RealCoordinate
	 * @methodtype boolean
	 */
	public boolean isNullObject() {
		return isNull;
	}

	public abstract double getRealDistanceWithHaversineFormula(RealCoordinate other) throws NullCoordinateException;

	public abstract double getLongitudinalDistance(RealCoordinate other) throws NullCoordinateException;

	public abstract double getLatitudinalDistance(RealCoordinate other) throws NullCoordinateException;

	public abstract double getDistance(RealCoordinate other) throws NullCoordinateException;

	/**
	 * Checks, whether the double-value for the longitude is valid. |
	 * 
	 * @param longitude
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	private void assertIsLongitudeValueValid(double longitude) {
		if (longitude > 180.0 || longitude < -180.0) {
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

}
