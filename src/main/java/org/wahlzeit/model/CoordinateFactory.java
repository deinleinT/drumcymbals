package org.wahlzeit.model;

/**
 * Factory to get a correct instance of a Coordinate
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateFactory {

	/**
	 * Factory-method
	 * 
	 * @param latitude
	 * @param longitude
	 * @return AbstractCoordinate-Object, if one parameter is null, this method
	 *         returns a CoordinateNull-Object (NullObject), if both parameter
	 *         are double-values, this method returns a Coordinate-Object
	 */
	public static AbstractCoordinate getCoordinate(Double latitude, Double longitude) {
		if (latitude == null || longitude == null) {
			return CoordinateNull.getInstance();
		} else {
			return new Coordinate(latitude, longitude);
		}
	}

}
