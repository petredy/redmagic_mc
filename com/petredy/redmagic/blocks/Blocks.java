package com.petredy.redmagic.blocks;

import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class Blocks {

	public static BlockFurnace furnace;
	public static BlockBasicEngine engine;
	public static BlockTradingChest trading;
	public static BlockDecoration decoration;
	public static BlockRheniumOre oreRhenium;
	public static BlockCage cage;
	public static BlockCrystal crystal;
	public static BlockSoulBase base;
	public static BlockSoulChest soulChest;
	
	public static void init(){
		furnace = new BlockFurnace(BlockIndex.FURNACE_ID);
		engine = new BlockBasicEngine(BlockIndex.ENGINE_ID);
		trading = new BlockTradingChest(BlockIndex.TRADING_CHEST_ID);
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		oreRhenium = new BlockRheniumOre(BlockIndex.RHENIUM_ORE_ID);
		cage = new BlockCage(BlockIndex.CAGE_ID);
		crystal = new BlockCrystal(BlockIndex.CRYSTAL_ID);
		base = new BlockSoulBase(BlockIndex.SOUL_BASE_ID);
		soulChest = new BlockSoulChest(BlockIndex.SOUL_CHEST_ID);
		
		GameRegistry.registerBlock(engine, BlockIndex.ENGINE_NAME);
		GameRegistry.registerBlock(furnace, BlockIndex.FURNACE_NAME);
		GameRegistry.registerBlock(trading, BlockIndex.TRADING_CHEST_NAME);
		GameRegistry.registerBlock(decoration, ItemBlockDecoration.class, BlockIndex.DECORATION_NAME);
		GameRegistry.registerBlock(oreRhenium, BlockIndex.RHENIUM_ORE_NAME);
		GameRegistry.registerBlock(cage, BlockIndex.CAGE_NAME);
		GameRegistry.registerBlock(crystal, ItemBlockCrystal.class, BlockIndex.CRYSTAL_NAME);
		GameRegistry.registerBlock(base, BlockIndex.SOUL_BASE_NAME);
		GameRegistry.registerBlock(soulChest, BlockIndex.SOUL_CHEST_NAME);
		addRecipes();
	}
	
	
	public static void config(Configuration config){
		BlockIndex.FURNACE_ID = config.getBlock(BlockIndex.FURNACE_NAME, BlockIndex.FURNACE_DEFAULT_ID).getInt(BlockIndex.FURNACE_DEFAULT_ID);
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
		BlockIndex.TRADING_CHEST_ID = config.getBlock(BlockIndex.TRADING_CHEST_NAME, BlockIndex.TRADING_CHEST_DEFAULT_ID).getInt(BlockIndex.TRADING_CHEST_DEFAULT_ID);
		BlockIndex.DECORATION_ID = config.getBlock(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		BlockIndex.RHENIUM_ORE_ID = config.getBlock(BlockIndex.RHENIUM_ORE_NAME, BlockIndex.RHENIUM_ORE_DEFAULT_ID).getInt(BlockIndex.RHENIUM_ORE_DEFAULT_ID);
		BlockIndex.CAGE_ID = config.getBlock(BlockIndex.CAGE_NAME, BlockIndex.CAGE_DEFAULT_ID).getInt(BlockIndex.CAGE_DEFAULT_ID);
		BlockIndex.CRYSTAL_ID = config.getBlock(BlockIndex.CRYSTAL_NAME, BlockIndex.CRYSTAL_DEFAULT_ID).getInt(BlockIndex.CRYSTAL_DEFAULT_ID);
		BlockIndex.SOUL_BASE_ID = config.getBlock(BlockIndex.SOUL_BASE_NAME, BlockIndex.SOUL_BASE_DEFAULT_ID).getInt(BlockIndex.SOUL_BASE_DEFAULT_ID);
		BlockIndex.SOUL_CHEST_ID = config.getBlock(BlockIndex.SOUL_CHEST_NAME, BlockIndex.SOUL_CHEST_DEFAULT_ID).getInt(BlockIndex.SOUL_CHEST_DEFAULT_ID);
		
	}
	
	
	public static void addRecipes(){
		GameRegistry.addShapedRecipe(new ItemStack(engine), new Object[]{
			"RRR",
			" S ",
			"GPG",
			'S', new ItemStack(Items.soul),
			'R', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'G', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA),
			'P', Block.pistonBase
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(trading), new Object[]{
			"III",
			"I I",
			"WCW",
			'I', Item.ingotIron,
			'W', Block.planks,
			'C', Block.chest
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(crystal, 1, BlockIndex.CRYSTAL_SMALL_METADATA), new Object[]{
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), 
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(crystal, 1, BlockIndex.CRYSTAL_MEDIUM_METADATA), new Object[]{
			new ItemStack(crystal, 1, BlockIndex.CRYSTAL_SMALL_METADATA), new ItemStack(crystal, 1, BlockIndex.CRYSTAL_SMALL_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(crystal, 1, BlockIndex.CRYSTAL_LARGE_METADATA), new Object[]{
			new ItemStack(crystal, 1, BlockIndex.CRYSTAL_MEDIUM_METADATA), new ItemStack(crystal, 1, BlockIndex.CRYSTAL_MEDIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(base), new Object[]{
			"GFG",
			"FRF",
			"GFG",
			'G', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA),
			'F', Item.flint,
			'R', Item.redstone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(soulChest), new Object[]{
			"III",
			"III",
			"GCG",
			'I', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'G', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA),
			'C', new ItemStack(Block.chest)
		});
	}
}
