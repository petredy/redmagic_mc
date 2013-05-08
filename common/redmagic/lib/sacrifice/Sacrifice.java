package redmagic.lib.sacrifice;

import java.util.List;

import redmagic.api.frame.ISoul;
import redmagic.helpers.ModifierHelper;
import net.minecraft.item.ItemStack;

public class Sacrifice {
	public ItemStack input;
	public int damage = 5;
	public Sacrifice(ItemStack input){
		this.input = input;
	}
	
	public boolean matches(ItemStack input){
		return this.input.isItemEqual(input);
	}
	
	public void sacrifice(ItemStack soulStack, ISoul soul, ItemStack input, List<ModifierHelper> modifiers){
		
	}
	
	// Returns true when it destroys the soul
	public boolean damage(ItemStack soulStack, ISoul soul, ItemStack input, List<ModifierHelper> modifiers){
		soul.setSatisfaction(soulStack, soul.getSatisfaction(soulStack) - this.damage);
		if(soul.getSatisfaction(soulStack) <= 0){
			soulStack = null;
			return true;
		}
		return false;
	}
}
