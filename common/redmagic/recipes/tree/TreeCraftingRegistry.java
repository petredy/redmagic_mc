package redmagic.recipes.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import redmagic.recipes.Recipe;
import redmagic.recipes.ShapelessRecipe;

public class TreeCraftingRegistry {
	public static List<TreeRecipe> recipes = new ArrayList<TreeRecipe>();
	public static List<TreeShapelessRecipe> shapelessRecipes = new ArrayList<TreeShapelessRecipe>();
	
	public static void register(ItemStack output, ItemStack[] input, int intelligence, int strength, int capacity, int illusion, int satisfaction){
		recipes.add(new TreeRecipe(output, input, intelligence, strength, capacity, illusion, satisfaction));
	}
	
	public static void registerShapeless(ItemStack output, ItemStack[] input, int intelligence, int strength, int capacity, int illusion, int satisfaction){
		shapelessRecipes.add(new TreeShapelessRecipe(output, input, intelligence, strength, capacity, illusion, satisfaction));
	}
	
	public static ItemStack find(ItemStack[] input, ItemStack soul){
		Iterator<TreeRecipe> it = recipes.iterator();
		while(it.hasNext()){
			TreeRecipe recipe = (TreeRecipe) it.next();
			if(recipe.match(input, soul)){
				return recipe.output;
			}
		}
		Iterator<TreeShapelessRecipe> it1 = shapelessRecipes.iterator();
		while(it1.hasNext()){
			TreeShapelessRecipe recipe = (TreeShapelessRecipe)it1.next();
			if(recipe.match(input, soul)){
				return recipe.output;
			}
		}
		return null;
	}

	public static boolean contains(ItemStack stack) {
		if(stack == null)return false;
		Iterator<TreeRecipe> it = recipes.iterator();
		while(it.hasNext()){
			Recipe recipe = (Recipe) it.next();
			if(recipe.output.isItemEqual(stack))return true;
		}
		
		Iterator<TreeShapelessRecipe> it1 = shapelessRecipes.iterator();
		while(it1.hasNext()){
			ShapelessRecipe recipe = (ShapelessRecipe) it1.next();
			if(recipe.output.isItemEqual(stack))return true;
		}
		return false;
	}
}
