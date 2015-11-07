package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Subclass;

/**
 * Represents a Coordinate in decimal degree with latitude and longitude.
 * 
 * 
 * @author ThomasDeinlein
 *
 */
@Subclass
public abstract class AbstractCoordinate extends Coordinate {

//	// Values important for DataStore
//	@Id
//	private Long id;
//	@Parent
//	Key parent = ObjectManager.applicationRootKey;
//	//

	/**
	 * Only added for Testing correct creating of instances
	 */
	private boolean nullFlag;

	/**
	 * @methodtype default-constructor
	 */
	public AbstractCoordinate() {
	}

	/**
	 * 
	 * @return
	 * @methodtype get
	 */
	protected boolean getNullFlag() {
		return nullFlag;
	}

	/**
	 * @param isNull
	 * @methodtype set
	 */
	protected void setNullFlag(boolean isNull) {
		this.nullFlag = isNull;
	}

	/**
	 * Calculates the distances between two coordinates and returns the
	 * double-Value in km.
	 * 
	 * @methodtype template
	 */
	public double getDistance(Coordinate other) throws NullCoordinateException {
		assertIsParameterNull(other);
		return calculateDistance(this, other);
	};

	/**
	 * Checks, whether the parameters are instance of SphericCoordinate or
	 * CartesianCoordiante, converts Cartesian to Spheric and calculates the
	 * distance.
	 * 
	 * @methodtype helper
	 * @methodproperties primitive
	 */
	private double calculateDistance(Coordinate one, Coordinate two)
			throws IllegalArgumentException, NullCoordinateException {

		if (one instanceof SphericCoordinate && two instanceof SphericCoordinate) {
			return calculateDistanceBetweenTwoSphericCoordinates((SphericCoordinate) one, (SphericCoordinate) two);
		} else if (one instanceof NullCoordinate || two instanceof NullCoordinate) {
			throw new NullCoordinateException(
					"One of the Parameter is a NullCoordinate. Calculating the distance not possible.");
		} else if (one instanceof SphericCoordinate && two instanceof CartesianCoordinate) {
			return calculateDistanceBetweenTwoSphericCoordinates((SphericCoordinate) one,
					((CartesianCoordinate) two).asSphericCoordinate());
		} else if (one instanceof CartesianCoordinate && two instanceof CartesianCoordinate) {
			return calculateDistanceBetweenTwoCartesianCoordinates((CartesianCoordinate) one,
					(CartesianCoordinate) two);
		} else if (one instanceof CartesianCoordinate && two instanceof SphericCoordinate) {
			return calculateDistanceBetweenTwoSphericCoordinates(((CartesianCoordinate) one).asSphericCoordinate(),
					(SphericCoordinate) two);
		} else {
			throw new IllegalArgumentException(
					"Error. Distance can not be calculated. At lest one of the parameters has wrong value");
		}

	}

	/**
	 * @return the distance between two sphericCoordinates in km
	 * @methodtype helper
	 * @methodtye primitive
	 */
	private double calculateDistanceBetweenTwoSphericCoordinates(SphericCoordinate one, SphericCoordinate two) {

		double lat1AsRadiant = Math.toRadians(one.getLatitude());
		double lat2AsRadiant = Math.toRadians(two.getLatitude());
		double deltaLongAsRadiant = Math.toRadians(one.getLongitudinalDistance(two));

		double distance = Math.sin(lat1AsRadiant) * Math.sin(lat2AsRadiant)
				+ Math.cos(lat1AsRadiant) * Math.cos(lat2AsRadiant) * Math.cos(deltaLongAsRadiant);

		double returnValue = one.getRadius() * Math.acos(distance);

		return returnValue;
	}

	/**
	 * @return the distance between two sphericCoordinates in km
	 * @methodtype helper
	 * @methodtye primitive
	 */
	private double calculateDistanceBetweenTwoCartesianCoordinates(CartesianCoordinate one, CartesianCoordinate two) {

		double differenceOfXPow2 = Math.pow((one.getXValue() - two.getXValue()), 2);
		double differenceOfYPow2 = Math.pow((one.getYValue() - two.getYValue()), 2);
		double differenceOfZPow2 = Math.pow((one.getZValue() - two.getZValue()), 2);

		return Math.sqrt(differenceOfXPow2 + differenceOfYPow2 + differenceOfZPow2);
	}

	/**
	 * @param other
	 *            the parameter, which shall be null-checked
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertIsParameterNull(Coordinate other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter is null. Invalid value.");
		}
	}

	public abstract boolean isEqual(Coordinate other) throws NullCoordinateException;
}
