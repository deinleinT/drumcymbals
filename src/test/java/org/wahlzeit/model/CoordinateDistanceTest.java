package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * TestKlasse fuer die Ueberpruefung der Korrektheit der Distanzmethoden
 * 
 * @author ThomasDeinlein
 */
public class CoordinateDistanceTest {

	private Coordinate location;
	private Coordinate testCoordinate;
	private double expected;
	private double actual;

	@Before
	public void setUp() throws Exception {
		location = new Coordinate(0.0,0.0);
		testCoordinate = new Coordinate(0.0,0.0);
	}

	@Test
	public void testLatitudinalDistance() throws CoordinateNullException  {

		/*
		 * location hat Koordinaten (1.0, 1.0), testCoordinate hat (0.0, 0.0)
		 * zwei Tests: - getLatitudinalDistance darf nicht 0.0 zurueckgeben -
		 * Rueckgabewert muss 1.0 sein
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		expected = 1.0;
		actual = location.getLatitudinalDistance(testCoordinate);

		assertNotEquals(0.0, actual, 0.0);
		assertEquals(expected, actual, 1.0);

		/*
		 * location hat neue Koordinaten (-1.0, 0.0)
		 * testCoordinate (-5.0,5.0)
		 * Ergebnis muss 4.0 sein
		 */
		location.setLatitude(-1.0);
		location.setLongitude(0.0);
		testCoordinate.setLatitude(-5.0);
		testCoordinate.setLongitude(5.0);
		expected = location.getLatitudinalDistance(testCoordinate);
		actual = 4.0;

		assertEquals(expected, actual, 0.0);
	}

	@Test
	public void testLongitudinalDistance()throws CoordinateNullException  {

		/*
		 * location hat Koordinaten (1.0, 1.0), testCoordinate hat (0.0, 0.0)
		 * zwei Tests: - getLongitudinalDistance darf nicht 0.0 zurueckgeben -
		 * Rueckgabewert muss 1.0 sein
		 * 
		 */
		location.setLatitude(1.0);
		location.setLongitude(1.0);

		expected = 1.0;
		actual = location.getLongitudinalDistance(testCoordinate);

		assertNotEquals(0.0, actual, 0.0);
		assertEquals(expected, actual, 1.0);

		/*
		 * location hat neue Koordinaten (4.0, -1.0)
		 * testCoordinate (-5.0,-3.0)
		 * Ergebnis muss 2.0 sein
		 */
		location.setLatitude(4.0);
		location.setLongitude(-1.0);
		testCoordinate.setLatitude(-5.0);
		testCoordinate.setLongitude(-3.0);
		expected = 2.0;
		actual = location.getLongitudinalDistance(testCoordinate);

		assertEquals(expected, actual, 0.0);

	}

	/**
	 * Methode zum Testen der getDistance-Methode location hat (1.0, 2.0)
	 * testCoordinate hat (6.0, 3.0)
	 * 
	 * getDistance muss als Ergebnis 5.099 zurueckgeben
	 * 
	 */
	@Test
	public void testDistance1()throws CoordinateNullException  {

		location.setLatitude(1.0);
		location.setLongitude(2.0);

		testCoordinate.setLatitude(6.0);
		testCoordinate.setLongitude(3.0);

		expected = 5.099;
		actual = location.getDistance(testCoordinate);

		assertEquals(expected, actual, 0.001);

	}

	/**
	 * Methode zum Testen der getDistance-Methode location hat (0.0,0.0)
	 * testCoordinate hat (6.0, 3.0)
	 * 
	 * getDistance muss als Ergebnis 6.7082 zurueckgeben
	 * 
	 */
	@Test
	public void testDistance2()throws CoordinateNullException  {

		location.setLatitude(0.0);
		location.setLongitude(0.0);

		testCoordinate.setLatitude(6.0);
		testCoordinate.setLongitude(3.0);

		expected = 6.7082;
		actual = location.getDistance(testCoordinate);

		assertEquals(expected, actual, 0.001);

	}

	/**
	 * Methode zum Testen der getDistance-Methode location hat (-1.0, -2.0)
	 * testCoordinate hat (-6.0, -3.0)
	 * 
	 * getDistance muss als Ergebnis 5.099 zurueckgeben
	 * 
	 */
	@Test
	public void testDistance3()throws CoordinateNullException  {

		location.setLatitude(-1.0);
		location.setLongitude(-2.0);

		testCoordinate.setLatitude(-6.0);
		testCoordinate.setLongitude(-3.0);

		expected = 5.099;
		actual = location.getDistance(testCoordinate);

		assertEquals(expected, actual, 0.001);

	}

	/**
	 * Berechnet die Entfernung zwischen zwei Beispielkoordinaten.
	 * 
	 * Ergebnis ermittelt anhand google.maps
	 */
	@Test
	public void testRealDistance()throws CoordinateNullException  {

		location.setLatitude(49.460894);
		location.setLongitude(11.132840);

		testCoordinate.setLatitude(51.497557);
		testCoordinate.setLongitude(7.454901);

		expected = 345.100;
		actual = location.getRealDistance(testCoordinate);

		assertEquals(expected, actual, 0.2);

	}

	/**
	 * Berechnet die Entfernung zwischen zwei Beispielkoordinaten. Entfernung
	 * zwischen der FAU und Stanford.
	 * 
	 * Ergebnis ermittelt anhand google.maps
	 */
	@Test
	public void testRealDistanceBetweenFAUAndStanford() throws CoordinateNullException {

		location.setLatitude(49.572680);
		location.setLongitude(11.028427);

		testCoordinate.setLatitude(37.427994);
		testCoordinate.setLongitude(-122.170255);

		expected = 9305.376;
		actual = location.getRealDistance(testCoordinate);

		assertEquals(expected, actual, 2.5);

	}

	/**
	 * Bei der Uebergabe von null als Parameter soll eine Nullpointer-Exception
	 * geworfen werden.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullPointer()throws CoordinateNullException  {
		location.getDistance(null);
		location.getLatitudinalDistance(null);
		location.getLongitudinalDistance(null);
		
		assertEquals(0.0, 0.0, 0.0);
	}

}
