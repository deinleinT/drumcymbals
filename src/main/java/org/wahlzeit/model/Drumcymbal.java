package org.wahlzeit.model;

import org.wahlzeit.model.location.Location;

import com.googlecode.objectify.annotation.Container;

public class Drumcymbal implements PhotoInstance {

	// added in the context of adap-hw03 + adap-hw04 + adap-hw05
	@Container
	protected Location location;
	@Container
	protected Photo photo;
	@Container
	protected DrumcymbalType type;

	public Drumcymbal() {
		location = new Location(null, null);
	}

	/**
	 * @methodtype get
	 */
	public DrumcymbalType getType() {
		return type;
	}

	/**
	 * @methodtype set
	 */
	public void setType(DrumcymbalType type) {
		this.type = type;
	}

	/**
	 * @return the location
	 * @methodtype get
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 * @methodtype set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	//

}
