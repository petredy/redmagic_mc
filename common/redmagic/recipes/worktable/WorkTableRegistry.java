package redmagic.recipes.worktable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;

import redmagic.recipes.Recipe;
import redmagic.recipes.ShapelessRecipe;

public class WorkTableRegistry {
	
	public static List<Recipe> recipes = new ArrayList<Recipe>();
	public static List<ShapelessRecipe> shapelessRecipes = new ArrayList<ShapelessRecipe>();
	
	public static void register(ItemStack output, ItemStack[] input){
		recipes.add(new Recipe(output, input));
	}
	
	public static void registerShapeless(ItemStack output, ItemStack[] input){
		shapelessRecipes.add(new ShapelessRecipe(output, input));
	}
	
	public static ItemStack find(ItemStack[] input){
		Iterator<Recipe> it = recipes.iterator();
		while(it.hasNext()){
			Recipe recipe = (Recipe) it.next();
			if(recipe.match(input)){
				return recipe.output;
			}
		}
		Iterator<ShapelessRecipe> it1 = shapelessRecipes.iterator();
		while(it1.hasNext()){
			ShapelessRecipe recipe = (ShapelessRecipe)it1.next();
			if(recipe.match(input)){
				return recipe.output;
			}
		}
		return null;
	}

	public static boolean contains(ItemStack stack) {
		if(stack == null)return false;
		Iterator<Recipe> it = recipes.iterator();
		while(it.hasNext()){
			Recipe recipe = (Recipe) it.next();
			if(recipe.output.isItemEqual(stack))return true;
		}
		
		Iterator<ShapelessRecipe> it1 = shapelessRecipes.iterator();
		while(it1.hasNext()){
			ShapelessRecipe recipe = (ShapelessRecipe) it1.next();
			if(recipe.output.isItemEqual(stack))return true;
		}
		return false;
	}

	public static int getCraftingIndex(ItemStack stack) {
		int count = 0;
		if(stack == null)return count;
		Iterator<Recipe> it = recipes.iterator();
		while(it.hasNext()){
			Recipe recipe = (Recipe) it.next();
			if(recipe.output.isItemEqual(stack))return count;
			count++;
		}
		
		Iterator<ShapelessRecipe> it1 = shapelessRecipes.iterator();
		while(it1.hasNext()){
			ShapelessRecipe recipe = (ShapelessRecipe) it1.next();
			if(recipe.output.isItemEqual(stack))return count;
			count++;
		}
		return 0;
	}

}
