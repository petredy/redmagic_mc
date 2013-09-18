package com.petredy.redmagic.items;

import com.petredy.redmagic.lib.ItemIndex;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;

public class Items {

	public static ItemTradingCrystal trading;
	public static ItemSoul soul;
	public static ItemGlasses glasses;
	
	//Armor
	public static EnumArmorMaterial glassMaterial = EnumHelper.addArmorMaterial(ItemIndex.GLASSES_NAME, 1000, new int[]{2, 2, 2, 2, 2}, 30);
		
	
	public static void init(){
		trading = new ItemTradingCrystal(ItemIndex.TRADING_CRYSTAL_ID);
		soul = new ItemSoul(ItemIndex.SOUL_ID);
		glasses = new ItemGlasses(ItemIndex.GLASSES_ID, 0, 0);
	}
	
	public static void config(Configuration config){
		ItemIndex.TRADING_CRYSTAL_ID = config.getItem(ItemIndex.TRADING_CRYSTAL_NAME, ItemIndex.TRADING_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.TRADING_CRYSTAL_DEFAULT_ID);
		ItemIndex.GLASSES_ID = config.getItem(ItemIndex.GLASSES_NAME, ItemIndex.GLASSES_DEFAULT_ID).getInt(ItemIndex.GLASSES_DEFAULT_ID);
		ItemIndex.SOUL_ID = config.getItem(ItemIndex.SOUL_NAME, ItemIndex.SOUL_DEFAULT_ID).getInt(ItemIndex.SOUL_DEFAULT_ID);
		
	}
	
	
}
