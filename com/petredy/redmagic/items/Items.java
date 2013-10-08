package com.petredy.redmagic.items;

import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
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
	public static ItemRedmeter meter;
	public static ItemMatter matter;
	public static ItemScrewdriver screwdriver;
	
	public static ItemArtifact artifact;
		
	//Armor
	public static EnumArmorMaterial glassMaterial = EnumHelper.addArmorMaterial(ItemIndex.GLASSES_NAME, 1000, new int[]{2, 2, 2, 2, 2}, 30);
		
	
	public static void init(){
		trading = new ItemTradingCrystal(ItemIndex.TRADING_CRYSTAL_ID);
		glasses = new ItemGlasses(ItemIndex.GLASSES_ID, 0, 0);
		soul = new ItemSoul(ItemIndex.SOUL_ID);
		redhole = new ItemRedhole(ItemIndex.REDHOLE_ID);
		crafting = new ItemCrafting(ItemIndex.CRAFTING_ID);
		
		machine = new ItemMachine(ItemIndex.MACHINE_ID);
		meter = new ItemRedmeter(ItemIndex.REDMETER_ID);
		matter = new ItemMatter(ItemIndex.MATTER_ID);
		screwdriver = new ItemScrewdriver(ItemIndex.SCREWDRIVER_ID);
		
		artifact = new ItemArtifact(ItemIndex.ARTIFACT_ID);
		
		
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
		ItemIndex.MATTER_ID = config.getItem(ItemIndex.MATTER_NAME, ItemIndex.MATTER_DEFAULT_ID).getInt(ItemIndex.MATTER_DEFAULT_ID);
		ItemIndex.SCREWDRIVER_ID = config.getItem(ItemIndex.SCREWDRIVER_NAME, ItemIndex.SCREWDRIVER_DEFAULT_ID).getInt(ItemIndex.SCREWDRIVER_DEFAULT_ID);
		ItemIndex.ARTIFACT_ID = config.getItem(ItemIndex.ARTIFACT_NAME, ItemIndex.ARTIFACT_DEFAULT_ID).getInt(ItemIndex.ARTIFACT_DEFAULT_ID);
		
	}
	
	
	public static void addRecipes(){
		GameRegistry.addShapelessRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new Object[]{
			new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA),
			new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA)			
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
			'S', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(redhole), new Object[]{
			"RRR",
			"RCR",
			"RRR",
			'R', Item.redstone,
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_ENERGY_CONDENSER_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA), new Object[]{
			"rRF",
			"RIR",
			"FRr",
			'I', Item.ingotIron,
			'R', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'r', Item.redstone,
			'F', Item.flint
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA), new Object[]{
			"IfI",
			"rcr",
			"IfI",
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA),
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'r', Item.redstone,
			'f', Item.flint
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_ENERGY_CONDENSER_METADATA), new Object[]{
			"fIf",
			"p p",
			"fIf",
			'f', Item.flint,
			'p', Block.thinGlass,
			'd', Item.diamond,
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
			
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA), new Object[]{
			"IfI",
			"iii",
			"IfI",
			'I', Item.ingotIron,
			'f', Item.flint,
			'i', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(meter), new Object[]{
			" I ",
			"ICI",
			" I ",
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(matter, 1, ItemIndex.MATTER_STABLE_METADATA), new Object[]{
			new ItemStack(matter, 1, ItemIndex.MATTER_INSTABLE_METADATA), new ItemStack(matter, 1, ItemIndex.MATTER_INSTABLE_METADATA),
			new ItemStack(matter, 1, ItemIndex.MATTER_INSTABLE_METADATA), new ItemStack(matter, 1, ItemIndex.MATTER_INSTABLE_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(matter, 1, ItemIndex.MATTER_INSTABLE_METADATA), new Object[]{
			new ItemStack(matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA), new ItemStack(matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA),
			new ItemStack(matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA), new ItemStack(matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(screwdriver), new Object[]{
			new ItemStack(Item.stick), new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.COLLECTOR_METADATA), new Object[]{
			"PFP",
			"I I",
			"ICI",
			'P', new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'F', Block.fenceIron,
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.CONTACT_COOLING_METADATA), new Object[]{
			"IPI",
			"III",
			"scs",
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'P',  new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			's', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA),
			'c', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.FURNACE_METADATA), new Object[]{
			"PFP",
			"ICI",
			"IcI",
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'P', new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'F', Block.furnaceIdle,
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_ENERGY_CONDENSER_METADATA),
			'c', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.DEINTEGRATOR_METADATA), new Object[]{
			" R ",
			"PCP",
			"ScS",
			'R', new ItemStack(redhole),
			'P', new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_ENERGY_CONDENSER_METADATA),
			'S', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA),
			'c', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.CHARGER_METADATA), new Object[]{
			"I I",
			"I I",
			"PCP",
			'P', new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'F', Block.fenceIron,
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.REFRIGERATOR_METADATA), new Object[]{
			"iii",
			"PPP",
			"SCS",
			'i', Block.ice,
			'P', new ItemStack(crafting, 1, ItemIndex.CRAFTING_PLATE_RHENIUM_METADATA),
			'S', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_STORAGE_METADATA),
			'C', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)	
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(machine, 1, Machines.FREEZER_METADATA), new Object[]{
			"ini",
			"fcf",
			"iFi",
			'i', Block.ice,
			'f', Item.flint,
			'F', Block.furnaceIdle,
			'n', new ItemStack(crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA),
			'c', new ItemStack(crafting, 1, ItemIndex.CRAFTING_LOGIC_CORE_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(artifact), new Object[]{
			"SrS",
			"IRI",
			"SrS",
			'S', Block.sandStone,
			'R', Item.redstone,
			'I', Item.ingotIron,
			'r', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		
	}
	
	
}
