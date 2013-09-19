package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.Configuration;

public class Blocks {

	public static BlockFurnace furnace;
	public static BlockBasicEngine engine;
	public static BlockTradingChest trading;
	public static BlockDecoration decoration;
	public static BlockRheniumOre oreRhenium;
	public static BlockCage cage;
	public static BlockCrystal crystal;
	
	public static void init(){
		furnace = new BlockFurnace(BlockIndex.FURNACE_ID);
		engine = new BlockBasicEngine(BlockIndex.ENGINE_ID);
		trading = new BlockTradingChest(BlockIndex.TRADING_CHEST_ID);
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		oreRhenium = new BlockRheniumOre(BlockIndex.RHENIUM_ORE_ID);
		cage = new BlockCage(BlockIndex.CAGE_ID);
		crystal = new BlockCrystal(BlockIndex.CRYSTAL_ID);
		
		GameRegistry.registerBlock(engine, BlockIndex.ENGINE_NAME);
		GameRegistry.registerBlock(furnace, BlockIndex.FURNACE_NAME);
		GameRegistry.registerBlock(trading, BlockIndex.TRADING_CHEST_NAME);
		GameRegistry.registerBlock(decoration, ItemBlockDecoration.class, BlockIndex.DECORATION_NAME);
		GameRegistry.registerBlock(oreRhenium, BlockIndex.RHENIUM_ORE_NAME);
		GameRegistry.registerBlock(cage, BlockIndex.CAGE_NAME);
		GameRegistry.registerBlock(crystal, ItemBlockCrystal.class, BlockIndex.CRYSTAL_NAME);
	}
	
	
	public static void config(Configuration config){
		BlockIndex.FURNACE_ID = config.getBlock(BlockIndex.FURNACE_NAME, BlockIndex.FURNACE_DEFAULT_ID).getInt(BlockIndex.FURNACE_DEFAULT_ID);
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
		BlockIndex.TRADING_CHEST_ID = config.getBlock(BlockIndex.TRADING_CHEST_NAME, BlockIndex.TRADING_CHEST_DEFAULT_ID).getInt(BlockIndex.TRADING_CHEST_DEFAULT_ID);
		BlockIndex.DECORATION_ID = config.getBlock(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		BlockIndex.RHENIUM_ORE_ID = config.getBlock(BlockIndex.RHENIUM_ORE_NAME, BlockIndex.RHENIUM_ORE_DEFAULT_ID).getInt(BlockIndex.RHENIUM_ORE_DEFAULT_ID);
		BlockIndex.CAGE_ID = config.getBlock(BlockIndex.CAGE_NAME, BlockIndex.CAGE_DEFAULT_ID).getInt(BlockIndex.CAGE_DEFAULT_ID);
		BlockIndex.CRYSTAL_ID = config.getBlock(BlockIndex.CRYSTAL_NAME, BlockIndex.CRYSTAL_DEFAULT_ID).getInt(BlockIndex.CRYSTAL_DEFAULT_ID);
		
	}
	
}
