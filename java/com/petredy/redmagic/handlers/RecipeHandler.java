package com.petredy.redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.recipes.compactor.CompactorDictionary;
import com.petredy.redmagic.recipes.sieve.SieveDictionary;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;

public class RecipeHandler {

	public static void init(){
		
		/**
		 * ---------------------------------------------------------------------------------------------------
		 * Compactor
		 */
		CompactorDictionary.addRecipe(new ItemStack(Block.netherBrick, 4), new ItemStack[]{
				new ItemStack(Block.netherrack), new ItemStack(Block.netherrack), 
				new ItemStack(Block.netherrack), new ItemStack(Block.netherrack)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Block.sandStone, 8), new ItemStack[]{
			new ItemStack(Block.sand), new ItemStack(Block.sand), 
			new ItemStack(Block.sand), new ItemStack(Block.sand)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Item.blazeRod), new ItemStack[]{
			new ItemStack(Item.blazePowder), new ItemStack(Item.blazePowder),
			new ItemStack(Item.blazePowder), new ItemStack(Item.blazePowder) 
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Item.dyePowder, 4, 15), new ItemStack[]{
			new ItemStack(Item.egg), new ItemStack(Item.egg),
			new ItemStack(Item.egg), new ItemStack(Item.egg)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Block.obsidian, 5), new ItemStack[]{
			new ItemStack(Item.bucketWater), new ItemStack(Item.bucketWater),
			new ItemStack(Item.bucketLava), new ItemStack(Item.bucketLava)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Items.crafting, 8, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA), new ItemStack[]{
			new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA), new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA), 
			new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA), new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Blocks.decoration, 2, BlockIndex.COMPRESSED_OBSIDIAN_METADATA), new ItemStack[]{
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA),
			new ItemStack(Block.obsidian), new ItemStack(Block.obsidian)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Item.clay, 2), new ItemStack[]{
			new ItemStack(Block.dirt), new ItemStack(Block.sand),
			new ItemStack(Block.gravel), null
		});
		
		
		/**
		 * -----------------------------------------------------------------------------------------------------------------------------------
		 * Sieve
		 */
		
		SieveDictionary.register(new ItemStack(Item.flint, 2), new ItemStack(Block.gravel));
		
		SieveDictionary.register(new ItemStack(Item.seeds), new ItemStack(Block.grass));
		
		SieveDictionary.register(new ItemStack(Block.sapling), new ItemStack(Block.leaves));
		SieveDictionary.register(new ItemStack(Block.sapling, 1, 1), new ItemStack(Block.leaves, 1, 1));
		SieveDictionary.register(new ItemStack(Block.sapling, 1, 2), new ItemStack(Block.leaves, 1, 2));
		SieveDictionary.register(new ItemStack(Block.sapling, 1, 3), new ItemStack(Block.leaves, 1, 3));
	
	}
	
}
