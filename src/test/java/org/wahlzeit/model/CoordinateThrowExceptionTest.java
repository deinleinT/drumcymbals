package org.wahlzeit.model;

import org.junit.Test;

public class CoordinateThrowExceptionTest {

	Coordinate coordinate;
	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();
	
	/**
	 * TestMethod to check the correct behaviour, when the getDistance()-method
	 * will be executed on a NullCoordinate-Object (it has to throw a
	 * NullCoordinateException).
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullExceptionWhileGetDistance() throws NullCoordinateException {

		coordinate = coordinateFactory.createSphericCoordinate(null, null);
		SphericCoordinate anotherCoordinate = (SphericCoordinate) coordinateFactory.createSphericCoordinate(34.43, 3.33);
		coordinate.getDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedNegativeValue() {
		coordinate = coordinateFactory.createSphericCoordinate(-91.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedPositivValue() {
		coordinate = coordinateFactory.createSphericCoordinate(100.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedNegativeValue() {
		coordinate = coordinateFactory.createSphericCoordinate(0.0, -180.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedPositiveValue() {
		coordinate = coordinateFactory.createSphericCoordinate(0.0, 181.0);
	}
	
	/**
	 * The FactoryMethod gets one null-Parameter. 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCoordinateNullLongitudeNullParameter() {
		coordinate = coordinateFactory.createSphericCoordinate(45.23, null);
		
	}
	
	
	


}
