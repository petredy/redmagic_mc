package com.petredy.redmagic.recipes.sieve;

import net.minecraft.item.ItemStack;

public class SieveRecipe {

	public ItemStack input;
	public ItemStack output;
	
	public SieveRecipe(ItemStack output, ItemStack input){
		this.output = output;
		this.input = input;
	}
	
	public boolean matches(ItemStack input){
		return input != null && input.isItemEqual(this.input);
	}
}
