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
	boolean[] expecteds;
	boolean[] actuals;
	
	@Before
	public void setUp(){
		expecteds = new boolean[1];
		actuals = new boolean[1];
	}

	/**
	 * Der Fabrikmethode werden zwei null-Parameter uebergeben.
	 * Es muss das Flag entsprechend gesetzt werden.
	 */
	@Test
	public void testCoordinateNull1() {
		coordinate = CoordinateFactory.getCoordinate(null, null);
		expecteds[0]=coordinate.isNull;
		actuals[0]=true;
		
		assertArrayEquals(expecteds, actuals);
	}
	
	
	/**
	 * Der Fabrikmethode wird ein null-Parameter uebergeben.
	 * Es muss das Flag entsprechend gesetzt werden.
	 */
	@Test
	public void testCoordinateNull2() {
		coordinate = CoordinateFactory.getCoordinate(null, 45.23);
		expecteds[0]=coordinate.isNull;
		actuals[0]=true;
		
		assertArrayEquals(expecteds, actuals);
	}
	
	/**
	 * Der Fabrikmethode werden zwei null-Parameter uebergeben.
	 * Es muss das Flag entsprechend gesetzt werden.
	 */
	@Test
	public void testCoordinateNull3() {
		coordinate = CoordinateFactory.getCoordinate(45.23, null);
		expecteds[0]=coordinate.isNull;
		actuals[0]=true;
		
		assertArrayEquals(expecteds, actuals);
	}

}
