package com.petredy.redmagic.container;

import com.petredy.redmagic.container.slot.SlotOutput;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.machines.MachineDeintegrator;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDeintegrator extends Container {

	public TileEntityMachine machine;
	
	public ContainerDeintegrator(EntityPlayer player, TileEntityMachine machine){
		this.machine = machine;
		MachineDeintegrator deintegrator = (MachineDeintegrator) machine.getMachine(Machines.DEINTEGRATOR_METADATA);
		
		this.addSlotToContainer(new Slot(deintegrator.inventory, 0, 80, 35));
		this.addSlotToContainer(new SlotOutput(deintegrator.inventory, 1, 80, 54));
		
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