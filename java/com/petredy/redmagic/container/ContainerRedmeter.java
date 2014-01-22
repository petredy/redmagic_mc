package com.petredy.redmagic.container;

import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.utils.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRedmeter extends Container {

	public InventoryBasic inventory;
	
	public ContainerRedmeter(EntityPlayer player){
		super();
		
		 inventory = new InventoryBasic(Items.redmeter.getUnlocalizedName(), false, 1);
		
		this.addSlotToContainer(new Slot(inventory, 0, 24, 27));
		
		this.bindPlayerInventory(player.inventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
		InventoryUtils.dropInventory(inventory, par1EntityPlayer.worldObj, par1EntityPlayer.posX, par1EntityPlayer.posY, par1EntityPlayer.posZ);
		super.onContainerClosed(par1EntityPlayer);
    }
	
	public void bindPlayerInventory(InventoryPlayer inv){
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inv, j + i * 9 + 9,  8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
        }
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 1)
            {
                if (!this.mergeItemStack(itemstack1, 1, 4 * 9, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 1, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
