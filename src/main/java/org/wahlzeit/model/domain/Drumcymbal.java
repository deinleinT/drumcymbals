package org.wahlzeit.model.domain;

import org.wahlzeit.model.location.Location;
import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Container;

/**
 * @author ThomasDeinlein
 * @version 1.0
 *
 */
public class Drumcymbal extends DataObject {

	// attributes
	/**
	 * reference to the photo to which belongs
	 */
	@Container
	private DrumcymbalPhoto photo;

	/**
	 * reference to DrumcymbalType
	 */
	@Container
	private DrumcymbalType drumcymbalType;

	/**
	 * the manufacturerer of the cymbal
	 */
	private String manufacturer;

	/**
	 * the size of the cymbal
	 */
	private float size;

	/**
	 * the specific name ot the cymbal
	 */
	private String drumcymbalName;

	/**
	 * see at page 11 in ADAP C09 - Type Object Pattern
	 */
	@Container
	private Location location;

	private long instanceId;

	protected static long INSTANCE_ID;
	// end Attributes

	// constructors
	/**
	 * @methodtype constructor
	 */
	Drumcymbal(Location location, String manufacturer, String drumcymbalName, float size, DrumcymbalPhoto photo,
			DrumcymbalType type) {

		// assertParameterNotNull(photo);
		assertParameterNotNull(type);
		assertDrumcymbalSize(size);

		this.location = checkLocation(location);
		this.manufacturer = checkString(manufacturer);
		this.drumcymbalName = checkString(drumcymbalName);
		this.photo = photo;
		this.drumcymbalType = type;
		this.size = size;
		this.instanceId = INSTANCE_ID;
		INSTANCE_ID++;
	}

	// end constructors

	// getter and Setter
	public long getInstanceId() {
		return instanceId;
	}

	/**
	 * @methodtype get
	 */
	public DrumcymbalType getType() {
		return drumcymbalType;
	}

	/**
	 * @methodtype set
	 */
	void setType(DrumcymbalType type) {

		assertParameterNotNull(type);
		this.drumcymbalType = type;
	}

	/**
	 * 
	 * @methodtype get
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @methodtype set
	 */
	void setLocation(Location location) {

		assertParameterNotNull(location);
		this.location = location;
	}

	/**
	 * @methodtype get
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @methodtype set
	 */
	void setManufacturer(String manufacturer) {
		this.manufacturer = checkString(manufacturer);
	}

	/**
	 * @methodtype get
	 */
	public float getSize() {
		return size;
	}

	/**
	 * @methodtype set
	 */
	void setSize(float size) {
		this.size = size;
	}

	/**
	 * @methodtype get
	 */
	public String getDrumcymbalName() {
		return drumcymbalName;
	}

	/**
	 * @methodtype set
	 */
	void setDrumcymbalName(String drumcymbalName) {
		this.drumcymbalName = checkString(drumcymbalName);
	}

	// end getter and setter

	// getter from DrumcymbalType
	/**
	 * @methodtype get
	 */
	public String getDrumcymbalTypeName() {
		return getType().getDrumcymbalTypeName();
	}

	/**
	 * @methodtype get
	 */
	public String getNormalColor() {
		return getType().getNormal_color();
	}

	/**
	 * @methodtype get
	 */
	public boolean getAdditional() {
		return getType().getAdditional();
	}
	//

	// helper methods
	/**
	 * @methodtype helper
	 */
	protected static String checkString(String string) {
		String result = "";
		if (string != null) {
			result = string;
		}
		return result;
	}

	/**
	 * @methodtype helper
	 */
	protected static Location checkLocation(Location location) {
		Location result = new Location(null, null);
		if (location != null) {
			result = location;
		}
		return result;
	}
	// end helper methods

	// assertion methods
	/**
	 * @methodtype assertion
	 */
	protected static void assertParameterNotNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Parameter may be not null.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalSize(float size) {
		if ((size <= 0) || (size >= 50)) {
			throw new IllegalArgumentException("DrumcymbalSize must be > 0 and < 50");
		}
	}
	// end assertion methods

}
