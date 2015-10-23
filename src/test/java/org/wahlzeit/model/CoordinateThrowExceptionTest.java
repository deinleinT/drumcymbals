package org.wahlzeit.model;

import org.junit.Test;

public class CoordinateThrowExceptionTest {

	AbstractCoordinate coordinate;

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLatitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.getLatitude();
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLongitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.getLongitude();
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLatitudinalDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getLatitudinalDistance(anotherCoordinate);
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetLongitudinalDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getLongitudinalDistance(anotherCoordinate);
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileSetLatitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.setLatitude(34);
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileSetLongitude() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		coordinate.setLongitude(34);
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetRealDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getRealDistance(anotherCoordinate);
	}

	/**
	 * Per Factory wird ein NullObjekt erstellt. Auf diesem NullObjekt wird eine
	 * Coordinate-Methode ausgefuehrt. Da keine Koordinaten vorhanden sind, muss
	 * eine CoordinateNullException geworfen werden.
	 */
	@Test(expected = CoordinateNullException.class)
	public void testNullExceptionWhileGetDistance() throws CoordinateNullException {

		coordinate = CoordinateFactory.getCoordinate(null, null);
		Coordinate anotherCoordinate = (Coordinate) CoordinateFactory.getCoordinate(34.43, 3.33);
		coordinate.getDistance(anotherCoordinate);
	}

	/**
	 * Testet die Korrektheit des Wertebereiches. Latitude und Longitude duerfen
	 * nur im Bereiche 0 bis 180 sowie 0 bis -180 liegen. Es wird eine
	 * IllegalArgumentException geworfen, falls ungueltiger Double-Wert
	 * eingegeben wird.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitude1() {
		coordinate = CoordinateFactory.getCoordinate(-91.0, 0.0);
	}
	
	/**
	 * Testet die Korrektheit des Wertebereiches. Latitude und Longitude duerfen
	 * nur im Bereiche 0 bis 180 sowie 0 bis -180 liegen. Es wird eine
	 * IllegalArgumentException geworfen, falls ungueltiger Double-Wert
	 * eingegeben wird.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLatitude2() {
		coordinate = CoordinateFactory.getCoordinate(100.0, 0.0);
	}
	
	/**
	 * Testet die Korrektheit des Wertebereiches. Latitude und Longitude duerfen
	 * nur im Bereiche 0 bis 180 sowie 0 bis -180 liegen. Es wird eine
	 * IllegalArgumentException geworfen, falls ungueltiger Double-Wert
	 * eingegeben wird.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitude1() {
		coordinate = CoordinateFactory.getCoordinate(0.0, -181.0);
	}
	
	/**
	 * Testet die Korrektheit des Wertebereiches. Latitude und Longitude duerfen
	 * nur im Bereiche 0 bis 180 sowie 0 bis -180 liegen. Es wird eine
	 * IllegalArgumentException geworfen, falls ungueltiger Double-Wert
	 * eingegeben wird.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLongitude2() {
		coordinate = CoordinateFactory.getCoordinate(0.0, 181.0);
	}

}
