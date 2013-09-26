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

	public static BlockTradingChest trading;
	public static BlockDecoration decoration;
	public static BlockRheniumOre oreRhenium;
	public static BlockCage cage;
	public static BlockCrystal crystal;
	public static BlockWorkTable workTable;
	public static BlockEarthwire earthwire;
	public static BlockHole hole;
	public static BlockSoul soul;
	public static BlockSoulEnergyAccess energyAccess;
	
	public static void init(){
		trading = new BlockTradingChest(BlockIndex.TRADING_CHEST_ID);
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		oreRhenium = new BlockRheniumOre(BlockIndex.RHENIUM_ORE_ID);
		cage = new BlockCage(BlockIndex.CAGE_ID);
		crystal = new BlockCrystal(BlockIndex.CRYSTAL_ID);
		workTable = new BlockWorkTable(BlockIndex.WORK_TABLE_ID);
		earthwire = new BlockEarthwire(BlockIndex.EARTHWIRE_ID);
		hole = new BlockHole(BlockIndex.HOLE_ID);
		soul = new BlockSoul(BlockIndex.SOUL_ID);
		energyAccess = new BlockSoulEnergyAccess(BlockIndex.SOUL_ENERGY_ACCESS_ID);
		
		GameRegistry.registerBlock(trading, BlockIndex.TRADING_CHEST_NAME);
		GameRegistry.registerBlock(decoration, ItemBlockDecoration.class, BlockIndex.DECORATION_NAME);
		GameRegistry.registerBlock(oreRhenium, BlockIndex.RHENIUM_ORE_NAME);
		GameRegistry.registerBlock(cage, BlockIndex.CAGE_NAME);
		GameRegistry.registerBlock(crystal, ItemBlockCrystal.class, BlockIndex.CRYSTAL_NAME);
		//GameRegistry.registerBlock(workTable, BlockIndex.WORK_TABLE_NAME);
		GameRegistry.registerBlock(earthwire, BlockIndex.EARTHWIRE_NAME);
		GameRegistry.registerBlock(hole, BlockIndex.HOLE_NAME);
		GameRegistry.registerBlock(soul, BlockIndex.SOUL_NAME);
		GameRegistry.registerBlock(energyAccess, BlockIndex.SOUL_ENERGY_ACCESS_NAME);
		addRecipes();
	}
	
	
	public static void config(Configuration config){
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
		BlockIndex.TRADING_CHEST_ID = config.getBlock(BlockIndex.TRADING_CHEST_NAME, BlockIndex.TRADING_CHEST_DEFAULT_ID).getInt(BlockIndex.TRADING_CHEST_DEFAULT_ID);
		BlockIndex.DECORATION_ID = config.getBlock(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		BlockIndex.RHENIUM_ORE_ID = config.getBlock(BlockIndex.RHENIUM_ORE_NAME, BlockIndex.RHENIUM_ORE_DEFAULT_ID).getInt(BlockIndex.RHENIUM_ORE_DEFAULT_ID);
		BlockIndex.CAGE_ID = config.getBlock(BlockIndex.CAGE_NAME, BlockIndex.CAGE_DEFAULT_ID).getInt(BlockIndex.CAGE_DEFAULT_ID);
		BlockIndex.CRYSTAL_ID = config.getBlock(BlockIndex.CRYSTAL_NAME, BlockIndex.CRYSTAL_DEFAULT_ID).getInt(BlockIndex.CRYSTAL_DEFAULT_ID);
		BlockIndex.WORK_TABLE_ID = config.getBlock(BlockIndex.WORK_TABLE_NAME, BlockIndex.WORK_TABLE_DEFAULT_ID).getInt(BlockIndex.WORK_TABLE_DEFAULT_ID);
		BlockIndex.EARTHWIRE_ID = config.getBlock(BlockIndex.EARTHWIRE_NAME, BlockIndex.EARTHWIRE_DEFAULT_ID).getInt(BlockIndex.EARTHWIRE_DEFAULT_ID);
		BlockIndex.HOLE_ID = config.getBlock(BlockIndex.HOLE_NAME, BlockIndex.HOLE_DEFAULT_ID).getInt(BlockIndex.HOLE_DEFAULT_ID);
		BlockIndex.SOUL_ID = config.getBlock(BlockIndex.SOUL_NAME, BlockIndex.SOUL_DEFAULT_ID).getInt(BlockIndex.SOUL_DEFAULT_ID);
		BlockIndex.SOUL_ENERGY_ACCESS_ID = config.getBlock(BlockIndex.SOUL_ENERGY_ACCESS_NAME, BlockIndex.SOUL_ENERGY_ACCESS_DEFAULT_ID).getInt(BlockIndex.SOUL_ENERGY_ACCESS_DEFAULT_ID);
	}
	
	
	public static void addRecipes(){
		
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
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.crafting, 5, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new Object[]{
			new ItemStack(oreRhenium)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(decoration, 4, BlockIndex.MARBLE_BRICKS_METADATA), new Object[]{
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA), new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA),
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA), new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(workTable), new Object[]{
			"FFF",
			"RRR",
			"GWG",
			'F', Item.flint,
			'R', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'G', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA),
			'W', Block.workbench
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(earthwire), new Object[]{
			"FIF",
			"FIF",
			"FIF",
			'I', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'F', Item.flint
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(energyAccess, 2), new Object[]{
			"FIF",
			"IGI",
			"FIF",
			'F', Item.flint,
			'G', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA),
			'I', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});;
	}
}
