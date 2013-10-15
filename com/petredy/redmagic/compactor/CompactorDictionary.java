package com.petredy.redmagic.compactor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class CompactorDictionary {

	public static List<CompactorRecipe> recipes = new ArrayList<CompactorRecipe>();
	
	public static ItemStack findOutput(ItemStack[] matrix){
		for(CompactorRecipe recipe: recipes){
			if(recipe.matches(matrix))return recipe.output.copy();
		}
		return null;
	}

	public static void addRecipe(ItemStack itemStack, ItemStack[] itemStacks) {
		recipes.add(new CompactorRecipe(itemStack, itemStacks));
	}
	
}
