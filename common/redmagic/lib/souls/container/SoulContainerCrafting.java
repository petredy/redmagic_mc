package redmagic.lib.souls.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import redmagic.containers.ContainerTree;
import redmagic.core.Logger;
import redmagic.recipes.worktable.WorkTableRegistry;
import redmagic.slots.SlotOutput;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulContainerCrafting extends SoulContainer{

	public TileEntityTreeWood entity;
	
	@Override
	public void init(ContainerTree container, TileEntityTreeWood entity) {
		this.entity = entity;
		
		container.addSlot(new SlotOutput(entity, 0, 124, 35));
		
		container.addSlot(new Slot(entity, 1, 30, 17));
		container.addSlot(new Slot(entity, 2, 48, 17));
		container.addSlot(new Slot(entity, 3, 66, 17));
		container.addSlot(new Slot(entity, 4, 30, 35));
		container.addSlot(new Slot(entity, 5, 48, 35));
		container.addSlot(new Slot(entity, 6, 66, 35));
		container.addSlot(new Slot(entity, 7, 30, 53));
		container.addSlot(new Slot(entity, 8, 48, 53));
		container.addSlot(new Slot(entity, 9, 66, 53));
	}

	@Override
	public boolean onClick(int slot, int shift, int right, EntityPlayer player, ItemStack stack) {
		return true;
	}

}
