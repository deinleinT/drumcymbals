package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

/**
 * Factory to create DrumcymbalPhotos
 * 
 * @author ThomasDeinlein @Pattern (   name = “Abstract Factory”
 *           participants = {     “AbstractFactory”,      “ConcreteFactory”   }
 *         )
 * 
 * @Pattern ( name = "Singleton")
 *
 */
public class DrumcymbalPhotoFactory extends PhotoFactory {

	private static PhotoFactory INSTANCE = null;

	/**
	 * @methodtype constructor
	 */
	private DrumcymbalPhotoFactory() {
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (INSTANCE == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
			setInstance(new DrumcymbalPhotoFactory());
		}
		return INSTANCE;
	}

	/**
	 * @methodtype initialize
	 */
	public static void initialize() {
		// getInstance(); // drops result due to getInstance() side-effects
		DrumcymbalPhotoFactory.getInstance();
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 * 
	 * @methodtype assertion
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) {
		if (INSTANCE != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		INSTANCE = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new DrumcymbalPhoto();
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new DrumcymbalPhoto(id);
	}

}
