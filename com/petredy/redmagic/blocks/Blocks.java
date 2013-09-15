package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.Configuration;

public class Blocks {

	public static BlockFurnace furnace;
	public static BlockBasicEngine engine;
	public static BlockTradingChest trading;
	public static BlockSoulCatcher soulCatcher;
	public static BlockRune rune;
	public static BlockDecoration decoration;
	public static BlockRheniumOre oreRhenium;
	
	public static void init(){
		furnace = new BlockFurnace(BlockIndex.FURNACE_ID);
		engine = new BlockBasicEngine(BlockIndex.ENGINE_ID);
		trading = new BlockTradingChest(BlockIndex.TRADING_CHEST_ID);
		soulCatcher = new BlockSoulCatcher(BlockIndex.SOUL_CATCHER_ID);
		rune = new BlockRune(BlockIndex.RUNE_ID);
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		oreRhenium = new BlockRheniumOre(BlockIndex.RHENIUM_ORE_ID);
		
		GameRegistry.registerBlock(engine, BlockIndex.ENGINE_NAME);
		GameRegistry.registerBlock(furnace, BlockIndex.FURNACE_NAME);
		GameRegistry.registerBlock(trading, BlockIndex.TRADING_CHEST_NAME);
		GameRegistry.registerBlock(soulCatcher, BlockIndex.SOUL_CATCHER_NAME);
		GameRegistry.registerBlock(rune, ItemBlockRune.class, BlockIndex.RUNE_NAME);
		GameRegistry.registerBlock(decoration, ItemBlockDecoration.class, BlockIndex.DECORATION_NAME);
		GameRegistry.registerBlock(oreRhenium, BlockIndex.RHENIUM_ORE_NAME);
	}
	
	
	public static void config(Configuration config){
		BlockIndex.FURNACE_ID = config.getBlock(BlockIndex.FURNACE_NAME, BlockIndex.FURNACE_DEFAULT_ID).getInt(BlockIndex.FURNACE_DEFAULT_ID);
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
		BlockIndex.TRADING_CHEST_ID = config.getBlock(BlockIndex.TRADING_CHEST_NAME, BlockIndex.TRADING_CHEST_DEFAULT_ID).getInt(BlockIndex.TRADING_CHEST_DEFAULT_ID);
		BlockIndex.SOUL_CATCHER_ID = config.getBlock(BlockIndex.SOUL_CATCHER_NAME, BlockIndex.SOUL_CATCHER_DEFAULT_ID).getInt(BlockIndex.SOUL_CATCHER_DEFAULT_ID);
		BlockIndex.RUNE_ID = config.getBlock(BlockIndex.RUNE_NAME, BlockIndex.RUNE_DEFAULT_ID).getInt(BlockIndex.RUNE_DEFAULT_ID);
		BlockIndex.DECORATION_ID = config.getBlock(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		BlockIndex.RHENIUM_ORE_ID = config.getBlock(BlockIndex.RHENIUM_ORE_NAME, BlockIndex.RHENIUM_ORE_DEFAULT_ID).getInt(BlockIndex.RHENIUM_ORE_DEFAULT_ID);
	
	}
	
}
