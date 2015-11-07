package org.wahlzeit.model;

import java.io.Serializable;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * 
 * Interface for SphericCoordinate, CartesianCoordinate, NullCoordinate
 * 
 * @author ThomasDeinlein
 *
 */
@Entity
public abstract class Coordinate extends DataObject {

	// Values important for DataStore
	@Id
	String coordinateId = "coordinateId";
	@Parent
	Key parent = ObjectManager.applicationRootKey;
	//

	public abstract double getDistance(Coordinate coordinate) throws NullCoordinateException;

	public abstract boolean isEqual(Coordinate coordinate) throws NullCoordinateException;

}
