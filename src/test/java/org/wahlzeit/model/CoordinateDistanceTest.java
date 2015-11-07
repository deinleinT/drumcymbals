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

	private SphericCoordinate sphericCoordinate1;
	private SphericCoordinate sphericCoordinate2;	

	@Before
	public void setUp() throws Exception {
		sphericCoordinate1 = new SphericCoordinate(0.0, 0.0);
		sphericCoordinate2 = new SphericCoordinate(0.0, 0.0);
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
		sphericCoordinate1.setLatitude(1.0);
		sphericCoordinate1.setLongitude(1.0);

		assertEquals(1.0, sphericCoordinate1.getLatitudinalDistance(sphericCoordinate2), 0.0);

		/*
		 * location gets new coordinates (-1.0, 0.0) testCoordinate (-5.0,5.0)
		 * result has to be 4.0
		 */
		sphericCoordinate1.setLatitude(-1.0);
		sphericCoordinate1.setLongitude(0.0);
		sphericCoordinate2.setLatitude(-5.0);
		sphericCoordinate2.setLongitude(5.0);

		assertEquals(4.0, sphericCoordinate1.getLatitudinalDistance(sphericCoordinate2), 0.0);
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
		sphericCoordinate1.setLatitude(1.0);
		sphericCoordinate1.setLongitude(1.0);

		assertEquals(1.0, sphericCoordinate1.getLongitudinalDistance(sphericCoordinate2), 0.0);

		/*
		 * location gets new coordinates (4.0, -1.0) testCoordinate (-5.0,-3.0)
		 * result has to be 2.0
		 */
		sphericCoordinate1.setLatitude(4.0);
		sphericCoordinate1.setLongitude(-1.0);
		sphericCoordinate2.setLatitude(-5.0);
		sphericCoordinate2.setLongitude(-3.0);

		assertEquals(2.0, sphericCoordinate1.getLongitudinalDistance(sphericCoordinate2), 0.0);

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
		sphericCoordinate1.setLatitude(49.572680);
		sphericCoordinate1.setLongitude(11.028427);
		sphericCoordinate2.setLatitude(37.427994);
		sphericCoordinate2.setLongitude(-122.170255);

		assertEquals(9305.376, sphericCoordinate1.getDistance(sphericCoordinate2), 1.5);

		/**
		 * Calculates the real distance between the coordinates
		 * (49.460894,11.132840) and (51.497557,7.454901).
		 * 
		 * result determined via google maps, it has to be about 345.100 km
		 */
		sphericCoordinate1.setLatitude(49.460894);
		sphericCoordinate1.setLongitude(11.132840);
		sphericCoordinate2.setLatitude(51.497557);
		sphericCoordinate2.setLongitude(7.454901);

		assertEquals(345.100, sphericCoordinate1.getDistance(sphericCoordinate2), 1.5);

	}

	/**
	 * Test for throwing IllegalArgumentException, if parameter of a
	 * DistanceMethod is null or an invalid double-value
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullParameter() throws NullCoordinateException {
		sphericCoordinate1.getDistance(null);
		sphericCoordinate1.getLatitudinalDistance(null);
		sphericCoordinate1.getLongitudinalDistance(null);
		sphericCoordinate1.setLatitude(-91.2);
		sphericCoordinate1.setLongitude(-190.2);

	}

}
