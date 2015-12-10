package org.wahlzeit.model.location;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.wahlzeit.model.NullCoordinateException;
import org.wahlzeit.model.location.Location;
import org.wahlzeit.model.location.coordinate.CartesianCoordinate;
import org.wahlzeit.model.location.coordinate.CoordinateFactory;
import org.wahlzeit.model.location.coordinate.SphericCoordinate;

public class LocationTest {

	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();

	/**
	 * Test to check isEqual-Method of Location
	 * 
	 * @throws NullCoordinateException
	 */
	@Test
	public void testLocationIsEqual() throws IllegalStateException,NullCoordinateException {

		CartesianCoordinate cartesian = coordinateFactory.getCartesianCoordinate(3, 4, 5);
		SphericCoordinate spheric = coordinateFactory.getSphericCoordinate(53.120102354156, 45.0, 7.0710678118655);

		Location locationCartesian = new Location("test", cartesian);
		Location locationSpheric = new Location("test", spheric);

		assertTrue(locationCartesian.isEqual(locationSpheric));
		assertTrue(locationCartesian.isEqual(locationCartesian));
		assertTrue(locationSpheric.isEqual(locationCartesian));
		assertTrue(locationSpheric.isEqual(locationSpheric));

		// check whether ifEqual returns true, if new object with same values is
		// paramater
		Location cartesianLocation = new Location("test", coordinateFactory.getCartesianCoordinate(3, 4, 5));
		assertTrue(locationCartesian.isEqual(cartesianLocation));

		Location sphericLocation = new Location("test",
				coordinateFactory.getSphericCoordinate(53.120102354156, 45.0, 7.0710678118655));
		assertTrue(locationSpheric.isEqual(sphericLocation));

	}

}
