package com.petredy.redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.compactor.CompactorDictionary;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;

public class RecipeHandler {

	public static void init(){
		CompactorDictionary.addRecipe(new ItemStack(Block.netherBrick), new ItemStack[]{
				new ItemStack(Block.netherrack), new ItemStack(Block.netherrack), 
				new ItemStack(Block.netherrack), new ItemStack(Block.netherrack)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Item.blazeRod), new ItemStack[]{
			new ItemStack(Item.blazePowder), new ItemStack(Item.blazePowder),
			new ItemStack(Item.blazePowder), new ItemStack(Item.blazePowder) 
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Item.dyePowder, 1, 15), new ItemStack[]{
			new ItemStack(Item.egg), new ItemStack(Item.egg),
			new ItemStack(Item.egg), new ItemStack(Item.egg)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Block.obsidian, 3), new ItemStack[]{
			new ItemStack(Item.bucketWater), new ItemStack(Item.bucketWater),
			new ItemStack(Item.bucketLava), new ItemStack(Item.bucketLava)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Items.crafting, 8, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA), new ItemStack[]{
			new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA), new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA), 
			new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA), new ItemStack(Blocks.decoration, 1, BlockIndex.GRANITE_METADATA)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Blocks.decoration, 1, BlockIndex.COMPRESSED_OBSIDIAN_METADATA), new ItemStack[]{
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_CONCENTRADTED_GRANITE_METADATA),
			new ItemStack(Block.obsidian), new ItemStack(Block.obsidian)
		});
		
		CompactorDictionary.addRecipe(new ItemStack(Item.clay), new ItemStack[]{
			new ItemStack(Block.dirt), new ItemStack(Block.sand),
			new ItemStack(Block.gravel), null
		});
		
		
		
	}
	
}