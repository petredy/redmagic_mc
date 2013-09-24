package com.petredy.redmagic.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.petredy.redmagic.container.slot.SlotOutput;
import com.petredy.redmagic.player.PlayerInformation;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.PlayerUtils;

public class ContainerPlayerInventory extends Container{
	
	public PlayerInformation information;

	public ContainerPlayerInventory(EntityPlayer player){
		super();
		this.information = PlayerUtils.getPlayerInformation(player);
		
		if(information != null)
		for (int i = 0; i < 6; ++i){
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(information.inventory, j + i * 9,  8 + j * 18, 18 + i * 18));
            }
        }

		
		this.bindPlayerInventory(player.inventory);
	}
	
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
		PlayerUtils.setPlayerInformation(par1EntityPlayer, information);
		super.onContainerClosed(par1EntityPlayer);
    }
	
	
	public void bindPlayerInventory(InventoryPlayer inv){
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inv, j + i * 9 + 9,  8 + j * 18, 140 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(inv, i, 8 + i * 18, 198));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 6 * 9)
            {
                if (!this.mergeItemStack(itemstack1, 6 * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 6 * 9, false))
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
