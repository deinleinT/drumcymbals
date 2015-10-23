package org.wahlzeit.model;

/**
 * @author ThomasDeinlein
 *
 *
 */
public class Coordinate extends AbstractCoordinate {


	public Coordinate(double latitude, double longitude) {
		super(latitude, longitude);
	}

	/**
	 * @param other
	 *            zu uebergebendes Coordinate-Objekt
	 * @return neues Coordinates-Objekt
	 * 
	 *         Berechnet die Distanz zweier Koordinaten anhand des Satz des
	 *         Pythagoras. Die Latitude-Werte werden voneiander abgezogen und
	 *         quadriert, ebenso die Longitude-Werte. Beide Ergebnise werden
	 *         addiert. Von diesem Ergebnis wird die Quadratwurzel gezogen, was
	 *         dann den Distance-Wert wiederspiegelt.
	 */
	public double getDistance(Coordinate other) throws CoordinateNullException {

		double latitudeDistanceSquare = Math.pow(this.getLatitudinalDistance(other), 2);
		double longitudeDistanceSquare = Math.pow(this.getLongitudinalDistance(other), 2);

		return Math.sqrt((latitudeDistanceSquare + longitudeDistanceSquare));
	}

	/**
	 * @param other
	 *            zu uebergebendes Coordinate-Objekt
	 * @return double-Wert der berechneten Differenz der Latitude-Werte
	 * 
	 *         Berechnet this.latitude - other.latitude, und gibt berechneten
	 *         als absoluten Wert zurueck.
	 * 
	 */
	public double getLatitudinalDistance(Coordinate other) throws CoordinateNullException {
		return Math.abs(other.getLatitude() - this.getLatitude());
	}

	/**
	 * @param other
	 *            zu uebergebendes Coordinate-Objekt
	 * @return double-Wert der berechneten Differenz der Longitude-Werte
	 * 
	 *         Berechnet Differenz der Longitude-Werte. Gibt als Ergebnis den
	 *         Betrag der Differenz zurueck.
	 */
	public double getLongitudinalDistance(Coordinate other) throws CoordinateNullException {

		return Math.abs(other.getLongitude() - this.getLongitude());
	}

	/**
	 * @param other
	 *            zu uebergebendes Coordinate-Objekt
	 * @return double-Wert der berechneten Entfernung in KM
	 * 
	 *         Berechnet mit Hilfe der Haversine-Formel die tatsächliche
	 *         Entfernung zwischen zwei GPS-Koordinaten in km.
	 *
	 */
	public double getRealDistance(Coordinate other) throws CoordinateNullException {

		int radius = 6371;

		double lat = Math.toRadians(other.getLatitude() - this.getLatitude());
		double lon = Math.toRadians(other.getLongitude() - this.getLongitude());

		double firstSum = Math.sin(lat / 2) * Math.sin(lat / 2) + Math.cos(Math.toRadians(this.getLatitude()))
				* Math.cos(Math.toRadians(other.getLatitude())) * Math.sin(lon / 2) * Math.sin(lon / 2);
		double secondeSum = 2 * Math.atan2(Math.sqrt(firstSum), Math.sqrt(1 - firstSum));
		double realDistanceInKm = radius * secondeSum;

		return realDistanceInKm;
	}

}
