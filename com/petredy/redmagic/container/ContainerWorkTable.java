package com.petredy.redmagic.container;

import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.container.inventory.MultiInventory;
import com.petredy.redmagic.container.slot.SlotNoInteraction;
import com.petredy.redmagic.container.slot.SlotOutput;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.tileentities.TileEntityWorkTable;
import com.petredy.redmagic.trading.TradingItem;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWorkTable extends Container{

	
	public TileEntityWorkTable entity;
	
	public ContainerWorkTable(EntityPlayer player, TileEntityWorkTable entity){
		super();
		this.entity = entity;
		
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(entity, i, 30 + i % 3 * 18, 17 + i / 3 * 18));
		}
		
		this.addSlotToContainer(new SlotOutput(entity, entity.OUTPUT, 124, 35));

		
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

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
			if(itemstack != null && par2 > this.entity.getSizeInventory()){
				if(!this.mergeItemStack(itemstack1, 0, 8, false)){
					return null;
				}
			}else if(itemstack1 != null && par2 <= this.entity.OUTPUT){
				if(!this.mergeItemStack(itemstack1, 9, par1EntityPlayer.inventory.getSizeInventory(), false)){
					return null;
				}
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
	
	public ItemStack slotClick(int id, int right, int shift, EntityPlayer player)
    {
		if(id == entity.OUTPUT){
			if(shift == 1){
				ItemStack stack = player.inventory.getItemStack();
				boolean contains = InventoryUtils.containsInventoryItems(player.inventory, entity.getCrafting());
				
				while(contains){
					entity.build(player);
					contains = InventoryUtils.containsInventoryItems(player.inventory, entity.getCrafting());
					player.inventory.addItemStackToInventory(entity.getStackInSlot(entity.OUTPUT));
					entity.setInventorySlotContents(entity.OUTPUT, null);
					entity.onInventoryChanged(0);
				}
				return null;
			}else{
				entity.build(player);
				ItemStack stack = super.slotClick(id, 0, 0, player);
				entity.onInventoryChanged(0);
				return stack;
			}
		}
		return super.slotClick(id, right, shift, player);
    }
}
