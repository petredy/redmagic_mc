package com.petredy.redmagic.handlers;

import com.petredy.redmagic.tileentities.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityTradingChest.class, "redmagic.TileEntityTradingChest");
		GameRegistry.registerTileEntity(TileEntityCage.class, "redmagic.TileEntityCage");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "redmagic.TileEntityCrystal");
		GameRegistry.registerTileEntity(TileEntityEarthwire.class, "redmagic.TileEntityEarthwire");
		GameRegistry.registerTileEntity(TileEntitySoul.class, "redmagic.TileEntitySoul");
		GameRegistry.registerTileEntity(TileEntitySoulEnergyAccess.class, "redmagic.TileEntitySoulEnergyAccess");
	}
	
}
