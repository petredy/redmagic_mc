package com.petredy.redmagic.items;

import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;

public class Items {

	public static ItemTradingCrystal trading;
	public static ItemGlasses glasses;
	public static ItemSoul soul;
	public static ItemRedhole redhole;
	public static ItemCrafting crafting;
	public static ItemMachine machine;
	public static ItemRedmeter redmeter;
	public static ItemScrewdriver screwdriver;
	public static ItemSieve sieve;
	public static ItemMatter matter;
	public static ItemJackhammer jackhammer;
	public static ItemESStorage esStorage;
	
	public static ItemTribological logicCore;
	public static ItemTribological logicStorage;
	public static ItemTribological energyCondenser;
	public static ItemTribological plateRhenium;
	public static ItemTribological frameRehnium;
	public static ItemTribological coolingDevice;
	public static ItemTribological heatDevice;
	public static ItemTribological filterDevice;
	
	
	//Armor
	public static EnumArmorMaterial glassMaterial = EnumHelper.addArmorMaterial(ItemIndex.GLASSES_NAME, 1000, new int[]{2, 2, 2, 2, 2}, 30);
		
	//Rhenium
	public static EnumToolMaterial rheniumMaterial = EnumHelper.addToolMaterial("rhenium", 3, 5000, 6, 1, 18);
	
	public static void init(){
		trading = new ItemTradingCrystal(ItemIndex.TRADING_CRYSTAL_ID);
		glasses = new ItemGlasses(ItemIndex.GLASSES_ID, 0, 0);
		soul = new ItemSoul(ItemIndex.SOUL_ID);
		redhole = new ItemRedhole(ItemIndex.REDHOLE_ID);
		crafting = new ItemCrafting(ItemIndex.CRAFTING_ID);
		
		machine = new ItemMachine(ItemIndex.MACHINE_ID);
		redmeter = new ItemRedmeter(ItemIndex.REDMETER_ID);
		screwdriver = new ItemScrewdriver(ItemIndex.SCREWDRIVER_ID);
		sieve = new ItemSieve(ItemIndex.SIEVE_ID);
		matter = new ItemMatter(ItemIndex.MATTER_ID);
		jackhammer = new ItemJackhammer(ItemIndex.JACKHAMMER_ID);
		esStorage = new ItemESStorage(ItemIndex.ES_STORAGE_ID);
		
		logicCore = new ItemTribological(ItemIndex.LOGIC_CORE_ID, ItemIndex.LOGIC_CORE_NAME, 3f);
		logicStorage = new ItemTribological(ItemIndex.LOCIG_STORAGE_ID, ItemIndex.LOGIC_STORAGE_NAME, 2f);
		energyCondenser = new ItemTribological(ItemIndex.ENERGY_CONDENSER_ID, ItemIndex.ENERGY_CONDENSER_NAME, 4f);
		plateRhenium = new ItemTribological(ItemIndex.PLATE_RHENIUM_ID, ItemIndex.PLATE_RHENIUM_NAME, 1f);
		frameRehnium = new ItemTribological(ItemIndex.FRAME_RHENIUM_ID, ItemIndex.FRAME_RHENIUM_NAME, 2f);
		coolingDevice = new ItemTribological(ItemIndex.COOLING_DEVICE_ID, ItemIndex.COOLING_DEVICE_NAME, 5f);
		heatDevice = new ItemTribological(ItemIndex.HEAT_DEVICE_ID, ItemIndex.HEAT_DEVICE_NAME, 5f);
		filterDevice = new ItemTribological(ItemIndex.FILTER_DEVICE_ID, ItemIndex.FILTER_DEVICE_NAME, 2f);
		
		addRecipes();
	}
	
