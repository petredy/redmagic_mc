package com.petredy.redmagic.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.petredy.redmagic.container.slot.SlotNoInteraction;
import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.network.PacketBuyItem;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketSellItem;
import com.petredy.redmagic.soul.SoulStack;
import com.petredy.redmagic.tileentities.TileEntitySoulChest;
import com.petredy.redmagic.trading.TradingItem;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.TradingUtils;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSoulChest extends Container{

	public TileEntitySoulChest entity;
	
	public InventoryBasic inv = new InventoryBasic("tmp", true, 45);
	
	public Object[] list;
	
	public ContainerSoulChest(EntityPlayer player, TileEntitySoulChest tileEntity){
		super();
		this.entity = tileEntity;
		this.entity.openChest();
		LogUtils.log(entity.soul);
		list = entity.soul != null ? entity.soul.inventory.stacks.values().toArray() : new Object[0];
		
		for (int i = 0; i < 5; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlotToContainer(new SlotNoInteraction(this.inv, i * 9 + j, 9 + j * 18, 18 + i * 18));
            }
        }
		
		this.bindPlayerInventory(player.inventory);
		this.scrollTo(0.0F);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventory){
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 9 + j * 18, 112 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 9 + i * 18, 169));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.entity.isUseableByPlayer(entityplayer);
	}
	
	@Override
    public void onContainerClosed(EntityPlayer entityPlayer) {

        super.onContainerClosed(entityPlayer);
        this.entity.closeChest();
    }
	
	public ItemStack slotClick(int index, int right, int shift, EntityPlayer player)
    {
		if(index >= 0 && index < this.inventorySlots.size() && entity.soul != null){
			Slot slot = (Slot) this.inventorySlots.get(index);
			ItemStack crystal = this.entity.getStackInSlot(0);
			if(index <= 44 && index > 0){
				if(slot != null && slot.getStack() != null && player.inventory.getItemStack() == null){
					ItemStack stack = slot.getStack();
					
					if(shift == 1 && right == 1){
					
					}else if(shift == 1){	
						LogUtils.log("take a stack");
					}else if(right == 1){
						LogUtils.log("take half a stack");
					}else{
						LogUtils.log("take one");
					}
					return null;
				}else if(player.inventory.getItemStack() != null){
					LogUtils.log("dump it in dude");
					ItemStack stack = player.inventory.getItemStack();
					stack = InventoryUtils.addItemStackToInventory(entity.soul.inventory, stack);
					player.inventory.setItemStack(stack);
					return stack;
				}
				return null;
			}else{
				if(slot != null && slot.getStack() != null && shift == 1 && index > 45 && index < this.inventorySlots.size()){
					//ItemStack rtn = this.sell(slot.getStack(), crystal, player);
					//slot.putStack(rtn);
					//player.inventory.setItemStack(null);
					//return null;
					
					if(entity.soul != null){
						ItemStack stack = slot.getStack();
						LogUtils.log("add " + stack);
						stack = InventoryUtils.addItemStackToInventory(entity.soul.inventory, stack);
						return stack;
					}
				}
				return super.slotClick(index, right, shift, player);
			}
		}
		return null;
    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return null;
	}
	
	public void scrollTo(float par1)
    {
        int i = list.length / 9 - 5 + 1;
        int j = (int)((double)(par1 * (float)i) + 0.5D);

        if (j < 0)
        {
            j = 0;
        }
        
        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 9; ++l)
            {
                int i1 = l + (k + j) * 9;
                
                if (i1 >= 0 && i1 < list.length)
                {
                	int count = 0;
                    this.inv.setInventorySlotContents(l + k * 9, list[i1] instanceof ItemStack ? (ItemStack)list[i1] : ((SoulStack)list[i1]).stack);
                }
                else
                {
                    this.inv.setInventorySlotContents(l + k * 9, (ItemStack)null);
                }
            }
        }
    }

}
