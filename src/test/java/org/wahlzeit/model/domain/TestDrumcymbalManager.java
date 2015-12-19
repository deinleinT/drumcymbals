package org.wahlzeit.model.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;
import org.wahlzeit.model.domain.Drumcymbal;
import org.wahlzeit.model.domain.DrumcymbalManager;
import org.wahlzeit.model.domain.DrumcymbalType;
import org.wahlzeit.model.location.Location;

public class TestDrumcymbalManager {

	DrumcymbalManager drumcymbalManager = DrumcymbalManager.getInstance();

	// TODO
	// getDrumcymbalsByDrumcymbalType
	// deleteDrumcymbal

	@Test
	public void TestDrumcymbalInstances() {

		// create Type "crashRide"
		DrumcymbalType chrashRide = drumcymbalManager.createDrumcymbalType("crashRide", false, "red");
		DrumcymbalType ride = drumcymbalManager.getDrumcymbalType(DrumcymbalManager.RIDE);

		// chrashride is subtype of ride
		drumcymbalManager.addSubType(ride, chrashRide);

		Drumcymbal cymbalOne = drumcymbalManager.createDrumcymbal("crashRide", new Location(null, null), "Meinl",
				"Hand Hammered Crash Ride", 21, null);

		assertTrue(cymbalOne.getType().getDrumcymbalInstances().contains(cymbalOne));
		assertTrue(chrashRide.getDrumcymbalInstances().contains(cymbalOne));
		assertTrue(drumcymbalManager.getDrumcymbalsByDrumcymbalType("crashRide").contains(cymbalOne));
		assertTrue(drumcymbalManager.getDrumcymbalsByDrumcymbalType("ride").contains(cymbalOne));

		// add another drumcymbal to crashride
		Drumcymbal cymbalTwo = drumcymbalManager.createDrumcymbal("crashRide", new Location(null, null), "Ufip",
				"Golden Crash Ride", 18, null);

		assertTrue(cymbalTwo.getType().getDrumcymbalInstances().contains(cymbalTwo));
		assertTrue(chrashRide.getDrumcymbalInstances().contains(cymbalTwo));
		assertTrue(drumcymbalManager.getDrumcymbalsByDrumcymbalType("crashRide").contains(cymbalTwo));
		assertTrue(drumcymbalManager.getDrumcymbalsByDrumcymbalType("ride").contains(cymbalTwo));
		assertTrue(chrashRide.getDrumcymbalInstances().size() == 2);

		// delete cymbalTwo
		drumcymbalManager.deleteDrumcymbal(cymbalTwo);

		assertTrue(chrashRide.getDrumcymbalInstances().size() == 1);
		assertTrue(chrashRide.getDrumcymbalInstances().contains(cymbalOne));
		assertFalse(chrashRide.getDrumcymbalInstances().contains(cymbalTwo));

		// getDrumcymbal
		HashSet<Drumcymbal> set = drumcymbalManager.getDrumcymbalsByDrumcymbalType("crashRide");
		assertTrue(set.contains(cymbalOne));
		assertFalse(set.contains(cymbalTwo));
	}

	@Test
	public void TestAddSubTypeAndDeleteSubType() {
		DrumcymbalType testType = drumcymbalManager.createDrumcymbalType("testType", false, "blue");
		DrumcymbalType subType = drumcymbalManager.createDrumcymbalType("subType", true, "red");

		assertTrue(testType.getSubTypes().size() == 0);
		assertTrue(subType.getSubTypes().size() == 0);

		// add subtype to testType
		drumcymbalManager.addSubType(testType, subType);

		assertTrue(testType.getSubTypes().size() == 1);
		assertTrue(subType.getSubTypes().size() == 0);

		testType = drumcymbalManager.getDrumcymbalType("testType");
		subType = drumcymbalManager.getDrumcymbalType("subType");

		assertTrue(testType.getSubTypes().size() == 1);
		assertTrue(subType.getSubTypes().size() == 0);
		assertTrue(testType.getSubTypes().contains(subType));
		assertTrue(subType.getSuperType().equals(testType));

		// delete from testType the subType
		drumcymbalManager.deleteSubTypeFromSupertype(testType, subType);

		assertTrue(testType.getSubTypes().size() == 0);
		assertTrue(subType.getSubTypes().size() == 0);
		assertTrue(testType.getSuperType().equals(drumcymbalManager.getDrumcymbalType(DrumcymbalManager.CYMBAL)));
		assertTrue(subType.getSuperType().equals(drumcymbalManager.getDrumcymbalType(DrumcymbalManager.CYMBAL)));
	}

