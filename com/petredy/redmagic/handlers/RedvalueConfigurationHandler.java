package com.petredy.redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.petredy.redmagic.redvalue.RedvalueDictionary;

public class RedvalueConfigurationHandler {

	
	public static void init(){
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestone), 1);
		RedvalueDictionary.registerNative(new ItemStack(Block.wood), 4);
		RedvalueDictionary.registerNative(new ItemStack(Block.oreIron), 10);
		RedvalueDictionary.registerNative(new ItemStack(Item.redstone), 5);
		RedvalueDictionary.registerNative(new ItemStack(Item.diamond), 100);
	}
	
}
