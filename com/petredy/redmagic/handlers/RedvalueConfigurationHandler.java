package com.petredy.redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.petredy.redmagic.redvalue.RedvalueDictionary;

public class RedvalueConfigurationHandler {

	
	public static void init(){
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestone), 1);
		RedvalueDictionary.registerNative(new ItemStack(Block.dirt), 1);
		RedvalueDictionary.registerNative(new ItemStack(Block.grass), 1);
		RedvalueDictionary.registerNative(new ItemStack(Block.sand), 1);
		RedvalueDictionary.registerNative(new ItemStack(Block.netherrack), 1);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.gravel), 4);
		RedvalueDictionary.registerNative(new ItemStack(Item.flint), 4);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cactus), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 0), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 1), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 2), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 3), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 4), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 5), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 6), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 7), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 8), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 9), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 10), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 11), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 12), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 13), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 14), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 15), 8);
		RedvalueDictionary.registerNative(new ItemStack(Item.feather), 8);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.silk), 12);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.seeds), 16);
		RedvalueDictionary.registerNative(new ItemStack(Item.pumpkinSeeds), 16);
		RedvalueDictionary.registerNative(new ItemStack(Item.melonSeeds), 16);
		RedvalueDictionary.registerNative(new ItemStack(Block.plantRed), 16);
		RedvalueDictionary.registerNative(new ItemStack(Block.plantYellow), 16);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.wheat), 24);
		RedvalueDictionary.registerNative(new ItemStack(Block.netherStalk), 24);
		RedvalueDictionary.registerNative(new ItemStack(Item.rottenFlesh), 24);
		RedvalueDictionary.registerNative(new ItemStack(Item.slimeBall), 24);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.coal, 1, 1), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 0), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 1), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 2), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 3), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.mushroomBrown), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.mushroomRed), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 0), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 1), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 2), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 3), 32);
		RedvalueDictionary.registerNative(new ItemStack(Block.reed), 32);
		RedvalueDictionary.registerNative(new ItemStack(Item.egg), 32);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 0), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 1), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 2), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 3), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 4), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 5), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 6), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 7), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 8), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 9), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 10), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 11), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 12), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 13), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 14), 48);
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 15), 48);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.slowSand), 49);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.obsidian), 64);
		RedvalueDictionary.registerNative(new ItemStack(Block.oreRedstone), 64);
		RedvalueDictionary.registerNative(new ItemStack(Item.porkRaw), 64);
		RedvalueDictionary.registerNative(new ItemStack(Item.beefRaw), 64);
		RedvalueDictionary.registerNative(new ItemStack(Item.fishRaw), 64);
		RedvalueDictionary.registerNative(new ItemStack(Item.chickenRaw), 64);
		RedvalueDictionary.registerNative(new ItemStack(Item.leather), 64);
		RedvalueDictionary.registerNative(new ItemStack(Item.clay), 64);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.bone), 96);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreCoal), 128);
		RedvalueDictionary.registerNative(new ItemStack(Item.appleRed), 128);
		RedvalueDictionary.registerNative(new ItemStack(Item.spiderEye), 128);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.melon), 144);
		RedvalueDictionary.registerNative(new ItemStack(Block.pumpkin), 144);
		RedvalueDictionary.registerNative(new ItemStack(Item.bed), 144);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestoneMossy), 145);
		

		RedvalueDictionary.registerNative(new ItemStack(Item.gunpowder), 192);
		RedvalueDictionary.registerNative(new ItemStack(Item.fermentedSpiderEye), 192);
		RedvalueDictionary.registerNative(new ItemStack(Item.saddle), 192);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreIron), 256);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.glowstone), 384);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.blazePowder), 768);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.enderPearl), 1024);
		RedvalueDictionary.registerNative(new ItemStack(Item.emerald), 1024);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.blazeRod), 1536);
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreGold), 2048);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.ghastTear), 4096);
		
		RedvalueDictionary.registerNative(new ItemStack(Item.diamond), 8192);
		
	}
	
}
