package com.petredy.redmagic.container;

import com.petredy.redmagic.api.ITribologicalItem;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.container.slot.SlotOnlyItem;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.utils.BlockUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTribological extends Container {

	public IMachineHandler handler;
	public Machine machine;
	
	public ContainerTribological(EntityPlayer player, IMachineHandler handler){
		this.handler = handler;
		this.machine = handler.getMachineOnSide(BlockUtils.getRotation(player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord(), player, true).ordinal());
		if(machine != null){
			int index = 0;
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					this.addSlotToContainer(new SlotOnlyItem(ITribologicalItem.class, machine.tribological.inventory, index++, 62 + 18 * j, 17 + 18 * i));
				}
			}
		}
		
		this.bindPlayerInventory(player.inventory);
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

            if (par2 < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 4 * 9, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
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
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
