package com.petredy.redmagic.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrafting extends Container {

	public InventoryBasic inv = new InventoryBasic("", false, 9);
	
	public ContainerCrafting(ItemStack[] crafting) {
		for(int i = 0; i < crafting.length; i++){
			inv.setInventorySlotContents(i, crafting[i]);
			this.addSlotToContainer(new Slot(inv, i, 0, 0));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
