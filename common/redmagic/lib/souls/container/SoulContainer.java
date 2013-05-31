package redmagic.lib.souls.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import redmagic.containers.ContainerTree;
import redmagic.tileentities.tree.TileEntityTreeWood;

public abstract class SoulContainer {

	public abstract void init(ContainerTree container, TileEntityTreeWood entity);
	
	public abstract boolean onClick(int slot, int shift, int right, EntityPlayer player, ItemStack stack);
	
	public abstract ItemStack transferStackInSlot(ContainerTree container, EntityPlayer player, int slot);
	
}
