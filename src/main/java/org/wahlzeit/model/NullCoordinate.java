package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * 
 * NullObject of a Coordinate is part of NullObject-Pattern cooperates with
 * RealObject, Coordinate and CoordinateFactory
 * 
 * implemented as a singleton
 * 
 * @author ThomasDeinlein
 * 
 */
@Subclass(index=true)
public class NullCoordinate extends AbstractCoordinate {

	private static NullCoordinate singleton;

	/**
	 * @methodtype constructor
	 */
	private NullCoordinate() {
		this.setNullFlag(true);
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
	public boolean isEqual(Coordinate other) throws NullCoordinateException {
		throw new NullCoordinateException("Coordinates aren't set. Comparison not possible.");
	}

}
