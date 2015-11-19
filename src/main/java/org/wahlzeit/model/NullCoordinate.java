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
	public double getXValue() throws NullCoordinateException{
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate. Comparison not possible.");
		
	}

	@Override
	public double getYValue() throws NullCoordinateException{
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate. Comparison not possible.");
		
	}

	@Override
	public double getZValue()throws NullCoordinateException {
		throw new NullCoordinateException("One of the Coordinate is a NullCoordinate. Comparison not possible.");
	}

	

}
