package org.wahlzeit.model;

/**
 * Factory to get a correct instance of a Coordinate
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateFactory {

	private static CoordinateFactory singleton = null;

	/**
	 * @methodtype private default-constructor for singleton-implementation
	 */
	private CoordinateFactory() {
	}

	/**
	 * @return Singleton-Instance of CoordinateFactory
	 * 
	 * @methodtype factory
	 */
	public static synchronized CoordinateFactory getInstance() {
		if (singleton == null) {
			singleton = new CoordinateFactory();
		}

		return singleton;
	}
	
	/**
	 * @methodtype initialization
	 */
	public static synchronized void initialize() {
		getInstance();
	}

	/**
	 * Factory-Method to create a SphericCoordinate with default-value for earth-radius (6371 km).
	 * 
	 * @param latitude
	 * @param longitude
	 * @return a SphericCoordinate
	 * @throws IllegalArgumentException
	 *             if one parameter is null
	 * @methodtype factory
	 */
	public SphericCoordinate createSphericCoordinate(Double latitude, Double longitude)
			throws IllegalArgumentException {
		assertIsParameterNull(latitude);
		assertIsParameterNull(longitude);
		return new SphericCoordinate(latitude, longitude);
	}

	/**
	 * Factory-Method to create a SphericCoordinate
	 * 
	 * @param latitude
	 * @param longitude
	 * @return a SphericCoordinate
	 * @throws IllegalArgumentException
	 *             if one parameter is null
	 * @methodtype factory
	 */
	public SphericCoordinate createSphericCoordinate(Double latitude, Double longitude, Double radius) {
		assertIsParameterNull(latitude);
		assertIsParameterNull(longitude);
		assertIsParameterNull(radius);
		return new SphericCoordinate(latitude, longitude, radius);

	}
	
	/**
	 * @param x xValue
	 * @param y yValue
	 * @param z zValue
	 * @return CartesianCoordinate
	 * @methodtype factory Helper
	 */
	public CartesianCoordinate createCartesianCoordinate(double x, double y, double z){
		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * Factory-method to create NullCoordinate
	 * 
	 * @return AbstractCoordinate-Object, if one parameter is null, this method
	 *         returns a CoordinateNull-Object (NullObject), if both parameter
	 *         are double-values, this method returns a Coordinate-Object
	 */
	public NullCoordinate createNullCoordinate() {
		return NullCoordinate.getInstance();
	}

	/**
	 * @param other
	 *            the parameter, which shall be null-checked
	 * @methodtype assertion Helper
	 * @methodproperty primitive
	 */
	protected void assertIsParameterNull(Object other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter is null. Invalid value.");
		}
	}

}
