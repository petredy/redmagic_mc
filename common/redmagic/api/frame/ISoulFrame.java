package redmagic.api.frame;

import net.minecraft.item.ItemStack;

public interface ISoulFrame {
	public abstract void storeSoul(ItemStack soul);
	
	public abstract ItemStack getSoul();
}
