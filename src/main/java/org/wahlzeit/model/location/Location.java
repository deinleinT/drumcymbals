package org.wahlzeit.model.location;

import org.wahlzeit.model.NullCoordinateException;
import org.wahlzeit.model.location.coordinate.Coordinate;
import org.wahlzeit.model.location.coordinate.CoordinateFactory;
import org.wahlzeit.services.DataObject;
import com.googlecode.objectify.annotation.Container;

/**
 * Encapsulates a Location with a name and a Coordinate.
 * 
 * @author ThomasDeinlein
 * @version 2.0
 */

public class Location extends DataObject {

	private static final String NAME_NOT_SET = "Name not set";

	protected String name = NAME_NOT_SET;
	@Container
	protected Coordinate coordinate = CoordinateFactory.getInstance().getNullCoordinate();

	/**
	 * @methodtype constructor
	 */
	public Location() {
		coordinate = CoordinateFactory.getInstance().getNullCoordinate();
		assertClassInvariants();
	}

	/**
	 * @methodtype constructor
	 */
	public Location(String name, Coordinate coordinate) {
		this.name = checkName(name);
		this.coordinate = checkCoordinate(coordinate);
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public String getName() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return name;
	}

	/**
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setName(String name) {

		// Preconditions
		assertParameterNotNull(name);

		this.name = checkName(name);

		// Postconditions
		assertClassInvariants();
	}

	/**
	 * @return
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return coordinate;
	}

	/**
	 * @param coordinate
	 *            null is allowed, the coordinate will be set to
	 *            NullCoordinate-Object
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate coordinate) {

		// Preconditions
		// assertParameterNotNull --> not necessary, see method checkCoordinate
		assertParameterInstanceOfCoordinate(coordinate);

		this.coordinate = checkCoordinate(coordinate);

		// Postconditions
		assertClassInvariants();
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

		// Preconditions
		assertParameterNotNull(otherLocation);

		boolean result = false;

		if (this.equals(otherLocation)) {
			return true;
		}

		if (this.getName().equals(otherLocation.getName())) {
			if (this.getCoordinate().isEqual(otherLocation.getCoordinate())) {
				result = true;

			} else {
				result = false;
			}
		} else {
			result = false;
		}

		// Postconditions
		assertClassInvariants(); 

		return result;
	}

	/**
	 * If name is null, the String "Name not set" will be returned.
	 * 
	 * @param name
	 * @return "Name not set" if name is emptyString
	 * 
	 * @methodtype command
	 * @methodproperty primitive
	 */
	protected String checkName(String name) {
		
		if(name==null){
			name="";
		}
		
		return (name.isEmpty()) ? (this.name = NAME_NOT_SET) : (this.name = name);
	}

	/**
	 * @param otherLocation
	 * @methodtype assertion
	 * @methodproperties primitive
	 */
	protected void assertParameterNotNull(Object otherLocation) throws IllegalArgumentException {
		if (otherLocation == null) {
			throw new IllegalArgumentException("Error. Null-Parameter is not allowed in this context.");
		}
	}

	/**
	 * @param otherLocation
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertParameterInstanceOfCoordinate(Coordinate coordinate) {
		if (!(coordinate instanceof Coordinate)) {
			throw new IllegalArgumentException("Parameter is not a Coordinate-Object.");
		}
	}

	/**
	 * Checks whether the parameter coordinate is null. If it is null, a
	 * NullCoordinate-Object will be returned.
	 * 
	 * @param coordinate
	 * @return correct instance of Coordinate
	 * @methodtype command
	 * @methodproperty primitve
	 */
	protected Coordinate checkCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			return CoordinateFactory.getInstance().getNullCoordinate();
		} else {
			return coordinate;
		}
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 */
	protected void assertClassInvariants() {
		assertParameterNotNull(this.name);
		assertParameterInstanceOfCoordinate(this.coordinate);
	}
}
