package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateDistanceTest {

	private Coordinate location;
	private Coordinate testCoordinate;
	private double[] expected;
	private double[] actual;

	@Before
	public void setUp() throws Exception {
		location = new Coordinate();
		testCoordinate = new Coordinate();
		expected = new double[1];
		actual = new double[1];
	}

	@Test
	public void testLatitudinalDistance() {

		/*
		 * location hat Koordinaten (1.0, 1.0), testCoordinate hat (0.0, 0.0)
		 * zwei Tests: - getLatitudinalDistance darf nicht 0.0 zurueckgeben -
		 * Rueckgabewert muss 1.0 sein
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		expected[0] = location.getLatitudinalDistance(testCoordinate);
		actual[0] = 1.0;

		assertNotEquals(0.0, actual[0], 0.0);
		assertArrayEquals(expected, actual, 1.0);

		/*
		 * 
		 */
		location.setLatitude(-1.0);
		location.setLongitude(0.0);
		testCoordinate.setLatitude(-5.0);
		testCoordinate.setLongitude(5.0);
		expected[0] = 4.0;
		actual[0] = location.getLatitudinalDistance(testCoordinate);

		assertArrayEquals(expected, actual, 0.0);
	}

	@Test
	public void testLongitudinalDistance() {

		/*
		 * location hat Koordinaten (1.0, 1.0), testCoordinate hat (0.0, 0.0)
		 * zwei Tests: - getLongitudinalDistance darf nicht 0.0 zurueckgeben -
		 * Rueckgabewert muss 1.0 sein
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		expected[0] = 1.0;
		actual[0] = location.getLongitudinalDistance(testCoordinate);

		assertNotEquals(0.0, actual[0], 0.0);
		assertArrayEquals(expected, actual, 1.0);

		/*
		 * 
		 */
		location.setLatitude(4.0);
		location.setLongitude(-1.0);
		testCoordinate.setLatitude(-5.0);
		testCoordinate.setLongitude(-3.0);
		expected[0] = 2.0;
		actual[0] = location.getLongitudinalDistance(testCoordinate);

		assertArrayEquals(expected, actual, 0.0);

	}

	/**
	 * Methode zum Testen der getDistance-Methode location hat (1.0, 2.0)
	 * testCoordinate hat (6.0, 3.0)
	 * 
	 * getDistance muss als Ergebnis 5.099 zurueckgeben
	 * 
	 */
	@Test
	public void testDistance1() {

		location.setLatitude(1.0);
		location.setLongitude(2.0);

		testCoordinate.setLatitude(6.0);
		testCoordinate.setLongitude(3.0);

		expected[0] = 5.099;
		actual[0] = location.getDistance(testCoordinate);

		assertArrayEquals(expected, actual, 0.001);

	}

	/**
	 * Methode zum Testen der getDistance-Methode location hat (0.0,0.0)
	 * testCoordinate hat (6.0, 3.0)
	 * 
	 * getDistance muss als Ergebnis 6.7082 zurueckgeben
	 * 
	 */
	@Test
	public void testDistance2() {

		location.setLatitude(0.0);
		location.setLongitude(0.0);

		testCoordinate.setLatitude(6.0);
		testCoordinate.setLongitude(3.0);

		expected[0] = 6.7082;
		actual[0] = location.getDistance(testCoordinate);

		assertArrayEquals(expected, actual, 0.001);

	}

	/**
	 * Methode zum Testen der getDistance-Methode location hat (-1.0, -2.0)
	 * testCoordinate hat (-6.0, -3.0)
	 * 
	 * getDistance muss als Ergebnis 5.099 zurueckgeben
	 * 
	 */
	@Test
	public void testDistance3() {

		location.setLatitude(-1.0);
		location.setLongitude(-2.0);

		testCoordinate.setLatitude(-6.0);
		testCoordinate.setLongitude(-3.0);

		expected[0] = 5.099;
		actual[0] = location.getDistance(testCoordinate);

		assertArrayEquals(expected, actual, 0.001);

	}

	/**
	 * Berechnet die Entfernung zwischen zwei Beispielkoordinaten.
	 * 
	 * Ergebnis ermittelt anhand google.maps
	 */
	@Test
	public void testRealDistance1() {

		location.setLatitude(49.460894);
		location.setLongitude(11.132840);

		testCoordinate.setLatitude(51.497557);
		testCoordinate.setLongitude(7.454901);

		expected[0] = 345.100;
		actual[0] = location.getRealDistance(testCoordinate);

		assertArrayEquals(expected, actual, 0.2);

	}

	/**
	 * Berechnet die Entfernung zwischen zwei Beispielkoordinaten. Entfernung
	 * zwischen der FAU und Stanford.
	 * 
	 * Ergebnis ermittelt anhand google.maps
	 */
	@Test
	public void testRealDistance2() {

		location.setLatitude(49.572680);
		location.setLongitude(11.028427);

		testCoordinate.setLatitude(37.427994);
		testCoordinate.setLongitude(-122.170255);

		expected[0] = 9305.376;
		actual[0] = location.getRealDistance(testCoordinate);

		assertArrayEquals(expected, actual, 2.5);

	}

	/**
	 * Bei der Uebergabe von null als Parameter soll eine Nullpointer-Exception
	 * geworfen werden.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {
		location.getDistance(null);
		location.getLatitudinalDistance(null);
		location.getLongitudinalDistance(null);
	}

}
