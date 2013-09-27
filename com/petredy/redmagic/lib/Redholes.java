package com.petredy.redmagic.lib;

import com.petredy.redmagic.redhole.HoleDimension;
import com.petredy.redmagic.redhole.HoleStoreable;

public class Redholes {

	public static final String MOD_PREFIX = "redmagic.hole";
	
	public static Class[] HOLES = new Class[]{
		HoleStoreable.class,
		HoleDimension.class
	};
	
	
	public static final String STOREABLE_NAME = "storeable";
	public static final int STOREABLE_ID = 0;

	public static final String DIMENSION_NAME = "dimension";
	public static final int DIMENSION_ID = 1;
}
