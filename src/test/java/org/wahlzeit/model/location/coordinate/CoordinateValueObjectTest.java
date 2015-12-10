package org.wahlzeit.model.location.coordinate;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateValueObjectTest {

	// TODO
	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();

	CartesianCoordinate cartesianOne = coordinateFactory.getCartesianCoordinate(1, 1, 1);;
	CartesianCoordinate cartesianTwo = coordinateFactory.getCartesianCoordinate(1, 1, 1);;
	CartesianCoordinate cartesianThree = coordinateFactory.getCartesianCoordinate(6371, 0, 0);;
	CartesianCoordinate cartesianFour;
	CartesianCoordinate cartesianFive;
	CartesianCoordinate cartesianSix;

	SphericCoordinate sphericOne = coordinateFactory.getSphericCoordinate(0, 90);;
	SphericCoordinate sphericTwo;
	SphericCoordinate sphericThree;
	SphericCoordinate sphericFour;
	SphericCoordinate sphericFive;
	SphericCoordinate sphericSix;

	@Test
	public void TestEquality() {

		assertTrue(cartesianOne.equals(cartesianTwo));

		//TODO
		assertTrue(cartesianThree.equals(sphericOne));

	}

}
