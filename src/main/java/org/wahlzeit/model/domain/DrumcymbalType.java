package org.wahlzeit.model.domain;

import java.util.HashSet;

import org.wahlzeit.model.location.Location;
import org.wahlzeit.services.DataObject;

/**
 * @author ThomasDeinlein
 * @version 1.0
 *
 */
public class DrumcymbalType extends DataObject {

	// TODO unter- und obertypen

	// attributes
	private String drumcymbalTypeName;
	private boolean additional;
	private String normal_color;

	private DrumcymbalType superType = null;
	private HashSet<DrumcymbalType> subTypes = new HashSet<DrumcymbalType>();

	private HashSet<Drumcymbal> drumcymbalInstances = new HashSet<Drumcymbal>();

	// end attributes

	// constructors
	DrumcymbalType(String name, boolean additional, String normal_color) {
		// no asserts because, see setterMethods for detail
		this.setDrumcymbalTypeName(name);
		this.setAdditional(additional);
		this.setNormal_color(normal_color);
	}
	// end constructors

	// getter and setter
	/**
	 * @methodtype get
	 */
	public String getDrumcymbalTypeName() {
		return drumcymbalTypeName;
	}

	/**
	 * @methodtype set
	 */
	void setDrumcymbalTypeName(String drumcymbalTypeName) {
		this.drumcymbalTypeName = checkString(drumcymbalTypeName);
	}

	/**
	 * @methodtype get
	 */
	public boolean getAdditional() {
		return additional;
	}

	/**
	 * @methodtype set
	 */
	void setAdditional(boolean additional) {
		this.additional = additional;
	}

	/**
	 * @methodtype get
	 */
	public String getNormal_color() {
		return normal_color;
	}

	/**
	 * @methodtype set
	 */
	void setNormal_color(String normal_color) {
		this.normal_color = checkString(normal_color);
	}

	/**
	 * @methodtype get
	 */
	public DrumcymbalType getSuperType() {
		return superType;
	}

	/**
	 * @methodtype set
	 */
	void setSuperType(DrumcymbalType drumcymbalType) {
		assertNotSubType(drumcymbalType);
		this.superType = drumcymbalType;
	}

	/**
	 * @methodtype get
	 */
	public HashSet<Drumcymbal> getDrumcymbalInstances() {
		return drumcymbalInstances;
	}

	/**
	 * @methodtype get
	 */
	public HashSet<DrumcymbalType> getSubTypes() {
		return subTypes;
	}

	/**
	 * @methodtype get
	 */
	void setSubTypes(HashSet<DrumcymbalType> newSubtypes) {
		assertParameterNotNull(newSubtypes);
		this.subTypes = newSubtypes;
	}

	/**
	 * @methodtype get
	 */
	public int getNoSubTypes() {
		return getSubTypes().size();
	}

	/**
	 * @methodtype get
	 */
	public int getNoConnectedInstances() {
		return getDrumcymbalInstances().size();
	}
	// end getter and setter

	// methods
	/**
	 * @methodtype factory
	 */
	protected Drumcymbal createDrumcymbalInstance(Location location, String manufacturer, String drumcymbalName,
			float size, DrumcymbalPhoto photo) {
		Drumcymbal result = new Drumcymbal(location, manufacturer, drumcymbalName, size, photo, this);
		drumcymbalInstances.add(result);

		return result;
	}

	/**
	 * @methodtype command
	 */
	boolean addSubType(DrumcymbalType drumcymbalType) {

		assertParameterNotNull(drumcymbalType);
		assertNotSupertype(drumcymbalType);

		int numberOfSubtypesBefore = getNoSubTypes();
		subTypes.add(drumcymbalType);

		assertNumberOfSubtypes(numberOfSubtypesBefore, getSubTypes().size());

		return true;
	}

	/**
	 * @methodtype command
	 */
	boolean deleteSubType(DrumcymbalType drumcymbalType) {
		assertParameterNotNull(drumcymbalType);
		return getSubTypes().remove(drumcymbalType);
	}

	/**
	 * @methodtype command
	 */
	boolean deleteDrumcymbalInstance(Drumcymbal drumcymbal) {

		assertParameterNotNull(drumcymbal);
		boolean result = drumcymbalInstances.remove(drumcymbal);

		return result;
	}

	/**
	 * @methodtype boolean Query
	 */
	public boolean isInstance(Drumcymbal cymbal) {
		return cymbal.getType() == this;
	}
	// end methods

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
	protected void assertNumberOfSubtypes(int numberOfSubtypesBefore, int size) {
		if (numberOfSubtypesBefore + 1 != size) {
			throw new IllegalStateException("Error during adding subtype to " + getDrumcymbalTypeName() + ".");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertNotSupertype(DrumcymbalType drumcymbalType) {
		if (this.getSuperType() == drumcymbalType) {
			throw new IllegalArgumentException("The DrumcymbalType " + drumcymbalType.getDrumcymbalTypeName()
					+ " already is supertype of the DrumcymbalType: " + this.getDrumcymbalTypeName()
					+ ". Cannot be supertype and subtype both.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertNotSubType(DrumcymbalType drumcymbalType) {
		if (this.getSubTypes().contains(drumcymbalType)) {
			throw new IllegalArgumentException("The DrumcymbalType " + drumcymbalType.getDrumcymbalTypeName()
					+ " already is a subtype of this DrumcymbalType " + this.getDrumcymbalTypeName()
					+ ". Can not be subtype and supertype both.");
		}
	}
	// end assertion methods
}
