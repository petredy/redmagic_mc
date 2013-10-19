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
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestone), Composition.getStandard(60, 1, 1, 10, 4));
		RedvalueDictionary.registerNative(new ItemStack(Block.dirt), Composition.getStandard(30, 10, 10, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.grass), Composition.getStandard(40, 20, 10, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.sand), Composition.getStandard(30, 1, 30, 10, 10));
		RedvalueDictionary.registerNative(new ItemStack(Block.netherrack), Composition.getStandard(60, 1, 1, 80, 1));
		
		
		RedvalueDictionary.registerNative(new ItemStack(Block.gravel), Composition.getStandard(60, 10, 1, 1, 15));
		RedvalueDictionary.registerNative(new ItemStack(Item.flint), Composition.getStandard(10, 1, 1, 1, 50));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cactus), Composition.getStandard(1, 60, 10, 5, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 0), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 1), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 2), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 3), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 4), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 5), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 6), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 7), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 8), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 9), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 10), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 11), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 12), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 13), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 14), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.dyePowder, 1, 15), Composition.getStandard(1, 1, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.feather), Composition.getStandard(1, 30, 30, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.silk), Composition.getStandard(1, 40, 10, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.seeds), Composition.getStandard(10, 60, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.pumpkinSeeds), Composition.getStandard(10, 60, 40, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.melonSeeds), Composition.getStandard(10, 50, 60, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.plantRed), Composition.getStandard(1, 40, 1, 30, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.plantYellow), Composition.getStandard(30, 40, 1, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.wheat), Composition.getStandard(10, 40, 10, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.netherStalk), Composition.getStandard(30, 1, 1, 50, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.rottenFlesh), Composition.getStandard(1, 30, 10, 30, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.slimeBall), Composition.getStandard(1, 30, 60, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.coal, 1, 1), Composition.getStandard(10, 10, 1, 80, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 0), Composition.getStandard(10, 60, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 1), Composition.getStandard(10, 60, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 2), Composition.getStandard(10, 60, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.wood, 1, 3), Composition.getStandard(10, 60, 1, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.mushroomBrown), Composition.getStandard(30, 30, 10, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.mushroomRed), Composition.getStandard(10, 30, 10, 20, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 0), Composition.getStandard(1, 60, 20, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 1), Composition.getStandard(1, 60, 20, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 2), Composition.getStandard(1, 60, 20, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.sapling, 1, 3), Composition.getStandard(1, 60, 20, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.reed), Composition.getStandard(1, 60, 40, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.potato), Composition.getStandard(10, 40, 10, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.carrot), Composition.getStandard(10, 40, 10, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.egg), Composition.getStandard(1, 70, 30, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 0), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 1), Composition.getStandard(130, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 2), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 3), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 4), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 5), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 6), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 7), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 8), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 9), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 10), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 11), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 12), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 13), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 14), Composition.getStandard(30, 30, 30, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.cloth, 1, 15), Composition.getStandard(30, 30, 30, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.slowSand), Composition.getStandard(60, 30, 30, 30, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.obsidian), Composition.getStandard(70, 1, 1, 60, 20));
		RedvalueDictionary.registerNative(new ItemStack(Block.oreRedstone), Composition.getStandard(40, 1, 1, 40, 40));
		RedvalueDictionary.registerNative(new ItemStack(Item.porkRaw), Composition.getStandard(1, 30, 5, 15, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.beefRaw), Composition.getStandard(1, 30, 5, 15, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.fishRaw), Composition.getStandard(1, 30, 5, 15, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.chickenRaw), Composition.getStandard(1, 30, 5, 15, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.leather), Composition.getStandard(1, 60, 1, 40, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.clay), Composition.getStandard(60, 10, 60, 20, 10));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.bone), Composition.getStandard(10, 50, 10, 5, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreCoal), Composition.getStandard(40, 10, 1, 80, 40));
		RedvalueDictionary.registerNative(new ItemStack(Item.appleRed), Composition.getStandard(10, 40, 40, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.spiderEye), Composition.getStandard(1, 50, 10, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.melon), Composition.getStandard(1, 60, 60, 1, 1));
		RedvalueDictionary.registerNative(new ItemStack(Block.pumpkin), Composition.getStandard(1, 60, 40, 10, 1));
		//RedvalueDictionary.registerNative(new ItemStack(Item.bed), Composition.getStandard(1, 1, 1, 1, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.cobblestoneMossy), Composition.getStandard(60, 30, 30, 1, 1));
		

		RedvalueDictionary.registerNative(new ItemStack(Item.gunpowder), Composition.getStandard(50, 1, 1, 60, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.fermentedSpiderEye), Composition.getStandard(1, 80, 1, 30, 1));
		RedvalueDictionary.registerNative(new ItemStack(Item.saddle), Composition.getStandard(10, 10, 1, 30, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreIron), Composition.getStandard(60, 1, 1, 10, 40));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.glowstone), Composition.getStandard(40, 1, 1, 30, 30));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.blazePowder), Composition.getStandard(1, 10, 1, 90, 40));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.enderPearl), Composition.getStandard(1, 70, 0, 1, 70));
		RedvalueDictionary.registerNative(new ItemStack(Item.emerald), Composition.getStandard(1, 1, 1, 1, 100));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.blazeRod), Composition.getStandard(1, 40, 1, 90, 70));
		
		RedvalueDictionary.registerNative(new ItemStack(Block.oreGold), Composition.getStandard(60, 1, 1, 30, 40));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.ghastTear), Composition.getStandard(1, 80, 1, 80, 1));
		
		RedvalueDictionary.registerNative(new ItemStack(Item.diamond), Composition.getStandard(60, 1, 60, 60, 100));
		
		
		RedvalueDictionary.registerNative(new ItemStack(Blocks.decoration.blockID, 1, BlockIndex.GRANITE_METADATA), Composition.getStandard(70, 1, 1, 10, 10));
		RedvalueDictionary.registerNative(new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA), Composition.getStandard(20, 1, 1, 30, 60));
	}
	
}
