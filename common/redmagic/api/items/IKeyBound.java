package redmagic.api.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {

	
	public abstract void doKeyBindingAction(EntityPlayer player, ItemStack stack, String key);
}
