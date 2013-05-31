package redmagic.api.glasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IHelpable {
	public abstract void help(EntityPlayer player, ItemStack stack);
}
