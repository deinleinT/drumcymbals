package org.wahlzeit.model.location.coordinate;

import java.io.Serializable;

import org.wahlzeit.model.NullCoordinateException;

/**
 * 
 * Interface for SphericCoordinate, CartesianCoordinate, NullCoordinate all
 * coordinates are represented as cartesiancoordinate
 * 
 * 
 * @author ThomasDeinlein
 * @version: 3.0
 *
 */

public interface Coordinate extends Serializable {

	public double getDistance(Coordinate coordinate) throws NullCoordinateException;

	/**
	 * Checks whether two Coordinates are equal. Converts first both Coordinates
	 * to CartesianCoordinate and then checks the values.
	 * 
	 * @methodtype comparison
	 */
	public boolean isEqual(Coordinate coordinate) throws NullCoordinateException;

	/**
	 * @return the x-Value of a coordinate
	 * @throws NullCoordinateException
	 *             if executed on a NullCoordinate-Object
	 */
	public double getXValue() throws NullCoordinateException;

	/**
	 * @return they y-Value of a coordinate
	 * @throws NullCoordinateException
	 *             if executed on a NullCoordinate-Object
	 */
	public double getYValue() throws NullCoordinateException;

	/**
	 * @return the z-Value of a coordinate
	 * @throws NullCoordinateException
	 *             if executed on a NullCoordinate-Object
	 */
	public double getZValue() throws NullCoordinateException;

}
