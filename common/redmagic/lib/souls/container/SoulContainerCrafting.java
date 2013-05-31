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
		if(slot == 0 && entity != null){
			for(int i = 1; i < 10; i++){
				entity.decrStackSize(i, 1);
			}
		}
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(ContainerTree container, EntityPlayer player, int slot) {
		Slot slotData = (Slot) container.inventorySlots.get(slot);
		if(slotData != null){
			ItemStack stack = slotData.getStack();
			if(stack != null){
				if(slot < container.entity.getSizeInventory()){
					if(!container.mergeItemStack(stack, container.entity.getSizeInventory(), container.inventorySlots.size(), false)){
						return null;
					}
				}else if(slot >= container.entity.getSizeInventory()){
					if(!container.mergeItemStack(stack, 1, container.entity.getSizeInventory(), false)){
						return null;
					}
				}
				if(stack.stackSize == 0){
					slotData.putStack(null);
					stack = null;
				}
			}
		}
		return null;
	}

}
