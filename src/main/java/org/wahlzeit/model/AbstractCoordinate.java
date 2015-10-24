package org.wahlzeit.model;

/**
 * AbstractClass to encapsulate the Coordinate of a Photo-Object
 * 
 * @author ThomasDeinlein
 *
 */
public abstract class AbstractCoordinate {

	protected boolean isNull;
	protected double latitude;
	protected double longitude;

	public AbstractCoordinate() {}

	public AbstractCoordinate(double latitude, double longitude) {
		
		if(latitude>90.0 || latitude<-90.0){
			throw new IllegalArgumentException("Invalid Value. Latitue is between 0 - 90 or 0 - (-90) degrees.");
		}
		
		if(longitude>180.0 || longitude<-180.0){
			throw new IllegalArgumentException("Invalid Value. Latitue is between 0 - 180 or 0 - (-180) degrees.");
		}
		
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() throws CoordinateNullException {
		return latitude;
	}

	public void setLatitude(double latitude) throws CoordinateNullException {
		this.latitude = latitude;
	}

	public double getLongitude() throws CoordinateNullException {
		return longitude;
	}

	public void setLongitude(double longitude) throws CoordinateNullException {
		this.longitude = longitude;
	}

	public boolean isNullObject() {
		return isNull;
	}

	public abstract double getRealDistance(Coordinate other) throws CoordinateNullException;

	public abstract double getLongitudinalDistance(Coordinate other) throws CoordinateNullException;

	public abstract double getLatitudinalDistance(Coordinate other) throws CoordinateNullException;
	
	public abstract double getDistance(Coordinate other) throws CoordinateNullException;

}
