package com.petredy.redmagic.handlers;

import com.petredy.redmagic.tileentities.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityTradingChest.class, "redmagic.TileEntityTradingChest");
		GameRegistry.registerTileEntity(TileEntityCage.class, "redmagic.TileEntityCage");
		GameRegistry.registerTileEntity(TileEntityEarthwire.class, "redmagic.TileEntityEarthwire");
		GameRegistry.registerTileEntity(TileEntityEnergySummoner.class, "redmagic.TileEntityEnergySummoner");
		GameRegistry.registerTileEntity(TileEntityMachine.class, "redmagic.TileEntityMachine");
	}
	
}
