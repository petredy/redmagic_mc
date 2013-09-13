package com.petredy.redmagic.handlers;

import com.petredy.redmagic.tileentities.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityFurnace.class, "redmagic.TileEntityFurnace");
		GameRegistry.registerTileEntity(TileEntityEngine.class, "redmagic.TileEntityEngine");
		GameRegistry.registerTileEntity(TileEntityTradingChest.class, "redmagic.TileEntityTradingChest");
		GameRegistry.registerTileEntity(TileEntitySoulCatcher.class, "redmagic.TileEntitySoulCatcher");
		GameRegistry.registerTileEntity(TileEntitySoulCatcherStorage.class, "redmagic.TileEntitySoulCatcherPlug");
	}
	
}
