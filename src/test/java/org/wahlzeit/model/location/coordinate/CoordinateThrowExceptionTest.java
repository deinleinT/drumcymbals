package org.wahlzeit.model.location.coordinate;

import org.junit.Test;
import org.wahlzeit.model.NullCoordinateException;
import org.wahlzeit.model.location.coordinate.CoordinateFactory;

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
	 * @throws NullCoordinateException 
	 * @throws IllegalStateException 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedNegativeValue() throws IllegalStateException, NullCoordinateException {
		coordinate = coordinateFactory.getSphericCoordinate(-91.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * @throws NullCoordinateException 
	 * @throws IllegalStateException 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedPositivValue() throws IllegalStateException, NullCoordinateException {
		coordinate = coordinateFactory.getSphericCoordinate(100.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * @throws NullCoordinateException 
	 * @throws IllegalStateException 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedNegativeValue() throws IllegalStateException, NullCoordinateException {
		coordinate = coordinateFactory.getSphericCoordinate(0.0, -180.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * @throws NullCoordinateException 
	 * @throws IllegalStateException 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedPositiveValue() throws IllegalStateException, NullCoordinateException {
		coordinate = coordinateFactory.getSphericCoordinate(0.0, 181.0);
	}

}
