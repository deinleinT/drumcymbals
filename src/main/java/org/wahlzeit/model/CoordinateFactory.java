package org.wahlzeit.model;

/**
 * Factory fuer die Erzeugung von Coordinate-Objekten
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateFactory {


	/**
	 * Fabrikmethode
	 * 
	 * @param latitude Breitengrad
	 * @param longitude Laengengrad
	 * @return Coordinate-Objekt, bei Übergabe von zwei Doublewerten wird
	 *         Coordinate-Objekt erstellt, bei Übergabe mindestens eines
	 *         null-Wertes wird CoordinateNull-Objekt zurueckgegeben
	 */
	public static AbstractCoordinate getCoordinate(Double latitude, Double longitude) {
		if (latitude == null || longitude == null) {
			return CoordinateNull.getInstance();
		} else {
			return new Coordinate(latitude, longitude);
		}
	}

}
