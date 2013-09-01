package redmagic.lib.crafting;

import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import net.minecraft.item.ItemStack;

public class ShapelessRecipe {
	
	public ItemStack output;
	public ItemStack[] input;
	public Talent need;
	
	public ShapelessRecipe(ItemStack output, Talent talent, ItemStack[] input){
		this.output = output;
		this.need = talent;
		this.input = input;
	}
	
	
	public boolean matches(ItemStack[] test, PlayerInformation information, int[] order){
		ItemStack[] testInput = input;
		int count = 0;
		if(information.pathManager.isTalentUnlocked(need)){
			for(int index: order){
				for(ItemStack stack: testInput){
					if(stack != null && test[index] != null && stack.isItemEqual(test[index]))testInput[count] = null;
					count++;
				}
				count = 0;
			}
			for(ItemStack slot: testInput){
				if(slot != null)return false;
			}
			return true;
		}
		return false;
	}
}
