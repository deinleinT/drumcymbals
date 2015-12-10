package org.wahlzeit.model.location.coordinate;

import java.util.HashMap;

import org.wahlzeit.model.NullCoordinateException;

/**
 * Class that represents a Cartesian Coordinate with x-, y- and z-value.
 * 
 * @author ThomasDeinlein
 * 
 *@Pattern (   name = “Abstract Factory”   
 *    participants = {“AbstractProduct”,      “ConcreteProduct”   } )
 *
 */

public class CartesianCoordinate extends AbstractCoordinate {

	private double xValue;
	private double yValue;
	private double zValue;

//	protected static final HashMap<String, CartesianCoordinate> INSTANCES = new HashMap<String, CartesianCoordinate>();

	/**
	 * @param xValue
	 * @param yValue
	 * @param zValue
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double xValue, double yValue, double zValue) {

		assertDoubleNaN(xValue);
		assertDoubleNaN(yValue);
		assertDoubleNaN(zValue);
		
		this.xValue=xValue;
		this.yValue=yValue;
		this.zValue=zValue;

		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	public double getXValue() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return xValue;
	}

	/**
	 * @throws Exception 
	 * @methodtype set
	 */
	public CartesianCoordinate setXValue(double xValue) {

		// Preconditions
		assertDoubleNaN(xValue);

		CartesianCoordinate result = (CartesianCoordinate)getInstance(xValue, getYValue(), getZValue());

		// Postconditions
		assertParameterNotNull(result);
		assertClassInvariants();
		
		return result;
	}

	/**
	 * @methodtype get
	 */
	public double getYValue() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return yValue;
	}

	/**
	 * @throws Exception 
	 * @methodtype set
	 */
	public CartesianCoordinate setYValue(double yValue) {

		// Preconditions
		assertDoubleNaN(yValue);

		CartesianCoordinate result = (CartesianCoordinate)getInstance(getXValue(), yValue, getZValue());
		
		// Postconditions
		assertParameterNotNull(result);
		assertClassInvariants();
		
		return result;
	}

	/**
	 * @methodtype get
	 */
	public double getZValue() {

		// Preconditions
		// none

		// Postconditions
		assertClassInvariants();

		return zValue;
	}

	/**
	 * @throws Exception 
	 * @methodtype set
	 */
	public CartesianCoordinate setZValue(double zValue) throws IllegalStateException,NullCoordinateException {

		// Preconditions
		assertDoubleNaN(zValue);

		CartesianCoordinate result = (CartesianCoordinate)getInstance(getXValue(), getYValue(), zValue);

		// Postconditions
		assertParameterNotNull(result);
		assertClassInvariants();
		
		return result;
	}

	// TODO
	/**
	 *@Methodtype
	 *@Methodproperty
	 */
	static Coordinate getInstance(double xValue, double yValue, double zValue) {
		
		//preconditions
		assertDoubleNaN(xValue);
		assertDoubleNaN(yValue);
		assertDoubleNaN(zValue);
		
		String keyString = doCreateKeyString(xValue,yValue,zValue,CartesianCoordinate.class.getCanonicalName());
		CartesianCoordinate result = (CartesianCoordinate)INSTANCES.get(keyString);
		if (result == null) {
			synchronized (INSTANCES) {
				result = (CartesianCoordinate)INSTANCES.get(keyString);
				if (result == null) {
					result = new CartesianCoordinate(xValue, yValue, zValue);
					INSTANCES.put(keyString, result);
				}
			}
		}
		
		//Postconditions
		assertParameterNotNull(result);
		
		
		
		return result;
	}

	/**
	 * Returns this CartesianCoordinate represented as a SphericCoordinate.
	 * Formula adapted from
	 * {@link http://keisan.casio.com/exec/system/1359533867}
	 * 
	 * @return this CartesianCoordinate represented as a SphericCoordinate
	 * @throws Exception 
	 * 
	 * @methodtype convenience
	 * @methodproperties hook, convenience
	 */
	public SphericCoordinate asSphericCoordinate()  {

		// Preconditions
		// none

		double radius = Math.sqrt(this.getXValue() * this.getXValue() + this.getYValue() * this.getYValue()
				+ this.getZValue() * this.getZValue());

		// THETA
		Double lat = Math.toDegrees(Math.atan(this.getYValue() / this.getXValue()));

		// PHI
		Double lon = Math.toDegrees(
				Math.atan((Math.sqrt(this.getXValue() * this.getXValue() + this.getYValue() * this.getYValue()))
						/ this.getZValue()));

		if (lat.isNaN()) {
			lat = 0.0;
		}
		if (lon.isNaN()) {
			lon = 0.0;
		}

		SphericCoordinate result = CoordinateFactory.getInstance().getSphericCoordinate(lat, lon, radius);

		// Postconditions
		assertParameterIsInstanceOfSphericCoordinate(result);
		assertClassInvariants();

		return result;

	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitve
	 */
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		assertDoubleNaN(this.xValue);
		assertDoubleNaN(this.yValue);
		assertDoubleNaN(this.zValue);
		assertParameterNotNull(INSTANCES);
	}

	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected static void assertParameterIsInstanceOfSphericCoordinate(SphericCoordinate other) {
		if (!(other instanceof SphericCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Spheric Coordinate.");
		}
	}
	
	/**
	 * @methodtype assertion
	 * @methodproperty primitive
	 * 
	 */
	protected static void assertParameterIsInstanceOfCartesianCoordinate(CartesianCoordinate other) {
		if (!(other instanceof CartesianCoordinate)) {
			throw new IllegalArgumentException("Not an Instance of Cartesian Coordinate.");
		}
	}

	
	/**
	 * TODO
	 */
	protected String asString() {

		String result = ""+getXValue()+" "+getYValue()+" "+getYValue();
		
		return result;
	}
	
	@Override
	public int hashCode() {
		return asString().hashCode();
	}

}
