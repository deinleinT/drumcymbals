package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateSphericCartesianTest {

	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();
	private static final double DELTA = 0.001;

	/**
	 * Tests to check the isEqual-Method between spheric and cartesian
	 * coordinates.
	 */
	@Test
	public void testIsEqual() {
		CartesianCoordinate cartesian = coordinateFactory.createCartesianCoordinate(3, 4, 5);
		SphericCoordinate spheric = coordinateFactory.createSphericCoordinate(53.120102354156, 45.0, 7.0710678118655);
		assertTrue(cartesian.isEqual(spheric));
		assertTrue(spheric.isEqual(cartesian));

		spheric = coordinateFactory.createSphericCoordinate(49.572900, 11.027835);
		cartesian = coordinateFactory.createCartesianCoordinate(790.2910695368588280491540296,
				927.699496849134391942092198877, 6253.3554727806854985276044533);
		assertTrue(cartesian.isEqual(spheric));
		assertTrue(spheric.isEqual(cartesian));

		spheric = coordinateFactory.createSphericCoordinate(49.45203, 11.076749999999947, 6371.0);
		cartesian = coordinateFactory.createCartesianCoordinate(795.717, 930.087, 6252.31);
		assertTrue(cartesian.isEqual(spheric));
		assertTrue(spheric.isEqual(cartesian));

		spheric = coordinateFactory.createSphericCoordinate(0.0, 90.0);
		cartesian = coordinateFactory.createCartesianCoordinate(6371, 0, 0);
		assertTrue(cartesian.isEqual(spheric));
		assertTrue(spheric.isEqual(cartesian));

		spheric = coordinateFactory.createSphericCoordinate(49.446456, 11.082362);
		cartesian = coordinateFactory.createCartesianCoordinate(796.205291, 930.474443, 6252.192851);
		assertTrue(cartesian.isEqual(spheric));
		assertTrue(spheric.isEqual(cartesian));

	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testIsEqualNullAndSpheric1() throws NullCoordinateException {

		Coordinate coordinate1 = CoordinateFactory.getInstance().createNullCoordinate();
		Coordinate coordinate2 = CoordinateFactory.getInstance().createSphericCoordinate(2.3, 3.3);

		coordinate1.getDistance(coordinate2);
	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testIsEqualNullAndSpheric2() throws NullCoordinateException {

		Coordinate coordinate1 = CoordinateFactory.getInstance().createNullCoordinate();
		Coordinate coordinate2 = CoordinateFactory.getInstance().createSphericCoordinate(2.3, 3.3);

		coordinate2.getDistance(coordinate1);
	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testIsEqualNullAndCartesian1() throws NullCoordinateException {

		Coordinate coordinate1 = CoordinateFactory.getInstance().createNullCoordinate();
		Coordinate coordinate2 = CoordinateFactory.getInstance().createCartesianCoordinate(2.3, 3.3, 4.4);

		coordinate1.getDistance(coordinate2);
	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testIsEqualNullAndCartesian2() throws NullCoordinateException {

		Coordinate coordinate1 = CoordinateFactory.getInstance().createNullCoordinate();
		Coordinate coordinate2 = CoordinateFactory.getInstance().createCartesianCoordinate(2.3, 3.3, 4.4);

		coordinate2.getDistance(coordinate1);
	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testIsEqualNullAndNull() throws NullCoordinateException {

		Coordinate coordinate1 = CoordinateFactory.getInstance().createNullCoordinate();
		Coordinate coordinate2 = CoordinateFactory.getInstance().createNullCoordinate();

		coordinate1.getDistance(coordinate2);
	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test
	public void testDistanceBetweenCartesianAndSpheric() throws NullCoordinateException {
		CartesianCoordinate cartesian = coordinateFactory.createCartesianCoordinate(0, 0, 6371);
		SphericCoordinate spheric = coordinateFactory.createSphericCoordinate(0.0, 0.0);
		assertEquals(0, cartesian.getDistance(spheric), 0.2);

	}

	/**
	 * Test for correct behavior of getDistance
	 *
	 * @throws NullCoordinateException
	 */
	@Test
	public void testDistanceBetweenTwoCartesianCoordinates() throws NullCoordinateException {

		CartesianCoordinate coordinate1 = coordinateFactory.createCartesianCoordinate(1, 3, -2);
		CartesianCoordinate coordinate2 = coordinateFactory.createCartesianCoordinate(-4, 2, 5);
		assertEquals(8.66, coordinate1.getDistance(coordinate2), DELTA);

		coordinate1 = coordinateFactory.createCartesianCoordinate(-2, 2, 1);
		coordinate2 = coordinateFactory.createCartesianCoordinate(4, 5, 3);
		assertEquals(7, coordinate1.getDistance(coordinate2), DELTA);
	}

	/**
	 * Test for correct behavior of getDistance between two spheric Coordinates
	 *
	 * @throws NullCoordinateException
	 */
	@Test
	public void testDistanceBetweenTwoSphericCoordinates() throws NullCoordinateException {

		SphericCoordinate coordinate1 = coordinateFactory.createSphericCoordinate(49.572680, 11.028427, 6371.0);
		SphericCoordinate coordinate2 = coordinateFactory.createSphericCoordinate(49.572680, 11.028427, 6371.0);
		assertEquals(0.0, coordinate1.getDistance(coordinate2), DELTA);

		coordinate1 = coordinateFactory.createSphericCoordinate(49.572680, 11.028427, 6371.0);
		coordinate2 = coordinateFactory.createSphericCoordinate(37.427994, -122.170255, 6371.0);
		assertEquals(9305.376, coordinate1.getDistance(coordinate2), 1.5);

		coordinate1 = coordinateFactory.createSphericCoordinate(49.460894, 11.132840, 6371.0);
		coordinate2 = coordinateFactory.createSphericCoordinate(51.497557, 7.454901, 6371.0);
		assertEquals(345.100, coordinate1.getDistance(coordinate2), 1.5);

		coordinate1 = coordinateFactory.createSphericCoordinate(49.460894, 11.132840);
		coordinate2 = coordinateFactory.createSphericCoordinate(51.497557, 7.454901);
		assertEquals(345.100, coordinate1.getDistance(coordinate2), 1.5);
	}

	/**
	 * Test to check correct Exception behavior
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullCoordinate1() throws NullCoordinateException {
		Coordinate coordinate1 = coordinateFactory.createNullCoordinate();
		coordinate1.getDistance(coordinate1);
	}

	/**
	 * Test to check correct Exception behavior
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullCoordinate2() throws NullCoordinateException {
		Coordinate coordinate1 = coordinateFactory.createNullCoordinate();
		Coordinate coordinate2 = coordinateFactory.createCartesianCoordinate(3, 2, 3);
		coordinate1.getDistance(coordinate2);
	}

	/**
	 * Test to check correct Exception behavior
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullCoordinate3() throws NullCoordinateException {
		Coordinate coordinate1 = coordinateFactory.createNullCoordinate();
		Coordinate coordinate3 = coordinateFactory.createSphericCoordinate(23.23, 23.222);
		coordinate1.getDistance(coordinate3);
	}

	/**
	 * Test to check correct Exception behavior
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullCoordinate4() throws NullCoordinateException {
		Coordinate coordinate1 = coordinateFactory.createNullCoordinate();
		Coordinate coordinate2 = coordinateFactory.createCartesianCoordinate(3, 2, 3);
		coordinate2.getDistance(coordinate1);
	}

	/**
	 * Test to check correct Exception behavior
	 *
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullCoordinate5() throws NullCoordinateException {
		Coordinate coordinate1 = coordinateFactory.createNullCoordinate();
		Coordinate coordinate3 = coordinateFactory.createSphericCoordinate(23.23, 23.222);
		coordinate3.getDistance(coordinate1);
	}

	/**
	 * Test to check isEqual on Nullcoordinate
	 * 
	 * @throws NullCoordinateException
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullCoordinateIsEqual() throws NullCoordinateException {
		Coordinate coordinate = CoordinateFactory.getInstance().createNullCoordinate();
		coordinate.isEqual(coordinate);
	}

	/**
	 * Test to check the distance between spheric and cartesian coordinates
	 * 
	 * @throws NullCoordinateException
	 */
	@Test
	public void testDistanceBetweenSphericAndCartesianCoordinates() throws NullCoordinateException {

		SphericCoordinate spheric = coordinateFactory.createSphericCoordinate(49.460894, 11.132840);
		CartesianCoordinate cartesian = coordinateFactory.createCartesianCoordinate(514.6045533210526268148,
				646.889971655839811607, 6317.147815135589073912);
		assertEquals(345, spheric.getDistance(cartesian), 5);
		assertEquals(345, cartesian.getDistance(spheric), 5);

		// Distance between Berlin (spheric) and London (caresian)
		spheric = coordinateFactory.createSphericCoordinate(52.536577, 13.308628);
		cartesian = coordinateFactory.createCartesianCoordinate(-8.792891929053, -11.057243052549, 6370.9843370101);
		assertEquals(1000, spheric.getDistance(cartesian), 200);
		assertEquals(1000, cartesian.getDistance(spheric), 200);

	}

}
