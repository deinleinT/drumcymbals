package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * TestCases to check the distance-methods
 * 
 * @author ThomasDeinlein
 */
public class CoordinateDistanceTest {

	private Coordinate location;
	private Coordinate testCoordinate;

	@Before
	public void setUp() throws Exception {
		location = new Coordinate(0.0, 0.0);
		testCoordinate = new Coordinate(0.0, 0.0);
	}

	@Test
	public void testLatitudinalDistance() throws CoordinateNullException {

		/*
		 * location has coordinates (1.0, 1.0), testCoordinate has (0.0, 0.0)
		 * result has to be 1.0
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		assertEquals(1.0, location.getLatitudinalDistance(testCoordinate), 1.0);

		/*
		 * location gets new coordinates (-1.0, 0.0) testCoordinate (-5.0,5.0)
		 * result has to be 4.0
		 */
		location.setLatitude(-1.0);
		location.setLongitude(0.0);
		testCoordinate.setLatitude(-5.0);
		testCoordinate.setLongitude(5.0);

		assertEquals(4.0, location.getLatitudinalDistance(testCoordinate), 0.0);
	}

	@Test
	public void testLongitudinalDistance() throws CoordinateNullException {

		/*
		 * location has coordinates (1.0, 1.0), testCoordinate has (0.0, 0.0)
		 * result has to be 1.0
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		assertEquals(1.0, location.getLongitudinalDistance(testCoordinate), 1.0);

		/*
		 * location gets new coordinates (4.0, -1.0) testCoordinate (-5.0,-3.0)
		 * result has to be 2.0
		 */
		location.setLatitude(4.0);
		location.setLongitude(-1.0);
		testCoordinate.setLatitude(-5.0);
		testCoordinate.setLongitude(-3.0);

		assertEquals(2.0, location.getLongitudinalDistance(testCoordinate), 0.0);

	}

	@Test
	public void testDistance1() throws CoordinateNullException {

		/**
		 * Tests the getDistance-Method. location hat (1.0, 2.0) testCoordinate
		 * hat (6.0, 3.0)
		 * 
		 * result has to be 5.099
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(2.0);

		testCoordinate.setLatitude(6.0);
		testCoordinate.setLongitude(3.0);

		assertEquals(5.099, location.getDistance(testCoordinate), 0.001);

	}

	@Test
	public void testDistance2() throws CoordinateNullException {
		/**
		 * tests the getDistance-Method. location has (0.0,0.0), testCoordinate
		 * has (6.0, 3.0)
		 * 
		 * result has to be 6.7082
		 * 
		 */
		location.setLatitude(0.0);
		location.setLongitude(0.0);

		testCoordinate.setLatitude(6.0);
		testCoordinate.setLongitude(3.0);

		assertEquals(6.7082, location.getDistance(testCoordinate), 0.001);

	}

	@Test
	public void testDistance3() throws CoordinateNullException {

		/**
		 * tests the getDistance-Method. location has (-1.0, -2.0),
		 * testCoordinate has (-6.0, -3.0)
		 * 
		 * result has to be 5.099
		 * 
		 */
		location.setLatitude(-1.0);
		location.setLongitude(-2.0);

		testCoordinate.setLatitude(-6.0);
		testCoordinate.setLongitude(-3.0);

		assertEquals(5.099, location.getDistance(testCoordinate), 0.001);

	}

	@Test
	public void testRealDistance() throws CoordinateNullException {

		/**
		 * Calculates the real distance between the coordinates
		 * (49.460894,11.132840) and (51.497557,7.454901).
		 * 
		 * result determined via google maps, it has to be about 345.100 km
		 */

		location.setLatitude(49.460894);
		location.setLongitude(11.132840);

		testCoordinate.setLatitude(51.497557);
		testCoordinate.setLongitude(7.454901);

		assertEquals(345.100, location.getRealDistance(testCoordinate), 0.2);

	}

	@Test
	public void testRealDistanceBetweenFAUAndStanford() throws CoordinateNullException {

		/**
		 * Calculates the real distance between the coordinates
		 * (49.572680,11.028427) and (37.427994,-122.170255).
		 * 
		 * result determined via google maps, it has to be about 9305.376 km
		 */

		location.setLatitude(49.572680);
		location.setLongitude(11.028427);

		testCoordinate.setLatitude(37.427994);
		testCoordinate.setLongitude(-122.170255);

		assertEquals(9305.376, location.getRealDistance(testCoordinate), 2.5);

	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer() throws CoordinateNullException {
		location.getDistance(null);
		location.getLatitudinalDistance(null);
		location.getLongitudinalDistance(null);

		assertEquals(0.0, 0.0, 0.0);
	}

}
