package redmagic.lib.crafting;

import redmagic.helpers.LogHelper;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import net.minecraft.item.ItemStack;

public class ShapedRecipe {
	public ItemStack output;
	
	public ItemStack[] input = new ItemStack[9];
	public Talent need;
	
	public ShapedRecipe(ItemStack output, Talent need, ItemStack[] input){
		this.output = output;
		this.need = need;
		this.input = input;
	}
	
	public boolean match(ItemStack[] test, PlayerInformation information, int[] order){
		int count = 0;
		LogHelper.log("------------_");
		if(information.pathManager.isTalentUnlocked(need)){
			for(int index: order){
				LogHelper.log(count + " " + test[index] + " " + input[count]);
				if((test[index] == null && input[count] != null) || (test[index] != null && input[count] == null))return false;
				if(test[index] != null && input[count] != null && !test[index].isItemEqual(input[count]))return false;
				count++;
			}
			return true;
		}
		return false;
	}
}
