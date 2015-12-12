package org.wahlzeit.model.location.coordinate;

import org.wahlzeit.model.NullCoordinateException;

/**
 * 
 * NullObject of a Coordinate is part of NullObject-Pattern cooperates with
 * 
 * implemented as Multiton
 * 
 * @author ThomasDeinlein
 * @version: 3.0
 *
 * @Pattern (name = “Abstract Factory”   
 *           participants = { “AbstractProduct”, “ConcreteProduct”   } )
 * 
 * @Pattern ( name = "Multiton / Singleton" )
 */

public class NullCoordinate extends AbstractCoordinate {

	private static String ERROR_STRING = "One of the Coordinates is a NullCoordinate.";

	/**
	 * @methodtype constructor
	 */
	private NullCoordinate() {
		// unnecessary to execute assertClassInvariants
		// because of private constructor
	}

	/**
	 * Factory-Method to get an Instance of NullCoordinate. In
	 * AbstractCoordinate is an attribute INSTANCES defined. This HashMap
	 * manages all created Coordinate Instances. This method checks first,
	 * whether a NullCoordinate-Instance has been already created. If it has
	 * been created, the Instance from the HashMap will be returned. Otherwise
	 * the private Constructor will be executed an the new Instance will be
	 * saved in INSTANCES.
	 * 
	 * @methodtype factory
	 */
	static synchronized Coordinate getInstance() {
		
		//Preconditions
		//none

		String keyString = doCreateKeyString(0, 0, 0, NullCoordinate.class.getCanonicalName());
		NullCoordinate result = (NullCoordinate) INSTANCES.get(keyString);
		if (result == null) {

			result = (NullCoordinate) INSTANCES.get(keyString);
			if (result == null) {
				result = new NullCoordinate();
				INSTANCES.put(keyString, result);
			}

		}

		// Postconditions
		assertParameterNotNull(result);
		assertNullCoordinateInstance(result);

		return result;
	}

	@Override
	public boolean isEqual(Coordinate other) throws NullCoordinateException {
		if (this.isSame(other)) {
			return true;
		} else {
			throw new NullCoordinateException(ERROR_STRING);
		}
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
	 * @methodtype conversion
	 * @methodproperty primitive
	 */
	protected String asString() {

		// Preconditons
		// none

		String result = "NullCoordinate! No coordinates setted.";

		// postconditions
		assertStringNotEmpty(result);
		assertParameterNotNull(result);

		return result;
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	@Override
	public void assertClassInvariants() throws IllegalStateException {
		// none
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected static void assertNullCoordinateInstance(Coordinate singleton) {
		if (!(singleton instanceof NullCoordinate)) {
			throw new IllegalStateException("Error while creating NullCoordinate.");
		}
	}

}
