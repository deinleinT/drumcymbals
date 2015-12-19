package org.wahlzeit.model.domain;

import java.util.HashMap;
import java.util.HashSet;

import org.wahlzeit.model.location.Location;

/**
 * @author ThomasDeinlein
 * @version 1.0
 */
public class DrumcymbalManager {

	private static DrumcymbalManager INSTANCE = null;

	public static final String HIHAT = "hihat";
	public static final String CYMBAL = "cymbal";
	public static final String RIDE = "ride";
	public static final String CRASH = "crash";
	public static final String CHINA = "china";

	/**
	 * all created drumcymbaltypes
	 */
	private final HashMap<String, DrumcymbalType> drumcymbalTypes = new HashMap<String, DrumcymbalType>();
	/**
	 * all created instances of drumcymbals
	 */
	private final HashMap<Long, Drumcymbal> drumcymbals = new HashMap<Long, Drumcymbal>();

	/**
	 * @methodtype constructor
	 */
	private DrumcymbalManager() {
		createBasicDrumcymbalTypes();
	}

	/**
	 * @methodtype initialization
	 */
	private void createBasicDrumcymbalTypes() {
		createDrumcymbalType(CYMBAL, false, "");
		createDrumcymbalType(HIHAT, true, "golden");
		createDrumcymbalType(RIDE, true, "dark golden");
		createDrumcymbalType(CRASH, true, "shiny golden");
		createDrumcymbalType(CHINA, true, "dark copper");

	}

