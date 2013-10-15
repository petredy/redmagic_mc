package com.petredy.redmagic.container;

import com.petredy.redmagic.container.slot.SlotOutput;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.machines.MachineCompactor;
import com.petredy.redmagic.machines.MachineFreezer;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCompactor extends Container {

	public TileEntityMachine machine;
	
	public ContainerCompactor(EntityPlayer player, TileEntityMachine machine){
		this.machine = machine;
		MachineCompactor compactor = (MachineCompactor) machine.getMachine(Machines.COMPACTOR_METADATA);
		if(compactor != null){
			this.addSlotToContainer(new SlotOutput(compactor.inventory, 0, 80, 35));
			this.addSlotToContainer(new Slot(compactor.inventory, 1, 44, 17));
			this.addSlotToContainer(new Slot(compactor.inventory, 2, 116, 17));
			this.addSlotToContainer(new Slot(compactor.inventory, 3, 44, 53));
			this.addSlotToContainer(new Slot(compactor.inventory, 4, 116, 53));
		}
		this.bindPlayerInventory(player.inventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
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

            if (par2 < 2)
            {
                if (!this.mergeItemStack(itemstack1, 2, 4 * 9, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 2, false))
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
