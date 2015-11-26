package org.wahlzeit.model;

import java.io.Serializable;

/**
 * 
 * Interface for SphericCoordinate, CartesianCoordinate, NullCoordinate all
 * coordinates are represented as cartesiancoordinate
 * 
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
	 * @methodtype boolean query
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
