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
import com.petredy.redmagic.tileentities.TileEntityTradingChest;
import com.petredy.redmagic.trading.TradingItem;
import com.petredy.redmagic.trading.TradingManager;
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

public class ContainerTradingChest extends Container{

	public TileEntityTradingChest entity;
	
	public InventoryBasic inv = new InventoryBasic("tmp", true, 45);
	
	public Object[] list = TradingManager.getAllItems();
	
	public ContainerTradingChest(EntityPlayer player, TileEntityTradingChest tileEntity){
		super();
		this.entity = tileEntity;
		this.entity.openChest();
		
		this.addSlotToContainer(new Slot(this.entity, 0, 173, 150));
		
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
		if(index >= 0 && index < this.inventorySlots.size()){
			Slot slot = (Slot) this.inventorySlots.get(index);
			ItemStack crystal = this.entity.getStackInSlot(0);
			if(index <= 45 && index > 0){
				if(slot != null && crystal != null && slot.getStack() != null && player.inventory.getItemStack() == null){
					ItemStack stack = slot.getStack();
					if(shift == 1 && right == 1){
					
					}else if(shift == 1){	
						return this.buy(stack, crystal, stack.getMaxStackSize(), player);
					}else if(right == 1){
						return this.buy(stack, crystal, stack.getMaxStackSize() / 2, player);
					}else{
						return this.buy(stack, crystal, 1, player);
					}
				}else if(crystal != null && player.inventory.getItemStack() != null){
					ItemStack rtn = this.sell(player.inventory.getItemStack(), crystal, player);
					player.inventory.setItemStack(rtn);
					return rtn;
				}
				return null;
			}else{
				if(slot != null && crystal != null && slot.getStack() != null && shift == 1 && index > 45 && index < this.inventorySlots.size()){
					ItemStack rtn = this.sell(slot.getStack(), crystal, player);
					slot.putStack(rtn);
					player.inventory.setItemStack(null);
					return null;
				}
				return super.slotClick(index, right, shift, player);
			}
		}
		return null;
    }
	
	public ItemStack buy(ItemStack stack, ItemStack crystal, int amount, EntityPlayer player){
		float costs = TradingManager.getItemPrice(stack) * amount;
		if(player.worldObj.isRemote && TradingUtils.getMoney(crystal) >= costs && TradingManager.getItemAmount(stack) >= amount){
			
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketBuyItem(stack, amount, this.entity.xCoord, this.entity.yCoord, this.entity.zCoord)));
			
			TradingUtils.setMoney(crystal, TradingUtils.getMoney(crystal) - costs);
			ItemStack output = new ItemStack(stack.itemID, amount, stack.getItemDamage());
			player.playSound(Sounds.CHEST_CLOSE, 1.0F, 1.0F);
			player.inventory.addItemStackToInventory(output);
			player.inventory.setItemStack(null);
			return output;
		}
		return null;
	}
	
	public ItemStack sell(ItemStack stack, ItemStack crystal, EntityPlayer player){
		float money = TradingManager.getItemPrice(stack) * stack.stackSize;
		if(player.worldObj.isRemote){
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketSellItem(stack, stack.stackSize, this.entity.xCoord, this.entity.yCoord, this.entity.zCoord)));
			player.playSound(Sounds.CHEST_CLOSE, 1.0F, 1.0F);
			TradingUtils.setMoney(crystal, TradingUtils.getMoney(crystal) + money);
			return null;
		}
		return stack;
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
                    this.inv.setInventorySlotContents(l + k * 9, list[i1] instanceof ItemStack ? (ItemStack)list[i1] : ((TradingItem)list[i1]).item);
                }
                else
                {
                    this.inv.setInventorySlotContents(l + k * 9, (ItemStack)null);
                }
            }
        }
    }

}
