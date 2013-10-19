package com.petredy.redmagic.blocks;

import com.petredy.redmagic.items.ItemMachine;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class Blocks {

	public static BlockEngine engine;
	public static BlockTradingChest trading;
	public static BlockDecoration decoration;
	public static BlockRheniumOre oreRhenium;
	public static BlockCage cage;
	public static BlockEarthwire earthwire;
	public static BlockHole hole;
	public static BlockEnergySummoner energySummoner;
	public static BlockMachine machine;
	public static BlockRedTransformator transformator;
	
	public static void init(){
		engine = new BlockEngine(BlockIndex.ENGINE_ID);
		trading = new BlockTradingChest(BlockIndex.TRADING_CHEST_ID);
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		oreRhenium = new BlockRheniumOre(BlockIndex.RHENIUM_ORE_ID);
		cage = new BlockCage(BlockIndex.CAGE_ID);
		earthwire = new BlockEarthwire(BlockIndex.EARTHWIRE_ID);
		hole = new BlockHole(BlockIndex.HOLE_ID);
		energySummoner = new BlockEnergySummoner(BlockIndex.ENERGY_SUMMONER_ID);
		machine = new BlockMachine(BlockIndex.MACHINE_ID);
		transformator = new BlockRedTransformator(BlockIndex.RED_TRANSFORMATOR_ID);
		
		GameRegistry.registerBlock(engine, ItemBlockEngine.class, BlockIndex.ENGINE_NAME);
		GameRegistry.registerBlock(trading, BlockIndex.TRADING_CHEST_NAME);
		GameRegistry.registerBlock(decoration, ItemBlockDecoration.class, BlockIndex.DECORATION_NAME);
		GameRegistry.registerBlock(oreRhenium, BlockIndex.RHENIUM_ORE_NAME);
		GameRegistry.registerBlock(cage, BlockIndex.CAGE_NAME);
		GameRegistry.registerBlock(earthwire, BlockIndex.EARTHWIRE_NAME);
		GameRegistry.registerBlock(hole, BlockIndex.HOLE_NAME);
		GameRegistry.registerBlock(energySummoner, BlockIndex.ENERGY_SUMMONER_NAME);
		GameRegistry.registerBlock(machine, BlockIndex.MACHINE_NAME);
		GameRegistry.registerBlock(transformator, BlockIndex.RED_TRANSFORMATOR_NAME);
		
		addRecipes();
		
	}
	
	
	public static void config(Configuration config){
		BlockIndex.ENGINE_ID = config.getBlock(BlockIndex.ENGINE_NAME, BlockIndex.ENGINE_DEFAULT_ID).getInt(BlockIndex.ENGINE_DEFAULT_ID);
		BlockIndex.TRADING_CHEST_ID = config.getBlock(BlockIndex.TRADING_CHEST_NAME, BlockIndex.TRADING_CHEST_DEFAULT_ID).getInt(BlockIndex.TRADING_CHEST_DEFAULT_ID);
		BlockIndex.DECORATION_ID = config.getBlock(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		BlockIndex.RHENIUM_ORE_ID = config.getBlock(BlockIndex.RHENIUM_ORE_NAME, BlockIndex.RHENIUM_ORE_DEFAULT_ID).getInt(BlockIndex.RHENIUM_ORE_DEFAULT_ID);
		BlockIndex.CAGE_ID = config.getBlock(BlockIndex.CAGE_NAME, BlockIndex.CAGE_DEFAULT_ID).getInt(BlockIndex.CAGE_DEFAULT_ID);
		BlockIndex.EARTHWIRE_ID = config.getBlock(BlockIndex.EARTHWIRE_NAME, BlockIndex.EARTHWIRE_DEFAULT_ID).getInt(BlockIndex.EARTHWIRE_DEFAULT_ID);
		BlockIndex.HOLE_ID = config.getBlock(BlockIndex.HOLE_NAME, BlockIndex.HOLE_DEFAULT_ID).getInt(BlockIndex.HOLE_DEFAULT_ID);
		BlockIndex.ENERGY_SUMMONER_ID = config.getBlock(BlockIndex.ENERGY_SUMMONER_NAME, BlockIndex.ENERGY_SUMMONER_DEFAULT_ID).getInt(BlockIndex.ENERGY_SUMMONER_DEFAULT_ID);
		BlockIndex.MACHINE_ID = config.getBlock(BlockIndex.MACHINE_NAME, BlockIndex.MACHINE_DEFAULT_ID).getInt(BlockIndex.MACHINE_DEFAULT_ID);
		BlockIndex.RED_TRANSFORMATOR_ID = config.getBlock(BlockIndex.RED_TRANSFORMATOR_NAME, BlockIndex.RED_TRANSFORMATOR_DEFAULT_ID).getInt(BlockIndex.RED_TRANSFORMATOR_DEFAULT_ID);
		
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
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.crafting, 5, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new Object[]{
			new ItemStack(oreRhenium)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(decoration, 4, BlockIndex.MARBLE_BRICKS_METADATA), new Object[]{
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA), new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA),
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA), new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(earthwire), new Object[]{
			"FIF",
			"PIP",
			"SCS",
			'I', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'F', Item.flint,
			'S', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA),
			'C', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA),
			'P', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(energySummoner), new Object[]{
			"NIN",
			"PcP",
			"SCS",
			'N', Block.netherBrick,
			'I', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'c', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_ENERGY_CONDENSER_METADATA),
			'S', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA),
			'C', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA),
			'P', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine), new Object[]{
			"PSP",
			"PCP",
			"PSP",
			'P', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'C', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA),
			'S', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(engine, 1, BlockIndex.ENGINE_RHENIUM_METADATA), new Object[]{
			"PPP",
			" G ",
			"CpC",
			'P', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'G', Block.glass,
			'C', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_ENERGY_CONDENSER_METADATA),
			'p', Block.pistonBase
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.decoration, 4, BlockIndex.GRANITE_BRICKS_METADATA), new Object[]{
			new ItemStack(decoration, 1, BlockIndex.GRANITE_METADATA), new ItemStack(decoration, 1, BlockIndex.GRANITE_METADATA), 
			new ItemStack(decoration, 1, BlockIndex.GRANITE_METADATA), new ItemStack(decoration, 1, BlockIndex.GRANITE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(decoration, 1, BlockIndex.RHENIUM_BLOCK_METADATA), new Object[]{
			"III",
			"III",
			"III",
			'I', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(transformator), new Object[]{
			"ROR",
			"OPO",
			"ROR",
			'P', new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'O', Block.obsidian,
			'R', Item.redstone
		});
		
		// ITMES
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.crafting, 9, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new Object[]{
			new ItemStack(decoration, 1, BlockIndex.RHENIUM_BLOCK_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA), new Object[]{
			new ItemStack(decoration, 1, BlockIndex.GRANITE_METADATA),
			Block.stone,
			Block.stone
		});
		
	}
}
