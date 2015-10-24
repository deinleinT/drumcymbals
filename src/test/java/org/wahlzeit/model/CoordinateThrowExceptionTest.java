package org.wahlzeit.model;

import org.junit.Test;

public class CoordinateThrowExceptionTest {

	AbstractCoordinate coordinate;

	/**
	 * TestMethod to check the correct behaviour, when the getLatitude()-method
	 * will be executed on a CoordinateNull-Object (it has to throw a
	 * CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLatitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.getLatitude();
	}

	/**
	 * TestMethod to check the correct behaviour, when the getLongitude()-method
	 * will be executed on a CoordinateNull-Object (it has to throw a
	 * CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLongitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.getLongitude();
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getLatitudinalDistance()-method will be executed on a
	 * CoordinateNull-Object (it has to throw a CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLatitudinalDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getLatitudinalDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getLongitudinalDistance()-method will be executed on a
	 * CoordinateNull-Object (it has to throw a CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLongitudinalDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getLongitudinalDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getLongitudinalDistance()-method will be executed on a
	 * CoordinateNull-Object (it has to throw a CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileSetLatitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.setLatitude(34);
	}

	/**
	 * TestMethod to check the correct behaviour, when the setLongitude()-method
	 * will be executed on a CoordinateNull-Object (it has to throw a
	 * CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileSetLongitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.setLongitude(34);
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getRealDistance()-method will be executed on a CoordinateNull-Object (it
	 * has to throw a CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetRealDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getRealDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the getDistance()-method
	 * will be executed on a CoordinateNull-Object (it has to throw a
	 * CoordinateNullException).
	 * 
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedNegativeValue() {
		coordinate = CoordinateFactory.getCoordinate(-91.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedPositivValue() {
		coordinate = CoordinateFactory.getCoordinate(100.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedNegativeValue() {
		coordinate = CoordinateFactory.getCoordinate(0.0, -181.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedPositiveValue() {
		coordinate = CoordinateFactory.getCoordinate(0.0, 181.0);
	}

}
