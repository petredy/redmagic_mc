package redmagic.lib.crafting;

import java.util.ArrayList;
import java.util.List;

import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

import net.minecraft.item.ItemStack;

public class CraftingManager {

	public static List<ShapedRecipe> shapedRecipes = new ArrayList<ShapedRecipe>();
	public static List<ShapelessRecipe> shapelessRecipes = new ArrayList<ShapelessRecipe>();
	
	private static int[] defaultOrder = new int[]{
		0, 1, 2, 3, 4, 5, 6, 7, 8
	};
		
	
	public static void addShapedRecipe(ItemStack output, Talent talent, ItemStack[] input){
		shapedRecipes.add(new ShapedRecipe(output, talent, input));
	}
	
	public static void addShapelessRecipe(ItemStack output, Talent talent, ItemStack[] input){
		shapelessRecipes.add(new ShapelessRecipe(output, talent, input));
	}
	
	public static ItemStack find(ItemStack[] test, PlayerInformation information, int[] order){
		for(ShapedRecipe recipe: shapedRecipes){
			if(recipe.match(test, information, order)){
				return recipe.output;
			}
		}
		for(ShapelessRecipe recipe: shapelessRecipes){
			if(recipe.matches(test, information, order)){
				return recipe.output;
			}
		}
		return null;
	}
	
	public static ItemStack find(ItemStack[] test, PlayerInformation information){
		return find(test, information, defaultOrder);
	}
	
}
