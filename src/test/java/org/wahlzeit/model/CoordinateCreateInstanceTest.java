package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * TestKlasse zur Pruefung, ob das isNull-Flag in der CoordinateFactory richtig gesetzt wird.
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateCreateInstanceTest {
	
	AbstractCoordinate coordinate;
	
	/**
	 * Der Fabrikmethode werden zwei null-Parameter uebergeben.
	 * Es muss das Flag entsprechend gesetzt werden.
	 */
	@Test
	public void testCoordinateNullTwoNullParameters() {
		coordinate = CoordinateFactory.getCoordinate(null, null);
		assertTrue(coordinate.isNull);
	}
	
	
	/**
	 * Der Fabrikmethode wird ein null-Parameter uebergeben.
	 * Es muss das Flag entsprechend gesetzt werden.
	 */
	@Test
	public void testCoordinateNullLatitudeNullParameter() {
		coordinate = CoordinateFactory.getCoordinate(null, 45.23);
		assertTrue(coordinate.isNull);
	}
	
	/**
	 * Der Fabrikmethode wird ein null-Parameter uebergeben.
	 * Es muss das Flag entsprechend gesetzt werden.
	 */
	@Test
	public void testCoordinateNullLongitudeNullParameter() {
		coordinate = CoordinateFactory.getCoordinate(45.23, null);
		assertTrue(coordinate.isNull);
	}
	
	/**
	 * Der Fabrikmethode werden zwei double-Parameter uebergeben.
	 * Es muss das Flag auf false gesetzt sein.
	 */
	@Test
	public void testCoordinateObject() {
		coordinate = CoordinateFactory.getCoordinate(45.23, 23.23);
		assertFalse(coordinate.isNull);
	}

}