	/**
	 * @methodtype get
	 */
	public static synchronized DrumcymbalManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DrumcymbalManager();

		}

		return INSTANCE;
	}

	/**
	 * @methodtype factory
	 */
	public synchronized Drumcymbal createDrumcymbal(String drumcymbalTypeName, Location location, String manufacturer,
			String drumcymbalName, float size, DrumcymbalPhoto photo) {

		assertParameterNotNull(drumcymbalTypeName);
		assertParameterNotNull(location);
		assertParameterNotNull(manufacturer);
		assertParameterNotNull(drumcymbalName);
		assertParameterNotNull(photo);

		int sizeHashMap = drumcymbals.size();
		DrumcymbalType drumcymbalType = getDrumcymbalType(drumcymbalTypeName.toLowerCase());
		assertParameterNotNull(drumcymbalType);

		Drumcymbal drumcymbal = drumcymbalType.createDrumcymbalInstance(location, manufacturer, drumcymbalName, size,
				photo);
		drumcymbals.put(drumcymbal.getInstanceId(), drumcymbal);

		assertParameterNotNull(drumcymbal);
		assertDrumcymbalsSizeAdd(sizeHashMap, drumcymbals);

		return drumcymbal;
	}

	/**
	 * @methodtype factory
	 */
	public synchronized DrumcymbalType createDrumcymbalType(String drumcymbalTypeName, boolean additional,
			String normal_color) {

		assertParameterNotNull(drumcymbalTypeName);
		assertParameterNotNull(normal_color);

		DrumcymbalType result = null;

		if (drumcymbalTypes.isEmpty()) {
			result = new DrumcymbalType(drumcymbalTypeName, additional, normal_color);
			drumcymbalTypes.put(drumcymbalTypeName.toLowerCase(), result);
			return result;
		}

		result = getDrumcymbalType(drumcymbalTypeName.toLowerCase());

		if (result == null) {
			result = new DrumcymbalType(drumcymbalTypeName, additional, normal_color);
			DrumcymbalType supertype = getDrumcymbalType(CYMBAL);
			drumcymbalTypes.put(drumcymbalTypeName.toLowerCase(), result);
			addSuperType(result, supertype);
			drumcymbalTypes.put(drumcymbalTypeName.toLowerCase(), result);
		}

		assertParameterNotNull(result);

		return result;
	}

	/**
	 * @methodtype command
	 */
	public synchronized boolean deleteDrumcymbal(Drumcymbal drumcymbal) {

		assertParameterNotNull(drumcymbal);
		assertDrumcymbalAvailable(drumcymbal);

		int size = drumcymbals.size();
		DrumcymbalType type = drumcymbal.getType();

		// delete instance from type
		type.deleteDrumcymbalInstance(drumcymbal);

		// update the hashmaps
		drumcymbalTypes.put(type.getDrumcymbalTypeName().toLowerCase(), type);
		drumcymbals.remove(drumcymbal.getInstanceId());

		assertDrumcymbalsSizeDelete(size, drumcymbals);

		return true;
	}

	/**
	 * @methodtype command
	 */
	public synchronized void deleteDrumcymbalType(String drumcymbalTypeName) {
		assertParameterNotNull(drumcymbalTypeName);
		assertDrumcymbalTypeIsAvailable(drumcymbalTypeName, drumcymbalTypes);
		assertNotAStandardDrumcymbalType(drumcymbalTypeName);

		// original size drumcymbalTypes HashMap
		int size = drumcymbalTypes.size();

		// get type, supertype and subtypes
		DrumcymbalType drumcymbalType = getDrumcymbalType(drumcymbalTypeName);
		DrumcymbalType supertype = drumcymbalType.getSuperType();
		HashSet<DrumcymbalType> subtypes = drumcymbalType.getSubTypes();

		// delete all supertypes
		supertype.deleteSubType(drumcymbalType);
		drumcymbalTypes.put(supertype.getDrumcymbalTypeName().toLowerCase(), supertype);

		// delete from subtypes the supertype and set to cymbal
		for (DrumcymbalType type : subtypes) {

			deleteSuperType(type);
			drumcymbalTypes.put(type.getDrumcymbalTypeName().toLowerCase(), type);

		}

		drumcymbalTypes.remove(drumcymbalTypeName.toLowerCase());

		assertDrumcymbalTypesSizeAfterRemove(size, drumcymbalTypes);
	}

	/**
	 * @methodtype get
	 */
	public DrumcymbalType getDrumcymbalType(String drumcymbalTypeName) {
		assertParameterNotNull(drumcymbalTypeName);

		return drumcymbalTypes.get(drumcymbalTypeName.toLowerCase());
	}

	/**
	 * adds a supertype to param one
	 * 
	 * @methodtype command
	 */
	public synchronized boolean addSuperType(DrumcymbalType drumcymbalType_with_no_supertype,
			DrumcymbalType supertype) {
		assertDrumcymbalTypeIsAvailable(drumcymbalType_with_no_supertype.getDrumcymbalTypeName(), drumcymbalTypes);
		assertDrumcymbalTypeIsAvailable(supertype.getDrumcymbalTypeName().toLowerCase(), drumcymbalTypes);
		assertBothTypesNotEqual(drumcymbalType_with_no_supertype, supertype);

		// delete from old supertype the subtype
		DrumcymbalType recentSupertype = drumcymbalType_with_no_supertype.getSuperType();
		if (recentSupertype != null) {
			recentSupertype.deleteSubType(drumcymbalType_with_no_supertype);
		}

		// set supertype, add subtype, update hashmap
		drumcymbalType_with_no_supertype.setSuperType(supertype);
		supertype.addSubType(drumcymbalType_with_no_supertype);
		drumcymbalTypes.put(drumcymbalType_with_no_supertype.getDrumcymbalTypeName().toLowerCase(),
				drumcymbalType_with_no_supertype);
		drumcymbalTypes.put(supertype.getDrumcymbalTypeName().toLowerCase(), supertype);

		assertHasSupertype(drumcymbalType_with_no_supertype, supertype);
		assertHasSubType(supertype, drumcymbalType_with_no_supertype);

		return true;
	}

	/**
	 * deletes the supertype, and sets supertype to type CYMBAL
	 * 
	 * @methodtype command
	 */
	public synchronized boolean deleteSuperType(DrumcymbalType drumcymbalType_with_supertype) {
		assertDrumcymbalTypeIsAvailable(drumcymbalType_with_supertype.getDrumcymbalTypeName(), drumcymbalTypes);

		// get the supertype, set to null, delete subtype from supertype
		DrumcymbalType supertype = drumcymbalType_with_supertype.getSuperType();
		drumcymbalType_with_supertype.setSuperType(null);
		supertype.deleteSubType(drumcymbalType_with_supertype);
		drumcymbalTypes.put(supertype.getDrumcymbalTypeName().toLowerCase(), supertype);

		// get cymbal, set supertype to cymbal, add subtype to cymbal
		DrumcymbalType cymbal = getDrumcymbalType(CYMBAL);
		drumcymbalType_with_supertype.setSuperType(cymbal);
		cymbal.addSubType(drumcymbalType_with_supertype);

		// update hashmap
		drumcymbalTypes.put(cymbal.getDrumcymbalTypeName().toLowerCase(), cymbal);
		drumcymbalTypes.put(drumcymbalType_with_supertype.getDrumcymbalTypeName().toLowerCase(),
				drumcymbalType_with_supertype);

		assertSubTypeDeleted(supertype, drumcymbalType_with_supertype);

		return true;
	}

	/**
	 * adds a subType to param one
	 * 
	 * @methodtype command
	 */
	public synchronized boolean addSubType(DrumcymbalType supertype, DrumcymbalType subtype) {
		assertDrumcymbalTypeIsAvailable(supertype.getDrumcymbalTypeName(), drumcymbalTypes);
		assertDrumcymbalTypeIsAvailable(subtype.getDrumcymbalTypeName(), drumcymbalTypes);

		// add subtype to supertype and update hashmap
		supertype.addSubType(subtype);
		drumcymbalTypes.put(supertype.getDrumcymbalTypeName().toLowerCase(), supertype);

		// get the supertype of the subtype before setting and update
		DrumcymbalType subtypeSupertype = subtype.getSuperType();
		// delete from subtypeSupertype the subtype
		subtypeSupertype.deleteSubType(subtype);
		// set the new supertype
		subtype.setSuperType(supertype);
		// update hashmap
		drumcymbalTypes.put(subtypeSupertype.getDrumcymbalTypeName().toLowerCase(), subtypeSupertype);
		drumcymbalTypes.put(subtype.getDrumcymbalTypeName().toLowerCase(), subtype);

		assertHasSubType(supertype, subtype);
		assertHasSupertype(subtype, supertype);

		return true;
	}

	/**
	 * deletes from param one the subtype param two
	 * 
	 * @methodtype command
	 */
	public synchronized boolean deleteSubTypeFromSupertype(DrumcymbalType supertype, DrumcymbalType subtype) {
		assertDrumcymbalTypeIsAvailable(supertype.getDrumcymbalTypeName(), drumcymbalTypes);
		assertDrumcymbalTypeIsAvailable(subtype.getDrumcymbalTypeName(), drumcymbalTypes);
		assertIsSubTypeFromSupertype(supertype, subtype);

		// delete subtype from supertype and update hashmap
		supertype.deleteSubType(subtype);
		drumcymbalTypes.put(supertype.getDrumcymbalTypeName().toLowerCase(), supertype);

		// set subtype's supertype to cymbal
		DrumcymbalType cymbal = getDrumcymbalType(CYMBAL);
		subtype.setSuperType(cymbal);
		cymbal.addSubType(subtype);
		drumcymbalTypes.put(cymbal.getDrumcymbalTypeName().toLowerCase(), cymbal);
		drumcymbalTypes.put(subtype.getDrumcymbalTypeName().toLowerCase(), subtype);

		assertSubTypeDeleted(supertype, subtype);

		return true;
	}

	/**
	 * @methodtype get
	 */
	public HashSet<Drumcymbal> getDrumcymbalsByDrumcymbalType(String drumcymbalTypeName) {
		assertDrumcymbalTypeIsAvailable(drumcymbalTypeName, drumcymbalTypes);

		HashSet<Drumcymbal> result = new HashSet<Drumcymbal>();

		DrumcymbalType type = getDrumcymbalType(drumcymbalTypeName);
		HashSet<DrumcymbalType> subTypes = type.getSubTypes();
		result.addAll(type.getDrumcymbalInstances());

		for (DrumcymbalType subtype : subTypes) {
			result.addAll(subtype.getDrumcymbalInstances());
			for (DrumcymbalType subsubtype : subtype.getSubTypes()) {
				result.addAll(subsubtype.getDrumcymbalInstances());
			}
		}

		assertParameterNotNull(result);

		return result;

	}

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
	protected static void assertDrumcymbalTypeIsAvailable(String drumcymbalTypeName,
			HashMap<String, DrumcymbalType> drumcymbalTypes) {
		if (drumcymbalTypes.get(drumcymbalTypeName.toLowerCase()) == null) {
			throw new IllegalArgumentException("DrumcymbalType " + drumcymbalTypeName + " not available.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalTypeIsNotAvailable(String drumcymbalTypeName,
			HashMap<String, DrumcymbalType> drumcymbalTypes) {
		if (drumcymbalTypes.get(drumcymbalTypeName.toLowerCase()) != null) {
			throw new IllegalArgumentException("DrumcymbalType " + drumcymbalTypeName + " already exists.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalTypeAlreadyHasSupertype(DrumcymbalType drumcymbalTypeToCheck) {
		if (drumcymbalTypeToCheck.getSuperType() != null) {
			throw new IllegalStateException("DrumcymbalType " + drumcymbalTypeToCheck.getDrumcymbalTypeName()
					+ " still has a supertype " + drumcymbalTypeToCheck.getSuperType().getDrumcymbalTypeName());
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalTypeHasNoSupertype(DrumcymbalType drumcymbalTypeToCheck) {
		if (drumcymbalTypeToCheck.getSuperType() == null) {
			throw new IllegalStateException("DrumcymbalType " + drumcymbalTypeToCheck.getDrumcymbalTypeName()
					+ " still has a supertype " + drumcymbalTypeToCheck.getSuperType().getDrumcymbalTypeName());
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertDrumcymbalAvailable(Drumcymbal drumcymbal) {
		Drumcymbal check = drumcymbals.get(drumcymbal.getInstanceId());
		if (check == null) {
			throw new IllegalArgumentException(
					"The Drumcymbal with name " + drumcymbal.getDrumcymbalName() + " does not exist.");
		}
	}

	/**
	 * @methodtye assertion
	 */
	protected static void assertNotAStandardDrumcymbalType(String drumcymbalTypeName) {
		if (drumcymbalTypeName.equalsIgnoreCase(CHINA) || drumcymbalTypeName.equalsIgnoreCase(CRASH)
				|| drumcymbalTypeName.equalsIgnoreCase(CYMBAL) || drumcymbalTypeName.equalsIgnoreCase(HIHAT)
				|| drumcymbalTypeName.equalsIgnoreCase(RIDE)) {
			throw new IllegalArgumentException(
					"Not allowed to delete the standard type " + drumcymbalTypeName.toLowerCase() + "!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalsSizeAdd(int size, HashMap<Long, Drumcymbal> drumcymbals) {
		if (size + 1 != drumcymbals.size()) {
			throw new IllegalStateException("Error during deleting Drumcymbal. No Drumcymbal was removed!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalsSizeDelete(int size, HashMap<Long, Drumcymbal> drumcymbals) {
		if (size - 1 != drumcymbals.size()) {
			throw new IllegalStateException("Error during deleting Drumcymbal. No Drumcymbal was removed!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertBothTypesNotEqual(DrumcymbalType drumcymbalType_with_no_supertype,
			DrumcymbalType supertype) {
		if (drumcymbalType_with_no_supertype.equals(supertype)) {
			throw new IllegalArgumentException("It is not allowed to set the supertype with its own instance of type!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertDrumcymbalTypesSizeAfterRemove(int size,
			HashMap<String, DrumcymbalType> drumcymbalTypes) {
		if (size - 1 != drumcymbalTypes.size()) {
			throw new IllegalStateException(
					"Error during deleting DrumcymbalType. size " + (size - 1) + " dcsize " + drumcymbalTypes.size());
		}

	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertSubTypeDeleted(DrumcymbalType supertype, DrumcymbalType drumcymbalType) {
		if (supertype.getSubTypes().contains(drumcymbalType)) {
			throw new IllegalStateException("Error during deleting subtype " + drumcymbalType.getDrumcymbalTypeName()
					+ " from Supertype " + supertype.getDrumcymbalTypeName() + "!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertSuperTypeIsNull(DrumcymbalType drumcymbalType) {
		if (drumcymbalType.getSuperType() != null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertIsSubTypeFromSupertype(DrumcymbalType supertype, DrumcymbalType subtype) {
		if (subtype.getSuperType() != supertype || !(supertype.getSubTypes().contains(subtype))) {
			throw new IllegalStateException("The Type " + subtype.getDrumcymbalTypeName() + " is not a subtype from "
					+ supertype.getDrumcymbalTypeName() + "!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertHasSubType(DrumcymbalType supertype, DrumcymbalType drumcymbalType) {
		if (!supertype.getSubTypes().contains(drumcymbalType)) {
			throw new IllegalStateException(
					"Error during adding supertype in type " + drumcymbalType.getDrumcymbalTypeName() + "!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected static void assertHasSupertype(DrumcymbalType drumcymbalType, DrumcymbalType supertype) {
		if (!drumcymbalType.getSuperType().equals(supertype)) {
			throw new IllegalStateException(
					"Error during adding supertype in type " + drumcymbalType.getDrumcymbalTypeName() + "!");
		}
	}
	// end assertion methods
}
