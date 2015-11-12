package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import com.googlecode.objectify.annotation.Container;

/**
 * Encapsulates a Location with a name and a Coordinate.
 * 
 * @author ThomasDeinlein
 *
 */

public class Location extends DataObject {

	private static final String NAME_NOT_SET = "Name not set";

	protected String name = NAME_NOT_SET;
	@Container
	protected Coordinate coordinate = CoordinateFactory.getInstance().createNullCoordinate();

	/**
	 * @methodtype constructor
	 */
	public Location() {
		coordinate = CoordinateFactory.getInstance().createNullCoordinate();
	}

	/**
	 * @methodtype constructor
	 */
	public Location(String name, Coordinate coordinate) {
		this.name = checkName(name);
		this.coordinate = checkCoordinate(coordinate);
	}

	/**
	 * @return value of coordinates name
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setName(String name) {
		this.name = checkName(name);
	}

	/**
	 * @return
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = checkCoordinate(coordinate);
	}

	/**
	 * Returns true, if name and coordinate of otherLocation are equals to this
	 * location, otherwise false. Uses isEqual-Method from Coordinate-Classes.
	 * 
	 * @param otherLocation
	 *            the location to compare with
	 * @return true if name and coordinate of otherLocation are equals to this
	 *         location
	 * @throws NullCoordinateException
	 *             if otherLocation is instance of NullCoordinate
	 * @methodtype boolean
	 * @methodproperties regular
	 */
	public boolean isEqual(Location otherLocation) throws NullCoordinateException {
		assertIsParameterNull(otherLocation);

		if (this.equals(otherLocation)) {
			return true;
		}

		if (this.getName().equals(otherLocation.getName())) {
			if (this.getCoordinate().isEqual(otherLocation.getCoordinate())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * If name is null, the String "Name not set" will be returned.
	 * 
	 * @param name
	 * @return "Name not set" if name is null
	 * 
	 * @methodtype assertion
	 */
	private String checkName(String name) {
		return (name == null) ? (this.name = NAME_NOT_SET) : (this.name = name);
	}

	/**
	 * @param otherLocation
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	private void assertIsParameterNull(Location otherLocation) throws IllegalArgumentException {
		if (otherLocation == null) {
			throw new IllegalArgumentException("Error. Null-Parameter is not allowed in this context.");
		}
	}

	/**
	 * Checks whether the parameter coordinate is null. If it is null, a
	 * NullCoordinate-Object will be returned.
	 * 
	 * @param coordinate
	 * @return correct instance of Coordinate
	 * @methodtype assertion
	 */
	private Coordinate checkCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			return CoordinateFactory.getInstance().createNullCoordinate();
		} else {
			return coordinate;
		}
	}
}
