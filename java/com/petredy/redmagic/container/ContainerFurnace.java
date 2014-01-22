package com.petredy.redmagic.container;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.container.slot.SlotOutput;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.machines.MachineFurnace;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.PlayerUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerFurnace extends Container {

	public IMachineHandler machine;
	public MachineFurnace furnace;
	
	public ContainerFurnace(EntityPlayer player, IMachineHandler machine){
		this.machine = machine;
		this.furnace = (MachineFurnace) machine.getMachineOnSide(player.getEntityData().getInteger("redmagic.machine.side"));
		if(furnace != null){
			this.addSlotToContainer(new Slot(furnace.inventory, 0, 56, 34));
			this.addSlotToContainer(new SlotOutput(furnace.inventory, 1, 116, 35));
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
                if (!this.mergeItemStack(itemstack1, 2, 4 * 9 + 2, true))
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
