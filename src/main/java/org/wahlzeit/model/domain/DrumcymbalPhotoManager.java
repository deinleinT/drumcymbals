package org.wahlzeit.model.domain;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;

/**
 * @author Thomas Deinlein
 * @version 1.0
 *
 */
public class DrumcymbalPhotoManager extends PhotoManager {

	protected static final PhotoManager instance = new DrumcymbalPhotoManager();

	public DrumcymbalPhotoManager() {
		photoTagCollector = DrumcymbalPhotoFactory.getInstance().createPhotoTagCollector();
	}

	/**
	 * @methodtype factory
	 */
	public static final PhotoManager getInstance() {
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.model.PhotoManager#getPhoto(org.wahlzeit.model.PhotoId)
	 */
	public final Photo getPhoto(PhotoId id) {
		return instance.getPhotoFromId(id);
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.model.PhotoManager#hasPhoto(org.wahlzeit.model.PhotoId)
	 */
	public final boolean hasPhoto(PhotoId id) {
		return getPhoto(id) != null;
	}

}
