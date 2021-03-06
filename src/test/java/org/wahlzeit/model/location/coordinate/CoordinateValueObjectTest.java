package org.wahlzeit.model.location.coordinate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.wahlzeit.model.NullCoordinateException;

public class CoordinateValueObjectTest {

	// TODO
	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();

	CartesianCoordinate cartesianOne;
	CartesianCoordinate cartesianTwo;
	CartesianCoordinate cartesianThree;
	CartesianCoordinate cartesianFour;
	CartesianCoordinate cartesianFive;
	CartesianCoordinate cartesianSix;

	SphericCoordinate sphericOne;
	SphericCoordinate sphericTwo;
	SphericCoordinate sphericThree;
	SphericCoordinate sphericFour;
	SphericCoordinate sphericFive;
	SphericCoordinate sphericSix;

	NullCoordinate nullCoordinateOne;
	NullCoordinate nullCoordinateTwo;

	@Test
	public void TestEqualsMethod() {

		/*
		 * Two seperate objects, both have same coordinates. This test shall
		 * prove, that both objects are equal
		 */
		cartesianOne = coordinateFactory.getCartesianCoordinate(1, 1, 1);
		cartesianTwo = coordinateFactory.getCartesianCoordinate(1, 1, 1);

		assertTrue(cartesianOne.equals(cartesianTwo));
		assertTrue(cartesianTwo.equals(cartesianOne));

		CartesianCoordinate cartesianFour = coordinateFactory.getCartesianCoordinate(2, 2, 2);
		CartesianCoordinate cartesianFive = coordinateFactory.getCartesianCoordinate(2.0, 2.0, 2.0);

		assertTrue(cartesianFour.equals(cartesianFive));
		assertTrue(cartesianFive.equals(cartesianFour));

		/*
		 * test with spheric and cartesian Coordinates. The coordinates of both
		 * are chosen in that way, that the converted spheric coordinate is
		 * equal to the cartesian coordinate
		 */
		sphericOne = coordinateFactory.getSphericCoordinate(0, 90);
		cartesianThree = coordinateFactory.getCartesianCoordinate(6371, 0, 0);

		assertTrue(cartesianThree.equals(sphericOne));
		assertTrue(sphericOne.equals(cartesianThree));

		/*
		 * compares cartesian and spheric Coordinates, which are not equal
		 */
		assertFalse(cartesianFour.equals(sphericOne));
		assertFalse(sphericOne.equals(cartesianFour));

		/*
		 * checks whether two sphericCoordinates are equal
		 */
		sphericSix = coordinateFactory.getSphericCoordinate(44, 44);
		sphericFive = coordinateFactory.getSphericCoordinate(44, 44, 6730);

		assertFalse(sphericSix.equals(sphericFive));
		assertFalse(sphericFive.equals(sphericSix));

		sphericSix = sphericSix.setRadius(6730);

		assertTrue(sphericSix.equals(sphericFive));
		assertTrue(sphericFive.equals(sphericSix));

		/*
		 * checks NullCoordinateBehaviour
		 */
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateTwo = coordinateFactory.getNullCoordinate();

		assertTrue(nullCoordinateOne.equals(nullCoordinateTwo));
		assertTrue(nullCoordinateTwo.equals(nullCoordinateOne));

	}

