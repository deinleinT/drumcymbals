package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TestClass to check the correct behavior of the FactoryMethod
 * (CoordinateFactory)
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateCreateInstanceTest {

	AbstractCoordinate coordinate;

	/**
	 * The FactoryMethod gets two null-Parameter. It has to return a
	 * CoordinateNull-Object.
	 * 
	 */
	@Test
	public void testCoordinateNullTwoNullParameters() {
		coordinate = CoordinateFactory.getCoordinate(null, null);
		assertTrue(coordinate.isNull);
	}

	/**
	 * The FactoryMethod gets one null-Parameter. It has to return a
	 * CoordinateNull-Object.
	 * 
	 */
	@Test
	public void testCoordinateNullLatitudeNullParameter() {
		coordinate = CoordinateFactory.getCoordinate(null, 45.23);
		assertTrue(coordinate.isNull);
	}

	/**
	 * The FactoryMethod gets one null-Parameter. It has to return a
	 * CoordinateNull-Object.
	 * 
	 */
	@Test
	public void testCoordinateNullLongitudeNullParameter() {
		coordinate = CoordinateFactory.getCoordinate(45.23, null);
		assertTrue(coordinate.isNull);
	}

	/**
	 * The FactoryMethod gets one null-Parameter. It has to return a
	 * Coordinate-Object.
	 * 
	 */
	@Test
	public void testCoordinateObject() {
		coordinate = CoordinateFactory.getCoordinate(45.23, 23.23);
		assertFalse(coordinate.isNull);
	}

}
