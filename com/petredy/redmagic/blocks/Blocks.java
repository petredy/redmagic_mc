package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.Configuration;

public class Blocks {

	public static BlockFurnace furnace;
	public static BlockBasicEngine engine;
	
	public static void init(){
		furnace = new BlockFurnace(BlockIndex.FURNACE_ID);
		engine = new BlockBasicEngine(BlockIndex.ENGINE_ID);
		
		GameRegistry.registerBlock(engine, BlockIndex.ENGINE_NAME);
		GameRegistry.registerBlock(furnace, BlockIndex.FURNACE_NAME);
	}
	
	
	public static void config(Configuration config){
		BlockIndex.FURNACE_ID = config.getBlock(BlockIndex.FURNACE_NAME, BlockIndex.FURNACE_DEFAULT_ID).getInt(BlockIndex.FURNACE_DEFAULT_ID);
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
	}
	
}
