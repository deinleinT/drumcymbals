package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LocationTest {

	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();

	/**
	 * Test to check isEqual-Method of Location
	 * 
	 * @throws NullCoordinateException
	 */
	@Test
	public void testLocationIsEqual() throws NullCoordinateException {

		CartesianCoordinate cartesian = coordinateFactory.createCartesianCoordinate(3, 4, 5);
		SphericCoordinate spheric = coordinateFactory.createSphericCoordinate(53.120102354156, 45.0, 7.0710678118655);

		Location locationCartesian = new Location("test", cartesian);
		Location locationSpheric = new Location("test", spheric);

		assertTrue(locationCartesian.isEqual(locationSpheric));
		assertTrue(locationCartesian.isEqual(locationCartesian));
		assertTrue(locationSpheric.isEqual(locationCartesian));
		assertTrue(locationSpheric.isEqual(locationSpheric));

		// check whether ifEqual returns true, if new object with same values is
		// paramater
		Location cartesianLocation = new Location("test", coordinateFactory.createCartesianCoordinate(3, 4, 5));
		assertTrue(locationCartesian.isEqual(cartesianLocation));

		Location sphericLocation = new Location("test",
				coordinateFactory.createSphericCoordinate(53.120102354156, 45.0, 7.0710678118655));
		assertTrue(locationSpheric.isEqual(sphericLocation));

	}

}
