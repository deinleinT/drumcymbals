package org.wahlzeit.model;

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
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate. Comparison not possible.");
	}

	@Override
	protected double calculateDistance(Coordinate one) throws IllegalArgumentException, NullCoordinateException {
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate. Calculating the Distance not possible.");
	}

	@Override
	protected CartesianCoordinate asCartesianCoordinate() throws NullCoordinateException {
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate. Calculating the Distance not possible.");
	}

	@Override
	protected boolean compareValues(Coordinate other) throws NullCoordinateException {
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate.");
	}

}
