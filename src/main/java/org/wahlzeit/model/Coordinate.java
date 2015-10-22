package org.wahlzeit.model;

/**
 * @author ThomasDeinlein
 *
 *
 */
public class Coordinate {

	private double latitude;
	private double longitude;

	public Coordinate() {
	}

	public Coordinate(double latitude, double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Coordinate) {
			if (this.getLatitude() == ((Coordinate) obj).getLatitude()
					&& this.getLatitude() == ((Coordinate) obj).getLatitude()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * @param other
	 *            zu uebergebendes Coordinate-Objekt
	 * @return neues Coordinates-Objekt
	 * 
	 *         Berechnet die Distanz zweier Koordinaten anhand des Satz des
	 *         Pythagoras. Die Latitude-Werte werden voneiander abgezogen und quadriert, ebenso die Longitude-Werte.
	 *         Beide Ergebnise werden addiert. Von diesem Ergebnis wird die Quadratwurzel gezogen, was dann den Distance-Wert 
	 *         wiederspiegelt.
	 */
	public double getDistance(Coordinate other) {

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
	public double getLatitudinalDistance(Coordinate other) {
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
	public double getLongitudinalDistance(Coordinate other) {

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
	public double getRealDistance(Coordinate other) {

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
