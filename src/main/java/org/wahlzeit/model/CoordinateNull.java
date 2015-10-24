package org.wahlzeit.model;

/**
 * 
 * NullObject of a Coordinate
 * 
 * implemented as a singleton
 * 
 * @author ThomasDeinlein
 * 
 */
public class CoordinateNull extends AbstractCoordinate {

	private static CoordinateNull singleton;

	private CoordinateNull() {
		this.isNull = true;
	}

	public static CoordinateNull getInstance() {
		if (singleton == null) {
			singleton = new CoordinateNull();
		}
		return singleton;
	}

	@Override
	public double getLatitude() throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public double getLongitude() throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public void setLatitude(double latitude) throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public void setLongitude(double longitude) throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public double getRealDistance(Coordinate other) throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public double getLongitudinalDistance(Coordinate other) throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public double getLatitudinalDistance(Coordinate other) throws CoordinateNullException {
		throw new CoordinateNullException();
	}

	@Override
	public double getDistance(Coordinate other) throws CoordinateNullException {
		throw new CoordinateNullException();
	}

}
