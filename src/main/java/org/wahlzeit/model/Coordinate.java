package org.wahlzeit.model;

import java.io.Serializable;

/**
 * 
 * Interface for SphericCoordinate, CartesianCoordinate, NullCoordinate
 * 
 * @author ThomasDeinlein
 *
 */

public interface Coordinate extends Serializable {

	public double getDistance(Coordinate coordinate) throws NullCoordinateException;

	/**
	 * Checks whether two Coordinates are equal. Converts first both Coordinates
	 * to CartesianCoordinate and then check the values.
	 * 
	 * @param coordinate
	 * @return
	 * @throws NullCoordinateException
	 */
	public boolean isEqual(Coordinate coordinate) throws NullCoordinateException;

}
