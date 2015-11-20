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
		coordinate = coordinateFactory.createNullCoordinate();
		assertTrue(coordinate instanceof NullCoordinate);
	}
	
	//all following tests added during adap-hw05
	
	/**
	 * The FactoryMethod gets two double-values.
	 * 
	 */
	@Test
	public void testCoordinateObject() {
		coordinate = coordinateFactory.createSphericCoordinate(45.23, 23.23);
		assertFalse(coordinate instanceof NullCoordinate);
	}
	
	
	@Test
	public void testSphericCoordinateObject() {
		
		coordinate = coordinateFactory.createSphericCoordinate(45.23, 23.23);
		assertFalse(coordinate instanceof NullCoordinate);
		
		coordinate = coordinateFactory.createSphericCoordinate(12.2, 122.2, 6733.34);
		assertTrue(coordinate instanceof SphericCoordinate);
		assertEquals(6733.34, ((SphericCoordinate)coordinate).getRadius(), 0.0);
		
	}
	
	@Test
	public void testCartesianCoordinate(){
		coordinate = coordinateFactory.createCartesianCoordinate(3.3, 4.4, 4.4);
		assertTrue(coordinate instanceof CartesianCoordinate);
	}
	
	
	
	//

}
