package redmagic.handlers;

import redmagic.configuration.PathIndex;
import redmagic.lib.path.*;

public class PathHandler {

	public static void init(){
		PathManager.registerPath(PathIndex.LIFE, LifePath.class);
		PathManager.registerPath(PathIndex.EARTH, EarthPath.class);
		PathManager.registerPath(PathIndex.WATER, WaterPath.class);
	}
	
	
}
