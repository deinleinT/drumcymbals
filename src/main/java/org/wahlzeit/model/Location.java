package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * Location Class
 * 
 * @author ThomasDeinlein
 *
 */
@Entity
public class Location extends DataObject {

	// Datastore values
	@Id
	private String idLong = "location";
	@Parent
	Key parent = ObjectManager.applicationRootKey;
	//

	private static final String NAME_NOT_SET = "Name not set";

	protected String name = NAME_NOT_SET;
	protected Coordinate coordinate = CoordinateFactory.getInstance().createCoordinate(null, null);

	/**
	 * @methodtype constructor
	 */
	public Location() {
	}

	/**
	 * @methodtype constructor
	 */
	public Location(String name, RealCoordinate coordinate) {
		this.name = checkName(name);
		this.coordinate = checkCoordinate(coordinate);
	}

	/**
	 * @param coordinate
	 * @return correct instance of Coordinate
	 * @methodtype assertion
	 */
	private Coordinate checkCoordinate(RealCoordinate coordinate) {
		if (coordinate == null) {
			return CoordinateFactory.getInstance().createCoordinate(null, null);
		} else {
			return null;
		}
	}

	/**
	 * @return value of coordinates name
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype get
	 */
	public String getName() throws NullCoordinateException {
		return name;
	}

	/**
	 * @throws NullCoordinateException
	 *             if this method is executed on NullCoordinate-Object
	 * @methodtype set
	 */
	public void setName(String name) throws NullCoordinateException {
		this.name = checkName(name);
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

}
