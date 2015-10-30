package org.wahlzeit.model;

/**
 * Factory to get a correct instance of a Coordinate
 * 
 * @author ThomasDeinlein
 *
 */
public class CoordinateFactory {
	
	private static CoordinateFactory singleton = null;

	/**
	 * @methodtype private default-constructor for singleton-implementation
	 */
	private CoordinateFactory() {
	}
	
	
	/**
	 * @return Singleton-Instance of CoordinateFactory
	 * 
	 * @methodtype factory
	 */
	public static synchronized CoordinateFactory getInstance(){
		if(singleton==null){
			singleton = new CoordinateFactory();
		}
		
		return singleton;
	}

	/**
	 * Factory-method
	 * 
	 * @param latitude
	 * @param longitude
	 * @return AbstractCoordinate-Object, if one parameter is null, this method
	 *         returns a CoordinateNull-Object (NullObject), if both parameter
	 *         are double-values, this method returns a Coordinate-Object
	 */
	public Coordinate createCoordinate(Double latitude, Double longitude) {
		if (latitude == null || longitude == null) {
			return NullCoordinate.getInstance();
		} else {
			return new RealCoordinate(latitude, longitude);
		}
	}

}
