package org.wahlzeit.model.location.coordinate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.wahlzeit.model.NullCoordinateException;
import org.wahlzeit.model.location.coordinate.CartesianCoordinate;
import org.wahlzeit.model.location.coordinate.CoordinateFactory;
import org.wahlzeit.model.location.coordinate.NullCoordinate;
import org.wahlzeit.model.location.coordinate.SphericCoordinate;

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
		coordinate = coordinateFactory.getNullCoordinate();
		assertTrue(coordinate instanceof NullCoordinate);
	}
	
	//all following tests added during adap-hw05
	
	/**
	 * The FactoryMethod gets two double-values.
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testCoordinateObject() throws IllegalStateException,NullCoordinateException {
		coordinate = coordinateFactory.getSphericCoordinate(45.23, 23.23);
		assertFalse(coordinate instanceof NullCoordinate);
	}
	
	
	@Test
	public void testSphericCoordinateObject() throws IllegalStateException,NullCoordinateException {
		
		coordinate = coordinateFactory.getSphericCoordinate(45.23, 23.23);
		assertFalse(coordinate instanceof NullCoordinate);
		
		coordinate = coordinateFactory.getSphericCoordinate(12.2, 122.2, 6733.34);
		assertTrue(coordinate instanceof SphericCoordinate);
		assertEquals(6733.34, ((SphericCoordinate)coordinate).getRadius(), 0.0);
		
	}
	
	@Test
	public void testCartesianCoordinate() throws IllegalStateException,NullCoordinateException{
		coordinate = coordinateFactory.getCartesianCoordinate(3.3, 4.4, 4.4);
		assertTrue(coordinate instanceof CartesianCoordinate);
	}
	
	
	
	//

}
