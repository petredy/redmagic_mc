package redmagic.recipes;

import redmagic.api.recipes.IRecipe;
import net.minecraft.item.ItemStack;

public class ShapelessRecipe implements IRecipe{
	public ItemStack output;
	public ItemStack[] input;
	
	public ShapelessRecipe(ItemStack output, ItemStack[] input){
		this.output = output;
		this.input = input;
	}
	
	public boolean match(ItemStack[] input){
		ItemStack[] matrix = new ItemStack[this.input.length];
		for(int i = 0; i < this.input.length; i++){
			matrix[i] = this.input[i].copy();
		}
		
		for(int i = 0; i < input.length; i++){
			ItemStack slot = input[i];
			for(int j = 0; j < matrix.length; j++){
				ItemStack stack = matrix[j];
				if(slot == null && stack == null)continue;
				if(slot != null && stack != null && slot.isItemEqual(stack)){
					matrix[j] = null;
					continue;
				}
			}
		}
		for(int i = 0; i < matrix.length; i++){
			if(matrix[i] != null)return false;
		}
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return this.output;
	}

	@Override
	public ItemStack[] getInput() {
		return this.input;
	}
}
