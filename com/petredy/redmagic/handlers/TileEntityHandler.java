package com.petredy.redmagic.handlers;

import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.tileentities.TileEntityFurnace;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityFurnace.class, "redmagic.TileEntityFurnace");
		GameRegistry.registerTileEntity(TileEntityEngine.class, "redmagic.TileEntityEngine");
	}
	
}