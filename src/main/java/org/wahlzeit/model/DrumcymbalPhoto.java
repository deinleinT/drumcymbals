package org.wahlzeit.model;

import java.util.Date;

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

	/**
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto() {
		super();
		photoInstance = new Drumcymbal();
	}

	/**
	 * 
	 * @methodtype constructor
	 * @param id
	 */
	public DrumcymbalPhoto(PhotoId id) {
		super(id);
		photoInstance = new Drumcymbal();
	}

	/**
	 * 
	 * @param id
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto(PhotoId id, String name, Date date) {
		super(id);
		this.name = checkIsStringNull(name);
		this.timeTaken = date;
		photoInstance = new Drumcymbal();
	}

}
