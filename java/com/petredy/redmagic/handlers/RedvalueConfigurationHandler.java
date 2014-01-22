package com.petredy.redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

public class RedvalueConfigurationHandler {

	
	public static void init(){
		
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreCoal), Composition.getStandard(0, 0, 0, 1600, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestone), Composition.getStandard(10, 0, 0, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.dirt), Composition.getStandard(50, 0, 10, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.grass), Composition.getStandard(40, 0, 20, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.sand), Composition.getStandard(30, 0, 30, 10, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.netherrack), Composition.getStandard(60, 0, 0, 80, 0));
		
		
		RedvalueDictionary.registerNative(new ItemStack(Block.gravel), Composition.getStandard(60, 10, 0, 0, 15));
		RedvalueDictionary.registerNative(new ItemStack(Item.flint), Composition.getStandard(60, 0, 0, 0, 15));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cactus), Composition.getStandard(20, 0, 110, 20, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 0), Composition.getStandard(0, 0, 100, 0, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 1), Composition.getStandard(0, 0, 20, 20, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 2), Composition.getStandard(20, 0, 90, 20, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 3), Composition.getStandard(0, 0, 100, 0, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 4), Composition.getStandard(1000, 0, 0, 0, 200));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 5), Composition.getStandard(500, 0, 15, 15, 100));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 6), Composition.getStandard(510, 0, 55, 10, 10));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 7), Composition.getStandard(6.125f, 0, 31, 1, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 8), Composition.getStandard(12.5f, 0, 62.5f, 15, 5));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 9), Composition.getStandard(12.5f, 0, 17.5f, 15, 15));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 10), Composition.getStandard(27.5f, 0, 57.5f, 15, 15));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 11), Composition.getStandard(20, 0, 20, 0, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 12), Composition.getStandard(512.5f, 0, 7.5f, 5, 105));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 13), Composition.getStandard(256.125f, 0, 17, 12.5f, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 14), Composition.getStandard(10, 0, 20, 10, 20));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 15), Composition.getStandard(25, 0, 15, 10, 10));
		RedvalueDictionary.registerNative(new ItemStack(Item.feather), Composition.getStandard(0, 120, 30, 0, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.silk), Composition.getStandard(0, 40, 10, 0, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.seeds), Composition.getStandard(0, 0, 90, 10, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.pumpkinSeeds), Composition.getStandard(0, 0, 30, 40, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.melonSeeds), Composition.getStandard(0, 0, 80, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.plantRed), Composition.getStandard(0, 0, 30, 30, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.plantYellow), Composition.getStandard(30, 0, 30, 0, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.wheat), Composition.getStandard(0, 0, 100, 20, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.netherStalk), Composition.getStandard(30, 0, 0, 120, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.rottenFlesh), Composition.getStandard(0, 0, 0, 90, 10));
		RedvalueDictionary.registerNative(new ItemStack(Item.slimeBall), Composition.getStandard(0, 30, 60, 0, 0));
		
		
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 0), Composition.getStandard(0, 0, 150, 150, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 1), Composition.getStandard(0, 0, 150, 150, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 2), Composition.getStandard(0, 0, 150, 150, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 3), Composition.getStandard(0, 0, 150, 150, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.mushroomBrown), Composition.getStandard(70, 0, 30, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.mushroomRed), Composition.getStandard(0, 0, 30, 70, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 0), Composition.getStandard(0, 0, 80, 20, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 1), Composition.getStandard(0, 0, 80, 20, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 2), Composition.getStandard(0, 0, 80, 20, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 3), Composition.getStandard(0, 0, 80, 20, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.reed), Composition.getStandard(0, 0, 150, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.potato), Composition.getStandard(30, 0, 120, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.carrot), Composition.getStandard(30, 0, 110, 10, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.egg), Composition.getStandard(0, 0, 100, 0, 0));
		
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 0), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 1), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 2), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 3), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 4), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 5), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 6), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 7), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 8), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 9), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 10), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 11), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 12), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 13), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 14), Composition.getStandard(0, 160, 40, 0, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 15), Composition.getStandard(0, 160, 40, 0, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.slowSand), Composition.getStandard(50, 0, 0, 50, 200));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.obsidian), Composition.getStandard(200, 0, 0, 20000, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.oreRedstone), Composition.getStandard(1000, 0, 0, 1000, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.porkRaw), Composition.getStandard(0, 0, 100, 100, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.beefRaw), Composition.getStandard(0, 0, 100, 100, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.fishRaw), Composition.getStandard(0, 0, 100, 100, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.chickenRaw), Composition.getStandard(0, 0, 100, 100, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.leather), Composition.getStandard(0, 0, 0, 600, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.clay), Composition.getStandard(100, 10, 100, 40, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.bone), Composition.getStandard(50, 0, 30, 20, 10));
		
		
		RedvalueDictionary.registerNative(new ItemStack(Item.appleRed), Composition.getStandard(10, 10, 120, 5, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.spiderEye), Composition.getStandard(0, 20, 80, 0, 100));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.melon), Composition.getStandard(0, 0, 360, 40, 0));
		RedvalueDictionary.registerNative(new ItemStack(Block.pumpkin), Composition.getStandard(0, 0, 120, 160, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestoneMossy), Composition.getStandard(10, 0, 40, 0, 0));
		

		RedvalueDictionary.registerNative(new ItemStack(Item.gunpowder), Composition.getStandard(100, 100, 0, 800, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.fermentedSpiderEye), Composition.getStandard(0, 40, 160, 0, 200));
		RedvalueDictionary.registerNative(new ItemStack(Item.saddle), Composition.getStandard(100, 100, 0, 300, 100));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreIron), Composition.getStandard(1000, 0, 0, 1000, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.glowstone), Composition.getStandard(40, 1, 1, 30, 30));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.blazePowder), Composition.getStandard(0, 10, 0, 580, 10));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.enderPearl), Composition.getStandard(0, 0, 0, 0, 10000));
		RedvalueDictionary.registerNative(new ItemStack(Item.emerald), Composition.getStandard(3000, 0, 0, 0, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.blazeRod), Composition.getStandard(0, 40, 0, 2320, 40));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreGold), Composition.getStandard(2000, 0, 0, 2000, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.ghastTear), Composition.getStandard(0, 4000, 0, 4000, 2000));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.diamond), Composition.getStandard(20000, 0, 0, 82400, 0));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.bucketLava), Composition.getStandard(3000, 0, 0, 23000, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.bucketWater), Composition.getStandard(3000, 0, 1000, 3000, 0));
		RedvalueDictionary.registerNative(new ItemStack(Item.bucketMilk), Composition.getStandard(3000, 0, 1000, 3000, 0));


		
		RedvalueDictionary.registerNative(new ItemStack(Blocks.decoration.blockID, 1, BlockIndex.GRANITE_METADATA), Composition.getStandard(300, 0, 0, 100, 0));
		RedvalueDictionary.registerNative(new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), Composition.getStandard(100, 0, 0, 300, 100));
		RedvalueDictionary.registerNative(new ItemStack(Items.matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA), Composition.getStandard(0, 0, 0, 0, 100));
		RedvalueDictionary.registerNative(new ItemStack(Items.matter, 1, ItemIndex.MATTER_INSTABLE_METADATA), Composition.getStandard(0, 0, 0, 0, 500));
		RedvalueDictionary.registerNative(new ItemStack(Items.matter, 1, ItemIndex.MATTER_STABLE_METADATA), Composition.getStandard(0, 0, 0, 0, 1000));
	}
	
}
