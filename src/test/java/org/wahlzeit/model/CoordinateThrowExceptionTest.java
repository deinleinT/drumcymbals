package org.wahlzeit.model;

import org.junit.Test;

/**
 * Test-Class for testing several Exception-Behavior.
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateThrowExceptionTest {

	Coordinate coordinate;
	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();

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

}
