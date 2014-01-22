package com.petredy.redmagic.recipes.compactor;

import net.minecraft.item.ItemStack;

public class CompactorRecipe {

	
	
	
	
	public ItemStack[] recipe;
	
	public ItemStack output;
	
	public CompactorRecipe(ItemStack stack, ItemStack[] matrix){
		this.recipe = matrix;
		this.output = stack;
	}
	
	public boolean matches(ItemStack[] other){
		ItemStack[] list = new ItemStack[4];
		for(int i = 0; i < 4; i++){
			list[i] = this.recipe[i] != null ? this.recipe[i].copy() : null;
		}
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(list[i] == null && other[j] == null)list[i] = null;
				if(list[i] != null && other[j] != null && list[i].isItemEqual(other[j]))list[i] = null;
			}
		}
		for(int i = 0; i < 4; i++){
			if(list[i] != null)return false;
		}
		return true;
	}
	
}
