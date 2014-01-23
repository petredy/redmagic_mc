package com.petredy.redmagic.forge.helper;

import cpw.mods.fml.common.FMLCommonHandler;

public class EventHelper {

	public static void register(Object object){
		FMLCommonHandler.instance().bus().register(object);
	}
	
}