	@Test
	public void TestAddSuperTypeAndDeleteSuperType() {

		DrumcymbalType testType = drumcymbalManager.createDrumcymbalType("testType", false, "yellow");
		DrumcymbalType superType = drumcymbalManager.createDrumcymbalType("testSupertype", true, "pink");

		// default test
		assertTrue(testType.getSubTypes().size() == 0);
		assertTrue(superType.getSubTypes().size() == 0);
		assertTrue(testType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.CYMBAL));
		assertTrue(superType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.CYMBAL));

		// add supertype to testype
		drumcymbalManager.addSuperType(testType, superType);

		assertTrue(testType.getSubTypes().size() == 0);
		assertTrue(superType.getSubTypes().size() == 1);
		assertTrue(testType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase("testSupertype"));
		assertTrue(superType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.CYMBAL));

		testType = drumcymbalManager.getDrumcymbalType("testType");
		superType = drumcymbalManager.getDrumcymbalType("testSuperType");

		assertTrue(testType.getSubTypes().size() == 0);
		assertTrue(superType.getSubTypes().size() == 1);
		assertTrue(testType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase("testSupertype"));
		assertTrue(superType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.CYMBAL));

		// delete supertype from testType, new supertype is cymbal
		drumcymbalManager.deleteSuperType(testType);

		assertTrue(testType.getSubTypes().size() == 0);
		assertTrue(superType.getSubTypes().size() == 0);
		assertTrue(testType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.CYMBAL));
		assertTrue(superType.getSuperType().getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.CYMBAL));

	}

	@Test(expected = IllegalArgumentException.class)
	public void TestDeleteStandardDrumcymbalType() {
		drumcymbalManager.deleteDrumcymbalType(DrumcymbalManager.CYMBAL);
	}

	@Test
	public void TestDeleteDrumcymbalType() {
		DrumcymbalType newOne = drumcymbalManager.createDrumcymbalType("newOne", false, "black");

		DrumcymbalType testOne = drumcymbalManager.getDrumcymbalType("newOne");

		assertTrue(testOne != null);
		assertTrue(testOne.getDrumcymbalInstances().size() == 0);
		assertTrue(testOne.getDrumcymbalTypeName().equalsIgnoreCase("newOne"));
		assertTrue(testOne.getSuperType().equals(drumcymbalManager.getDrumcymbalType(DrumcymbalManager.CYMBAL)));

		drumcymbalManager.deleteDrumcymbalType("newOne");
		testOne = drumcymbalManager.getDrumcymbalType("newOne");

		assertTrue(testOne == null);
	}

	@Test
	public void TestCreateDrumcymbal() {

		// test factoryMethod
		Drumcymbal drumcymbalOne = drumcymbalManager.createDrumcymbal(DrumcymbalManager.HIHAT, new Location(null, null),
				"", "", 16, null);
		assertTrue(drumcymbalOne.getType().equals(drumcymbalManager.getDrumcymbalType(DrumcymbalManager.HIHAT)));

		Drumcymbal drumcymbalTwo = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CRASH, new Location(null, null),
				"Zildjian", "gold", 18, null);
		assertTrue(drumcymbalTwo.getType().equals(drumcymbalManager.getDrumcymbalType(DrumcymbalManager.CRASH)));

	}

	@Test
	public void TestCreateDrumcymbalType() {

		// test factoryMethod
		DrumcymbalType cymbal = drumcymbalManager.createDrumcymbalType(DrumcymbalManager.RIDE, true, "");
		assertTrue(cymbal.getDrumcymbalInstances().size() == 0);

	}

	@Test
	public void TestNumberOfInstances() {
		// test drumcymbalInstances
		Drumcymbal drumcymbalThree = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CRASH,
				new Location(null, null), "Zildjian", "Dark China", 18, null);
		Drumcymbal drumcymbalFour = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CRASH,
				new Location(null, null), "Zildjian", "Smooth China", 18, null);
		Drumcymbal drumcymbalFive = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CRASH,
				new Location(null, null), "Zildjian", "Rock China", 18, null);

		DrumcymbalType china = drumcymbalManager.getDrumcymbalType(DrumcymbalManager.CRASH);
		assertEquals(3, china.getNoConnectedInstances(), 0);
		assertEquals(3, china.getDrumcymbalInstances().size(), 0);
		assertEquals(3, drumcymbalManager.getDrumcymbalsByDrumcymbalType(DrumcymbalManager.CRASH).size(), 0);
	}

	@Test
	public void TestDeleteDrumcymbal() {

		Drumcymbal drumcymbalThree = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CHINA,
				new Location(null, null), "Meinl", "Jazz China", 18, null);
		Drumcymbal drumcymbalFour = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CHINA,
				new Location(null, null), "Meinl", "Ultra China", 18, null);
		Drumcymbal drumcymbalFive = drumcymbalManager.createDrumcymbal(DrumcymbalManager.CHINA,
				new Location(null, null), "Meinl", "Dark China", 18, null);

		assertEquals(3, drumcymbalManager.getDrumcymbalsByDrumcymbalType(DrumcymbalManager.CHINA).size());

		drumcymbalManager.deleteDrumcymbal(drumcymbalThree);

		assertEquals(2, drumcymbalManager.getDrumcymbalType(DrumcymbalManager.CHINA).getNoConnectedInstances());

	}

	@Test
	public void TestGetDrumcymbalType() {

		DrumcymbalType cymbal = drumcymbalManager.getDrumcymbalType(DrumcymbalManager.RIDE);
		assertTrue(cymbal.getDrumcymbalTypeName().equalsIgnoreCase(DrumcymbalManager.RIDE));
	}

}
