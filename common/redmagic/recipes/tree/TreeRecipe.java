package redmagic.recipes.tree;

import net.minecraft.item.ItemStack;
import redmagic.helpers.SoulHelper;
import redmagic.recipes.Recipe;

public class TreeRecipe extends Recipe{

	public int intelligence, strength, capacity, illusion, satisfaction;
	
	public TreeRecipe(ItemStack output, ItemStack[] matrix, int intelligence, int strength, int capacity, int illusion, int satisfaction) {
		super(output, matrix);
		this.intelligence = intelligence;
		this.strength = strength;
		this.capacity = capacity;
		this.illusion = illusion;
		this.satisfaction = satisfaction;
	}
	
	public boolean match(ItemStack[] input, ItemStack soul){
		if(this.intelligence <= SoulHelper.getIntelligence(soul) && this.strength <= SoulHelper.getIntelligence(soul) && this.capacity <= SoulHelper.getCapacity(soul) && this.illusion <= SoulHelper.getIllusion(soul) && this.satisfaction <= SoulHelper.getSatisfaction(soul)){
			for(int i = 0; i < 9; i++){
				ItemStack stack = this.matrix[i];
				ItemStack slot = input[i];
				if((stack == null && slot != null) || (stack != null && slot == null) || (stack != null && slot != null && !stack.isItemEqual(slot))){
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}

}
