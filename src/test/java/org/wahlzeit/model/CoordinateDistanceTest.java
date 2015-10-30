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

	private RealCoordinate location;
	private RealCoordinate testCoordinate;

	@Before
	public void setUp() throws Exception {
		location = new RealCoordinate(0.0, 0.0);
		testCoordinate = new RealCoordinate(0.0, 0.0);
	}

	/**
	 * Test for the getLatitudinalDistance-Method of RealCoordinate-Class
	 */
	@Test
	public void testLatitudinalDistance() throws NullCoordinateException {

		/*
		 * location has coordinates (1.0, 1.0), testCoordinate has (0.0, 0.0)
		 * result has to be 1.0
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		assertEquals(1.0, location.getLatitudinalDistance(testCoordinate), 0.0);

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

	/**
	 * Test for the getLongitudinalDistance-Method of RealCoordinate-Class
	 */
	@Test
	public void testLongitudinalDistance() throws NullCoordinateException {

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

	/**
	 * Test for the getGetDistance-Method of RealCoordinate-Class
	 */
	@Test
	public void testGetDistance() throws NullCoordinateException {

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

		assertEquals(9305.376, location.getDistance(testCoordinate), 1.5);

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

		assertEquals(345.100, location.getDistance(testCoordinate), 1.5);

	}

	/**
	 * TestMethod for the getRealDistance-Method of RealCoordinate-Class
	 */
	@Test
	public void testGetRealDistance() throws NullCoordinateException {
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

		assertEquals(345.100, location.getRealDistanceWithHaversineFormula(testCoordinate), 0.2);

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

		assertEquals(9305.376, location.getRealDistanceWithHaversineFormula(testCoordinate), 2.5);

	}

	/**
	 * Test for throwing IllegalArgumentException, if parameter of a
	 * DistanceMethod is null or an invalid double-value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullParameter() throws NullCoordinateException {
		location.getDistance(null);
		location.getLatitudinalDistance(null);
		location.getLongitudinalDistance(null);
		location.setLatitude(-91.2);
		location.setLongitude(-190.2);

	}

}
