package com.petredy.redmagic.items;

import com.petredy.redmagic.lib.ItemIndex;

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
	public static ItemSoul soul;
	public static ItemGlasses glasses;
	public static ItemRedhole redhole;
	public static ItemCrafting crafting;
	public static ItemPointer pointer;
	
	public static ItemMachine machine;
	public static ItemRedmeter meter;
	
	//Armor
	public static EnumArmorMaterial glassMaterial = EnumHelper.addArmorMaterial(ItemIndex.GLASSES_NAME, 1000, new int[]{2, 2, 2, 2, 2}, 30);
		
	
	public static void init(){
		trading = new ItemTradingCrystal(ItemIndex.TRADING_CRYSTAL_ID);
		soul = new ItemSoul(ItemIndex.SOUL_ID);
		glasses = new ItemGlasses(ItemIndex.GLASSES_ID, 0, 0);
		redhole = new ItemRedhole(ItemIndex.REDHOLE_ID);
		crafting = new ItemCrafting(ItemIndex.CRAFTING_ID);
		pointer = new ItemPointer(ItemIndex.POINTER_ID);
		
		machine = new ItemMachine(ItemIndex.MACHINE_ID);
		meter = new ItemRedmeter(ItemIndex.REDMETER_ID);
		addRecipes();
	}
	
	public static void config(Configuration config){
		ItemIndex.TRADING_CRYSTAL_ID = config.getItem(ItemIndex.TRADING_CRYSTAL_NAME, ItemIndex.TRADING_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.TRADING_CRYSTAL_DEFAULT_ID);
		ItemIndex.SOUL_ID = config.getItem(ItemIndex.SOUL_NAME, ItemIndex.SOUL_DEFAULT_ID).getInt(ItemIndex.SOUL_DEFAULT_ID);
		ItemIndex.GLASSES_ID = config.getItem(ItemIndex.GLASSES_NAME, ItemIndex.GLASSES_DEFAULT_ID).getInt(ItemIndex.GLASSES_DEFAULT_ID);
		ItemIndex.REDHOLE_ID = config.getItem(ItemIndex.REDHOLE_NAME, ItemIndex.REDHOLE_DEFAULT_ID).getInt(ItemIndex.REDHOLE_DEFAULT_ID);
		ItemIndex.CRAFTING_ID = config.getItem(ItemIndex.CRAFTING_NAME, ItemIndex.CRAFTING_DEFAULT_ID).getInt(ItemIndex.CRAFTING_DEFAULT_ID);
		ItemIndex.POINTER_ID = config.getItem(ItemIndex.POINTER_NAME, ItemIndex.POINTER_DEFAULT_ID).getInt(ItemIndex.POINTER_DEFAULT_ID);
		ItemIndex.REDMETER_ID = config.getItem(ItemIndex.REDMETER_NAME, ItemIndex.REDMETER_DEFAULT_ID).getInt(ItemIndex.REDMETER_DEFAULT_ID);
		ItemIndex.MACHINE_ID = config.getItem(ItemIndex.MACHINE_NAME, ItemIndex.MACHINE_DEFAULT_ID).getInt(ItemIndex.MACHINE_DEFAULT_ID);
		
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
			'S', new ItemStack(crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(redhole), new Object[]{
			"RRR",
			"RGR",
			"RRR",
			'R', Item.redstone,
			'G', new ItemStack(crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(crafting, 1, ItemIndex.CRAFTING_GEAR_RHENIUM_METADATA), new Object[]{
			" R ",
			"RIR",
			" R ",
			'I', Item.ingotIron,
			'R', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(pointer), new Object[]{
			"N  ",
			" S ",
			"  S",
			'N', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'S', Item.stick
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(meter), new Object[]{
			" I ",
			"ICI",
			" I ",
			'I', new ItemStack(crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA),
			'C', Item.compass
		});
	}
	
	
}
