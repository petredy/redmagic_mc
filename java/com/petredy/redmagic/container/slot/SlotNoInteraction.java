package com.petredy.redmagic.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotNoInteraction extends Slot{

	public SlotNoInteraction(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	public boolean canTakeStack(EntityPlayer par1EntityPlayer)
    {
        return false;
    }
	
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
	
}
