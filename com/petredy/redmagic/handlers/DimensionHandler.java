package com.petredy.redmagic.handlers;

import net.minecraftforge.common.DimensionManager;

import com.petredy.redmagic.dimension.WorldProviderSoul;
import com.petredy.redmagic.lib.Dimensions;

public class DimensionHandler {

	public static void init(){
		Dimensions.SOUL_PROVIDER_ID = 2;
		while(!DimensionManager.registerProviderType(Dimensions.SOUL_PROVIDER_ID, WorldProviderSoul.class, false)){
			Dimensions.SOUL_PROVIDER_ID++;
		}
		
		DimensionManager.registerDimension(Dimensions.DIMENSION_ID, Dimensions.SOUL_PROVIDER_ID);
	}
	
}
