package redmagic.recipes.tree;

import net.minecraft.item.ItemStack;
import redmagic.helpers.SoulHelper;
import redmagic.recipes.ShapelessRecipe;

public class TreeShapelessRecipe extends ShapelessRecipe{
	
	public int intelligence, strength, capacity, illusion, satisfaction;
	
	public TreeShapelessRecipe(ItemStack output, ItemStack[] input, int intelligence, int strength, int capacity, int illusion, int satisfaction){
		super(output, input);
		this.intelligence = intelligence;
		this.strength = strength;
		this.capacity = capacity;
		this.illusion = illusion;
		this.satisfaction = satisfaction;
	}
	
	public boolean match(ItemStack[] input, ItemStack soul){
		if(this.intelligence <= SoulHelper.getIntelligence(soul) && this.strength <= SoulHelper.getIntelligence(soul) && this.capacity <= SoulHelper.getCapacity(soul) && this.illusion <= SoulHelper.getIllusion(soul) && this.satisfaction <= SoulHelper.getSatisfaction(soul)){
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
		}else{
			return false;
		}
		return true;
	}
}
