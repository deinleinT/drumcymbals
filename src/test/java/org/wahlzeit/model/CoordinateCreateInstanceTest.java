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

	Coordinate coordinate;
	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();

	/**
	 * The FactoryMethod gets two null-Parameter. It has to return a
	 * NullCoordinate-Object.
	 * 
	 */
	@Test
	public void testCoordinateNullTwoNullParameters() {
		coordinate = coordinateFactory.createCoordinate(null, null);
		assertTrue(coordinate.isNullObject());
	}

	/**
	 * The FactoryMethod gets one null-Parameter. It has to return a
	 * NullCoordinate-Object.
	 * 
	 */
	@Test
	public void testCoordinateNullLatitudeNullParameter() {
		coordinate = coordinateFactory.createCoordinate(null, 45.23);
		assertTrue(coordinate.isNullObject());
	}

	/**
	 * The FactoryMethod gets one null-Parameter. It has to return a
	 * NullCoordinate-Object.
	 * 
	 */
	@Test
	public void testCoordinateNullLongitudeNullParameter() {
		coordinate = coordinateFactory.createCoordinate(45.23, null);
		assertTrue(coordinate.isNullObject());
	}

	/**
	 * The FactoryMethod gets one null-Parameter. It has to return a
	 * NullCoordinate-Object.
	 * 
	 */
	@Test
	public void testCoordinateObject() {
		coordinate = coordinateFactory.createCoordinate(45.23, 23.23);
		assertFalse(coordinate.isNullObject());
	}

}
