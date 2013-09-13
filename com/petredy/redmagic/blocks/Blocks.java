package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.Configuration;

public class Blocks {

	public static BlockFurnace furnace;
	public static BlockBasicEngine engine;
	public static BlockTradingChest trading;
	public static BlockSoulCatcher soulCatcher;
	public static BlockSoulCatcherStorage soulCatcherPlug;
	
	public static void init(){
		furnace = new BlockFurnace(BlockIndex.FURNACE_ID);
		engine = new BlockBasicEngine(BlockIndex.ENGINE_ID);
		trading = new BlockTradingChest(BlockIndex.TRADING_CHEST_ID);
		soulCatcher = new BlockSoulCatcher(BlockIndex.SOUL_CATCHER_ID);
		soulCatcherPlug = new BlockSoulCatcherStorage(BlockIndex.SOUL_CATCHER_STORAGE_ID);
		
		GameRegistry.registerBlock(engine, BlockIndex.ENGINE_NAME);
		GameRegistry.registerBlock(furnace, BlockIndex.FURNACE_NAME);
		GameRegistry.registerBlock(trading, BlockIndex.TRADING_CHEST_NAME);
		GameRegistry.registerBlock(soulCatcher, BlockIndex.SOUL_CATCHER_NAME);
		GameRegistry.registerBlock(soulCatcherPlug, BlockIndex.SOUL_CATCHER_STORAGE_NAME);
	}
	
	
	public static void config(Configuration config){
		BlockIndex.FURNACE_ID = config.getBlock(BlockIndex.FURNACE_NAME, BlockIndex.FURNACE_DEFAULT_ID).getInt(BlockIndex.FURNACE_DEFAULT_ID);
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
		BlockIndex.TRADING_CHEST_ID = config.getBlock(BlockIndex.TRADING_CHEST_NAME, BlockIndex.TRADING_CHEST_DEFAULT_ID).getInt(BlockIndex.TRADING_CHEST_DEFAULT_ID);
		BlockIndex.SOUL_CATCHER_ID = config.getBlock(BlockIndex.SOUL_CATCHER_NAME, BlockIndex.SOUL_CATCHER_DEFAULT_ID).getInt(BlockIndex.SOUL_CATCHER_DEFAULT_ID);
		BlockIndex.SOUL_CATCHER_STORAGE_ID = config.getBlock(BlockIndex.SOUL_CATCHER_STORAGE_NAME, BlockIndex.SOUL_CATCHER_STORAGE_DEFAULT_ID).getInt(BlockIndex.SOUL_CATCHER_STORAGE_DEFAULT_ID);
	}
	
}
