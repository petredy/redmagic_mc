package com.petredy.redmagic.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOnlyItem extends Slot{

	public Class cl;
	
	public SlotOnlyItem(Class cl, IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		this.cl = cl;
	}
	
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        return par1ItemStack != null && this.cl.isInstance(par1ItemStack.getItem());
    }

}