	@Test
	public void TestIsSameMethod() {
		/*
		 * Two seperate objects, both have same coordinates. This test shall
		 * prove, that both objects are same
		 */
		cartesianOne = coordinateFactory.getCartesianCoordinate(1, 1, 1);
		cartesianTwo = coordinateFactory.getCartesianCoordinate(1, 1, 1);

		assertTrue(cartesianOne.isSame(cartesianTwo));
		assertTrue(cartesianTwo.isSame(cartesianOne));

		sphericOne = coordinateFactory.getSphericCoordinate(50, 50, 6371);
		sphericTwo = coordinateFactory.getSphericCoordinate(50, 50, 6371);

		assertTrue(sphericOne.isSame(sphericTwo));
		assertTrue(sphericTwo.isSame(sphericOne));

		/*
		 * two different Coordinates, with setterMethods converted all values to
		 * same values, after setting isSame should return true
		 */
		cartesianFive = coordinateFactory.getCartesianCoordinate(3, 3, 3);
		cartesianSix = coordinateFactory.getCartesianCoordinate(5, 5, 5);

		assertFalse(cartesianFive.isSame(cartesianSix));
		assertFalse(cartesianSix.isSame(cartesianFive));

		cartesianSix = cartesianSix.setXValue(3);

		assertFalse(cartesianFive.isSame(cartesianSix));
		assertFalse(cartesianSix.isSame(cartesianFive));

		cartesianSix = cartesianSix.setYValue(3);

		assertFalse(cartesianFive.isSame(cartesianSix));
		assertFalse(cartesianSix.isSame(cartesianFive));

		cartesianSix = cartesianSix.setZValue(3);

		assertTrue(cartesianSix.isSame(cartesianFive));
		assertTrue(cartesianSix.isSame(cartesianFive));

		/*
		 * Test with method chaining
		 */
		cartesianSix = cartesianSix.setXValue(5).setYValue(5).setZValue(5);
		cartesianFive = cartesianFive.setXValue(5).setYValue(5).setZValue(5);
		assertTrue(cartesianFive.isSame(cartesianSix));
		assertTrue(cartesianSix.isSame(cartesianFive));

		/*
		 * two different Coordinates, converted all Coordinate-values to same
		 * values with setterMethods, after setting isSame should return true
		 */
		sphericFive = coordinateFactory.getSphericCoordinate(3, 3, 3);
		sphericSix = coordinateFactory.getSphericCoordinate(5, 5, 5);

		assertFalse(sphericFive.isSame(sphericSix));
		assertFalse(sphericSix.isSame(sphericFive));

		sphericSix = sphericSix.setLatitude(3);

		assertFalse(sphericFive.isSame(sphericSix));
		assertFalse(sphericSix.isSame(sphericFive));

		sphericSix = sphericSix.setLongitude(3);

		assertFalse(sphericFive.isSame(sphericSix));
		assertFalse(sphericSix.isSame(sphericFive));

		sphericSix = sphericSix.setRadius(3);

		assertTrue(sphericSix.isSame(sphericFive));
		assertTrue(sphericSix.isSame(sphericFive));
		
		/*
		 * Test with method chaining
		 */
		sphericSix = sphericSix.setLatitude(5).setLongitude(5).setRadius(5);
		sphericFive = sphericFive.setLatitude(5).setLongitude(5).setRadius(5);
		assertTrue(sphericSix.isSame(sphericFive));
		assertTrue(sphericFive.isSame(sphericSix));

		/*
		 * checks whether two sphericCoordinates are same instances
		 */
		sphericSix = coordinateFactory.getSphericCoordinate(44, 44);
		sphericFive = coordinateFactory.getSphericCoordinate(44, 44, 6730);

		assertFalse(sphericSix.isSame(sphericFive));
		assertFalse(sphericFive.isSame(sphericSix));

		sphericSix = sphericSix.setRadius(6730);

		assertTrue(sphericSix.isSame(sphericFive));
		assertTrue(sphericFive.isSame(sphericSix));

		/*
		 * checks NullCoordinateBehaviour
		 */
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateTwo = coordinateFactory.getNullCoordinate();

		assertTrue(nullCoordinateOne.isSame(nullCoordinateTwo));
		assertTrue(nullCoordinateTwo.isSame(nullCoordinateOne));
		assertTrue(nullCoordinateOne.isSame(nullCoordinateOne));
		assertTrue(nullCoordinateTwo.isSame(nullCoordinateTwo));

		assertFalse(nullCoordinateOne.isSame(sphericSix));
		assertFalse(sphericSix.isSame(nullCoordinateOne));
	}

