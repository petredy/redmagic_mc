package com.petredy.redmagic.handlers;

import com.petredy.redmagic.tileentities.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityEngineRhenium.class, "redmagic.TileEntityRhenium");
		GameRegistry.registerTileEntity(TileEntityTradingChest.class, "redmagic.TileEntityTradingChest");
		GameRegistry.registerTileEntity(TileEntityCage.class, "redmagic.TileEntityCage");
		GameRegistry.registerTileEntity(TileEntityEarthwire.class, "redmagic.TileEntityEarthwire");
		GameRegistry.registerTileEntity(TileEntityEnergySummoner.class, "redmagic.TileEntityEnergySummoner");
		GameRegistry.registerTileEntity(TileEntityMachine.class, "redmagic.TileEntityMachine");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "redmagic.TileEntityGenerator");
		GameRegistry.registerTileEntity(TileEntityESLoader.class, "redmagic.TileEntityESLoader");
		GameRegistry.registerTileEntity(TileEntityMachineryCore.class, "redmagic.TileEntityMachineryCore");
		GameRegistry.registerTileEntity(TileEntityMachinery.class, "redmagic.TileEntityMachinery");
		GameRegistry.registerTileEntity(TileEntityMachineryDrive.class, "redmagic.TileEntityMachineryDrive");
		
		
	}
	
}