	public static void config(Configuration config){
		ItemIndex.TRADING_CRYSTAL_ID = config.getItem(ItemIndex.TRADING_CRYSTAL_NAME, ItemIndex.TRADING_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.TRADING_CRYSTAL_DEFAULT_ID);
		ItemIndex.GLASSES_ID = config.getItem(ItemIndex.GLASSES_NAME, ItemIndex.GLASSES_DEFAULT_ID).getInt(ItemIndex.GLASSES_DEFAULT_ID);
		ItemIndex.SOUL_ID = config.getItem(ItemIndex.SOUL_NAME, ItemIndex.SOUL_DEFAULT_ID).getInt(ItemIndex.SOUL_DEFAULT_ID);
		ItemIndex.REDHOLE_ID = config.getItem(ItemIndex.REDHOLE_NAME, ItemIndex.REDHOLE_DEFAULT_ID).getInt(ItemIndex.REDHOLE_DEFAULT_ID);
		ItemIndex.CRAFTING_ID = config.getItem(ItemIndex.CRAFTING_NAME, ItemIndex.CRAFTING_DEFAULT_ID).getInt(ItemIndex.CRAFTING_DEFAULT_ID);
		ItemIndex.REDMETER_ID = config.getItem(ItemIndex.REDMETER_NAME, ItemIndex.REDMETER_DEFAULT_ID).getInt(ItemIndex.REDMETER_DEFAULT_ID);
		ItemIndex.MACHINE_ID = config.getItem(ItemIndex.MACHINE_NAME, ItemIndex.MACHINE_DEFAULT_ID).getInt(ItemIndex.MACHINE_DEFAULT_ID);
		ItemIndex.SCREWDRIVER_ID = config.getItem(ItemIndex.SCREWDRIVER_NAME, ItemIndex.SCREWDRIVER_DEFAULT_ID).getInt(ItemIndex.SCREWDRIVER_DEFAULT_ID);
		ItemIndex.SIEVE_ID = config.getItem(ItemIndex.SIEVE_NAME, ItemIndex.SIEVE_DEFAULT_ID).getInt(ItemIndex.SIEVE_DEFAULT_ID);
		ItemIndex.MATTER_ID = config.getItem(ItemIndex.MATTER_NAME, ItemIndex.MATTER_DEFAULT_ID).getInt(ItemIndex.MATTER_DEFAULT_ID);
		ItemIndex.JACKHAMMER_ID = config.getItem(ItemIndex.JACKHAMMER_NAME, ItemIndex.JACKHAMMER_DEFAULT_ID).getInt(ItemIndex.JACKHAMMER_DEFAULT_ID);
		ItemIndex.ES_STORAGE_ID = config.getItem(ItemIndex.ES_STORAGE_NAME, ItemIndex.ES_STORAGE_DEFAULT_ID).getInt(ItemIndex.ES_STORAGE_DEFAULT_ID);
	
		
		ItemIndex.LOGIC_CORE_ID = config.getItem(ItemIndex.LOGIC_CORE_NAME, ItemIndex.LOGIC_CORE_DEFAULT_ID).getInt(ItemIndex.LOGIC_CORE_DEFAULT_ID);
		ItemIndex.LOCIG_STORAGE_ID = config.getItem(ItemIndex.LOGIC_STORAGE_NAME, ItemIndex.LOGIC_STORAGE_DEFAULT_ID).getInt(ItemIndex.LOGIC_STORAGE_DEFAULT_ID);
		ItemIndex.ENERGY_CONDENSER_ID = config.getItem(ItemIndex.ENERGY_CONDENSER_NAME, ItemIndex.ENERGY_CONDENSER_DEFAULT_ID).getInt(ItemIndex.ENERGY_CONDENSER_DEFAULT_ID);
		ItemIndex.PLATE_RHENIUM_ID = config.getItem(ItemIndex.PLATE_RHENIUM_NAME, ItemIndex.PLATE_RHENIUM_DEFAULT_ID).getInt(ItemIndex.PLATE_RHENIUM_DEFAULT_ID);
		ItemIndex.FRAME_RHENIUM_ID = config.getItem(ItemIndex.FRAME_RHENIUM_NAME, ItemIndex.FRAME_RHENIUM_DEFAULT_ID).getInt(ItemIndex.FRAME_RHENIUM_DEFAULT_ID);
		ItemIndex.COOLING_DEVICE_ID = config.getItem(ItemIndex.COOLING_DEVICE_NAME, ItemIndex.COOLING_DEVICE_DEFAULT_ID).getInt(ItemIndex.COOLING_DEVICE_DEFAULT_ID);
		ItemIndex.HEAT_DEVICE_ID = config.getItem(ItemIndex.HEAT_DEVICE_NAME, ItemIndex.HEAT_DEVICE_DEFAULT_ID).getInt(ItemIndex.HEAT_DEVICE_DEFAULT_ID);
		ItemIndex.FILTER_DEVICE_ID = config.getItem(ItemIndex.FILTER_DEVICE_NAME, ItemIndex.FILTER_DEVICE_DEFAULT_ID).getInt(ItemIndex.FILTER_DEVICE_DEFAULT_ID);
		
	}
	
	
	public static void addRecipes(){
		GameRegistry.addShapelessRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new Object[]{
			new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA),
			new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA)			
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(crafting, 4, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new Object[]{
			new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(trading), new Object[]{
			"XXX",
			"X X",
			"XXX",
			'X', new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(glasses), new Object[]{
			"PSP",
			"s s",
			"s s",
			'P', Block.thinGlass,
			's', Item.stick,
			'S', new ItemStack(logicCore)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(redhole), new Object[]{
			"RRR",
			"RCR",
			"RRR",
			'R', Item.redstone,
			'C', new ItemStack(energyCondenser)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(logicCore), new Object[]{
			"rRF",
			"RIR",
			"FRr",
			'I', Item.ingotIron,
			'R', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'r', Item.redstone,
			'F', Item.flint
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(logicStorage), new Object[]{
			"IfI",
			"rcr",
			"IfI",
			'C', new ItemStack(logicCore),
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'r', Item.redstone,
			'f', Item.flint
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(energyCondenser), new Object[]{
			"fIf",
			"p p",
			"fIf",
			'f', Item.flint,
			'p', Block.thinGlass,
			'd', Item.diamond,
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
			
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(plateRhenium), new Object[]{
			"CfC",
			"iii",
			"CfC",
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA),
			'f', Item.flint,
			'i', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(redmeter), new Object[]{
			new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			Item.ingotIron
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(screwdriver), new Object[]{
			new ItemStack(Item.stick), new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(sieve), new Object[]{
			Block.fenceIron,
			Item.flint
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(jackhammer, 1, rheniumMaterial.getMaxUses()), new Object[]{
			" P ",
			"I I",
			"IpI",
			'P', Item.pickaxeGold,
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'p', Block.pistonBase
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(esStorage, 1, ItemIndex.ES_STORAGE_SMALL_METADATA), new Object[]{
			"DDD",
			"DCD",
			"DDD",
			'D', Item.diamond,
			'C', new ItemStack(energyCondenser)
		});
		
		
		// Machines
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.COLLECTOR_METADATA), new Object[]{
			"FFF",
			"ISI",
			"PCP",
			'P', new ItemStack(plateRhenium),
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'F', Block.fenceIron,
			'C', new ItemStack(logicCore),
			'S', new ItemStack(logicStorage)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.CONTACT_COOLING_METADATA), new Object[]{
			"fIf",
			"sis",
			"PCP",
			'f', Item.flint,
			'I', Block.ice,
			'P',  new ItemStack(plateRhenium),
			's', new ItemStack(logicStorage),
			'C', new ItemStack(logicCore)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.FURNACE_METADATA), new Object[]{
			"FFF",
			"ICI",
			"PcP",
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'P', new ItemStack(plateRhenium),
			'F', Block.furnaceIdle,
			'C', new ItemStack(energyCondenser),
			'c', new ItemStack(logicCore)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.DISINTEGRATOR_METADATA), new Object[]{
			"PCP",
			"SRS",
			"PcP",
			'R', new ItemStack(redhole),
			'P', new ItemStack(plateRhenium),
			'C', new ItemStack(energyCondenser),
			'S', new ItemStack(logicStorage),
			'c', new ItemStack(logicCore)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.CHARGER_METADATA), new Object[]{
			"ISI",
			"FFF",
			"PCP",
			'P', new ItemStack(plateRhenium),
			'C', new ItemStack(logicCore),
			'F', Block.fenceIron,
			'S', new ItemStack(logicStorage)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.REFRIGERATOR_METADATA), new Object[]{
			"iii",
			"PSP",
			"PCP",
			'i', Block.ice,
			'P', new ItemStack(plateRhenium),
			'S', new ItemStack(logicStorage),
			'C', new ItemStack(logicCore)	
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.FREEZER_METADATA), new Object[]{
			"fif",
			"fif",
			"PFP",
			'i', Block.ice,
			'f', Item.flint,
			'F', Block.furnaceIdle,
			'c', new ItemStack(logicCore),
			'P', new ItemStack(plateRhenium),
		});
		
	
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.COMPACTOR_METADATA), new Object[]{
			"POP",
			"PWP",
			"PPP",
			'P', new ItemStack(plateRhenium),
			'O', Block.obsidian,
			'W', Block.workbench
		});
		
		/* TODO:
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.RECYCLER_METADATA), new Object[]{
			
		});
		*/
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.SIEVE_METADATA), new Object[]{
			"FFF",
			"P P",
			"P P",
			'F', Block.fenceIron,
			'P', new ItemStack(plateRhenium)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.CRYSTALIZER_METADATA), new Object[]{
			"PIP",
			"IiI",
			"PIP",
			'P', new ItemStack(plateRhenium),
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'i', Block.ice	
		});
		
		
	}
	
	
}