	@Test
	public void TestIsEqualMethod() throws NullCoordinateException {

		/*
		 * test with spheric and cartesian Coordinates. The coordinates of both
		 * are chosen in that way, that the converted spheric coordinate is
		 * equal to the cartesian coordinate
		 */
		sphericOne = coordinateFactory.getSphericCoordinate(0, 90);
		cartesianThree = coordinateFactory.getCartesianCoordinate(6371, 0, 0);

		assertTrue(cartesianThree.isEqual(sphericOne));
		assertTrue(sphericOne.isEqual(cartesianThree));

		/*
		 * Two seperate objects, both have same coordinates. This test shall
		 * prove, that both objects are equal
		 */
		cartesianOne = coordinateFactory.getCartesianCoordinate(1, 1, 1);
		cartesianTwo = coordinateFactory.getCartesianCoordinate(1, 1, 1);

		assertTrue(cartesianOne.isEqual(cartesianTwo));
		assertTrue(cartesianTwo.isEqual(cartesianOne));

		CartesianCoordinate cartesianFour = coordinateFactory.getCartesianCoordinate(2, 2, 2);
		CartesianCoordinate cartesianFive = coordinateFactory.getCartesianCoordinate(2.0, 2.0, 2.0);

		assertTrue(cartesianFour.isEqual(cartesianFive));
		assertTrue(cartesianFive.isEqual(cartesianFour));
		
		/*
		 * checks NullCoordinateBehaviour
		 */
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateTwo = coordinateFactory.getNullCoordinate();

		assertTrue(nullCoordinateOne.isEqual(nullCoordinateTwo));
		assertTrue(nullCoordinateTwo.isEqual(nullCoordinateOne));

		
	}

	@Test
	public void TestSetterMethod() {

		/*
		 * sets yValue of an Cartesian to an other value, checks the setted
		 * value with getter-Method
		 */
		cartesianSix = coordinateFactory.getCartesianCoordinate(665, 667, 668);
		cartesianSix = cartesianSix.setYValue(666);
		assertEquals(666.0, cartesianSix.getYValue(), 0);

		cartesianSix = cartesianSix.setXValue(666);
		assertEquals(666.0, cartesianSix.getXValue(), 0);

		cartesianSix = cartesianSix.setZValue(666);
		assertEquals(666.0, cartesianSix.getZValue(), 0);

		/*
		 * sets yValue of an spheric to an other value, checks the setted value
		 * with getter-Method
		 */
		sphericSix = coordinateFactory.getSphericCoordinate(65, 67, 68);
		sphericSix = sphericSix.setLatitude(66);
		assertEquals(66.0, sphericSix.getLatitude(), 0);

		sphericSix = sphericSix.setLongitude(66);
		assertEquals(66.0, sphericSix.getLongitude(), 0);

		sphericSix = sphericSix.setRadius(66);
		assertEquals(66.0, sphericSix.getRadius(), 0);

	}

	@Test(expected = NullCoordinateException.class)
	public void TestNullCoordinateExceptionGetXValue() throws NullCoordinateException {
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateOne.getXValue();
	}

	@Test(expected = NullCoordinateException.class)
	public void TestNullCoordinateExceptionGetYValue() throws NullCoordinateException {
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateOne.getYValue();
	}

	@Test(expected = NullCoordinateException.class)
	public void TestNullCoordinateExceptionGetZValue() throws NullCoordinateException {
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateOne.getZValue();
	}

	@Test(expected = NullCoordinateException.class)
	public void TestNullCoordinateExceptionIsEqualMethod() throws NullCoordinateException {

		/*
		 * comparing nullcoordinate returns true
		 */
		nullCoordinateOne = coordinateFactory.getNullCoordinate();
		nullCoordinateTwo = coordinateFactory.getNullCoordinate();

		assertTrue(nullCoordinateOne.isEqual(nullCoordinateTwo));
		assertTrue(nullCoordinateTwo.isEqual(nullCoordinateOne));

		/*
		 * comparing other coordinate with nullcoordinate throws Exception
		 */
		sphericOne = coordinateFactory.getSphericCoordinate(1, 1);
		nullCoordinateOne.isEqual(sphericOne);

	}

	/**
	 * Test behavior when getInstance-Method is called but not implemented, an
	 * IllegalStateException must be thrown
	 */
	@Test(expected = IllegalStateException.class)
	public void TestWrongGetInstanceCall() {
		NullCoordinate.getInstance(2, 2, 2);
	}

}
