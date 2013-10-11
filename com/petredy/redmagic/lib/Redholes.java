package com.petredy.redmagic.lib;

import com.petredy.redmagic.redhole.Hole;
import com.petredy.redmagic.redhole.HolePortable;
import com.petredy.redmagic.redhole.HoleStoreable;

public class Redholes {

	public static final String MOD_PREFIX = "redmagic.hole";
	
	public static Class[] HOLES = new Class[]{
		Hole.class,
		HoleStoreable.class,
		HolePortable.class
	};
	
	
	public static final String STOREABLE_NAME = "storeable";
	public static final int STOREABLE_ID = 1;

	public static final String PORTABLE_NAME = "portable";
	public static final int PORTABLE_ID = 2;
}
