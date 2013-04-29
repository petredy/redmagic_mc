package redmagic.recipes;

import redmagic.api.recipes.IRecipe;
import net.minecraft.item.ItemStack;

public class Recipe implements IRecipe{
	public ItemStack output;
	public ItemStack[] matrix;
	
	public Recipe(ItemStack output, ItemStack[] matrix){
		this.output = output;
		this.matrix = matrix;
	}
	
	public boolean match(ItemStack[] input){
		for(int i = 0; i < 9; i++){
			ItemStack stack = this.matrix[i];
			ItemStack slot = input[i];
			if((stack == null && slot != null) || (stack != null && slot == null) || (stack != null && slot != null && !stack.isItemEqual(slot))){
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return this.output;
	}

	@Override
	public ItemStack[] getInput() {
		return this.matrix;
	}
}
