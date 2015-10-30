package org.wahlzeit.model;

import org.junit.Test;

public class CoordinateThrowExceptionTest {

	Coordinate coordinate;
	CoordinateFactory coordinateFactory = CoordinateFactory.getInstance();
	
	/**
	 * TestMethod to check the correct behaviour, when the getLatitude()-method
	 * will be executed on a NullCoordinate-Object (it has to throw a
	 * NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileGetLatitude() throws NullCoordinateException {

		coordinate = coordinateFactory.createCoordinate(null, null);
		coordinate.getLatitude();
	}

	/**
	 * TestMethod to check the correct behaviour, when the getLongitude()-method
	 * will be executed on a NullCoordinate-Object (it has to throw a
	 * NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileGetLongitude() throws NullCoordinateException {

		coordinate = coordinateFactory.createCoordinate(null, null);
		coordinate.getLongitude();
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getLatitudinalDistance()-method will be executed on a
	 * NullCoordinate-Object (it has to throw a NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileGetLatitudinalDistance() throws NullCoordinateException {

		coordinate = CoordinateFactory.getInstance().createCoordinate(null, null);
		RealCoordinate anotherCoordinate = (RealCoordinate) coordinateFactory.createCoordinate(34.43, 3.33);
		coordinate.getLatitudinalDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getLongitudinalDistance()-method will be executed on a
	 * NullCoordinate-Object (it has to throw a NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileGetLongitudinalDistance() throws NullCoordinateException {

		coordinate = CoordinateFactory.getInstance().createCoordinate(null, null);
		RealCoordinate anotherCoordinate = (RealCoordinate) coordinateFactory.createCoordinate(34.43, 3.33);
		coordinate.getLongitudinalDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getLongitudinalDistance()-method will be executed on a
	 * NullCoordinate-Object (it has to throw a NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileSetLatitude() throws NullCoordinateException {

		coordinate = coordinateFactory.createCoordinate(null, null);
		coordinate.setLatitude(34);
	}

	/**
	 * TestMethod to check the correct behaviour, when the setLongitude()-method
	 * will be executed on a NullCoordinate-Object (it has to throw a
	 * NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileSetLongitude() throws NullCoordinateException {

		coordinate = coordinateFactory.createCoordinate(null, null);
		coordinate.setLongitude(34);
	}

	/**
	 * TestMethod to check the correct behaviour, when the
	 * getRealDistance()-method will be executed on a NullCoordinate-Object (it
	 * has to throw a NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileGetRealDistance() throws NullCoordinateException {

		coordinate = coordinateFactory.createCoordinate(null, null);
		RealCoordinate anotherCoordinate = (RealCoordinate) coordinateFactory.createCoordinate(34.43, 3.33);
		coordinate.getRealDistanceWithHaversineFormula(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the getDistance()-method
	 * will be executed on a NullCoordinate-Object (it has to throw a
	 * NullCoordinateException).
	 * 
	 */
	@Test(expected = NullCoordinateException.class)
	public void testNullExceptionWhileGetDistance() throws NullCoordinateException {

		coordinate = coordinateFactory.createCoordinate(null, null);
		RealCoordinate anotherCoordinate = (RealCoordinate) coordinateFactory.createCoordinate(34.43, 3.33);
		coordinate.getDistance(anotherCoordinate);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedNegativeValue() {
		coordinate = coordinateFactory.createCoordinate(-91.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the latitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitudeOversizedPositivValue() {
		coordinate = coordinateFactory.createCoordinate(100.0, 0.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedNegativeValue() {
		coordinate = coordinateFactory.createCoordinate(0.0, -181.0);
	}

	/**
	 * TestMethod to check the correct behaviour, when the CoordinateFactory
	 * gets an invalid parameter for the longitude.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitudeOversizedPositiveValue() {
		coordinate = coordinateFactory.createCoordinate(0.0, 181.0);
	}
	


}
