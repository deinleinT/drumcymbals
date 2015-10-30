package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * Class for a Photo of a drumcymbal
 * 
 * @author ThomasDeinlein
 *
 */
@Subclass
public class DrumcymbalPhoto extends Photo {

	private String name = "";
	private int size;

	static {
	}

	/**
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto() {
		super();

	}

	/**
	 * 
	 * @methodtype constructor
	 * @param id
	 */
	public DrumcymbalPhoto(PhotoId id) {
		super(id);
	}

	/**
	 * 
	 * @param id
	 * @methodtype constructor
	 */
	public DrumcymbalPhoto(PhotoId id, String name) {
		super(id);
		this.name = checkIsStringNull(name);
	}

	/**
	 * @return the name
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @methodtype set
	 */
	public void setName(String name) {
		this.name = checkIsStringNull(name);
	}

	/**
	 * @param name
	 *            the String which shall be checked
	 * @methodtype assertion
	 */
	private String checkIsStringNull(String name) {
		return (name == null) ? "" : name;
	}

	/**
	 * @return the CymbalSize
	 * @methodtype get
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param the
	 *            Cymbalsize
	 * @methodtype set
	 */
	public void setSize(int size) {
		assertIsSizeValid(size);
		this.size = size;
	}

	/**
	 * @param size
	 * @methodtype assertion
	 */
	private void assertIsSizeValid(int size) {
		if (size < 2 || size > 30) {
			throw new IllegalArgumentException("There is no Cymbal with such a size.");
		}
	}

}
