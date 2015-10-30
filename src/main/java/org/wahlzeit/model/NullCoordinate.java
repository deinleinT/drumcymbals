package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * 
 * NullObject of a Coordinate
 * is part of NullObject-Pattern
 * cooperates with RealObject, Coordinate and CoordinateFactory
 * 
 * implemented as a singleton
 * 
 * @author ThomasDeinlein
 * 
 */
@Subclass
public class NullCoordinate extends Coordinate {

	private static NullCoordinate singleton;

	/**
	 * @methodtype constructor
	 */
	private NullCoordinate() {
		this.isNull = true;
	}

	/**
	 * Returns the instance of the singleton NullCoordinate-object
	 * 
	 * @return singleton of NullCoordinate
	 * 
	 * @methodtype get
	 */
	public static synchronized NullCoordinate getInstance() {
		if (singleton == null) {
			singleton = new NullCoordinate();
		}
		return singleton;
	}

	@Override
	public double getLatitude() throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public double getLongitude() throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public void setLatitude(double latitude) throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public void setLongitude(double longitude) throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public double getRealDistanceWithHaversineFormula(RealCoordinate other) throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public double getLongitudinalDistance(RealCoordinate other) throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public double getLatitudinalDistance(RealCoordinate other) throws NullCoordinateException {
		throw new NullCoordinateException();
	}

	@Override
	public double getDistance(RealCoordinate other) throws NullCoordinateException {
		throw new NullCoordinateException();
	}

}
