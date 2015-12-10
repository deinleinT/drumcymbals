package org.wahlzeit.model.location.coordinate;

import org.wahlzeit.model.NullCoordinateException;

/**
 * 
 * NullObject of a Coordinate is part of NullObject-Pattern cooperates with
 * RealObject, Coordinate and CoordinateFactory
 * 
 * implemented as a singleton
 * 
 * @author ThomasDeinlein
 * 
 *
 * @Pattern (
 *   name = “Abstract Factory”
 *   participants = {
 *      “AbstractProduct”, 
 *     “ConcreteProduct”   } )
 * 
 * @Pattern (
 *   name = "Singleton" )
 */

public class NullCoordinate extends AbstractCoordinate {

	private static NullCoordinate singleton;
	private static String ERROR_STRING = "One of the Coordinates is a NullCoordinate.";

	/**
	 * @methodtype constructor
	 */
	private NullCoordinate() {
		//unnecessary to execute assertClassInvariants
		//because of private constructor
	}

	/**
	 * Returns the instance of the singleton NullCoordinate-object
	 * 
	 * @return singleton of NullCoordinate
	 * 
	 * @methodtype get
	 */
	public static synchronized NullCoordinate getInstance() {

		// Preconditions
		// none

		if (singleton == null) {
			singleton = new NullCoordinate();
		}

		// Postconditions
		assertSingletonState(singleton);

		return singleton;
	}

	@Override
	public boolean isEqual(Coordinate other) throws NullCoordinateException {
		throw new NullCoordinateException(ERROR_STRING);
	}

	@Override
	public double getDistance(Coordinate other) throws NullCoordinateException {
		throw new NullCoordinateException(ERROR_STRING);
	}
	
	@Override
	public double getXValue() throws NullCoordinateException {
		throw new NullCoordinateException(ERROR_STRING);
	}

	@Override
	public double getYValue() throws NullCoordinateException {
		throw new NullCoordinateException(ERROR_STRING);
	}

	@Override
	public double getZValue() throws NullCoordinateException {
		throw new NullCoordinateException(ERROR_STRING);
	}	

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	@Override
	public void assertClassInvariants() throws IllegalStateException {
		//none
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertSingletonState(NullCoordinate singleton) {
		singleton.assertClassInvariants();
		if (!(singleton instanceof NullCoordinate)) {
			throw new IllegalStateException("Error while creating NullCoordinate.");
		}
	}

}
