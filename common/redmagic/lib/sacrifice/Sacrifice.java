package redmagic.lib.sacrifice;

import java.util.List;

import redmagic.api.frame.ISoul;
import redmagic.helpers.ModifierHelper;
import net.minecraft.item.ItemStack;

public class Sacrifice {
	public ItemStack input;
	
	public Sacrifice(ItemStack input){
		this.input = input;
	}
	
	public void sacrifice(ItemStack soulStack, ISoul soul, ItemStack input, List<ModifierHelper> modifiers){
		
	}
}
