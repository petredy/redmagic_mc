package com.petredy.redmagic.recipes.sieve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class SieveDictionary {

	public static List<SieveRecipe> recipes = new ArrayList<SieveRecipe>();
	
	public static void register(ItemStack output, ItemStack input){
		recipes.add(new SieveRecipe(output, input));
	}
	
	public static ItemStack findOutput(ItemStack input){
		for(SieveRecipe recipe: recipes){
			if(recipe.matches(input))return recipe.output;
		}
		return null;
	}
	
	public static List<ItemStack> getInputs(){
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		for(SieveRecipe recipe: recipes){
			inputs.add(recipe.input);
		}
		return inputs;
	}
}
