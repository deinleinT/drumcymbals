package org.wahlzeit.model.domain;

import java.util.Date;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.location.Location;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Subclass;

/**
 * Class for a Photo of a drumcymbal
 * 
 * @author ThomasDeinlein
 * 
 * 		@Pattern (   name = “Abstract Factory”   participants = {
 *             “AbstractProduct”,      “ConcreteProduct”   } )
 *
 */
@Subclass(index = true)
public class DrumcymbalPhoto extends Photo {

	@Container
	protected Date timeTaken;
	@Container
	protected Drumcymbal drumcymbal;

	// constructors
	/**
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto() {
		super();

		this.timeTaken = new Date();
		this.name = "";
		this.drumcymbal = DrumcymbalManager.getInstance().createDrumcymbal("hihat", getLocation(), "Zildjian", "copper",
				14, this);
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto(PhotoId id) {
		super(id);
		assertParameterNotNull(id);
		this.timeTaken = new Date();
		this.name = "";
		this.drumcymbal = DrumcymbalManager.getInstance().createDrumcymbal("ride", getLocation(), "Meinl", "bronce", 20,
				this);
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto(PhotoId id, String name, Date date, Location location, String drumcymbalTypeName) {
		super(id);
		assertParameterNotNull(id);
		assertParameterNotNull(date);
		this.name = checkIsStringNull(name);
		this.timeTaken = date;
		this.drumcymbal = DrumcymbalManager.getInstance().createDrumcymbal(checkIsStringNull(drumcymbalTypeName),
				getLocation(), "Zildjian", "bronce", 14, this);
		this.drumcymbal.setLocation(location);
	}
	// end constructors

	// getter and setter
	public Date getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Date timeTaken) {
		assertParameterNotNull(timeTaken);
		this.timeTaken = timeTaken;
	}
	// end getters and setters

	// assertion methods
	/**
	 * @methodtype assertion
	 */
	protected static void assertParameterNotNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Parameter may be not null.");
		}
	}

	// end assertion methods
}
