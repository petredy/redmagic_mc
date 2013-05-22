package redmagic.containers;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import redmagic.configuration.Sounds;
import redmagic.core.Logger;
import redmagic.helpers.BankHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.lib.bank.BankManager;
import redmagic.network.PacketBuyItem;
import redmagic.network.PacketHandler;
import redmagic.network.PacketSellItem;
import redmagic.slots.SlotWorkTable;
import redmagic.tileentities.bank.TileEntityBank;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBank extends Container{

	public TileEntityBank entity;
	
	public InventoryBasic inv = new InventoryBasic("tmp", true, 45);
	
	public List<ItemStack> list = BankManager.getAllItems();
	
	public ContainerBank(EntityPlayer player, TileEntityBank tileEntity){
		super();
		this.entity = tileEntity;
		this.entity.openChest();
		
		this.addSlotToContainer(new Slot(this.entity, 0, 173, 150));
		
		for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new SlotWorkTable(this.inv, i * 9 + j, 9 + j * 18, 18 + i * 18));
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
    public void onCraftGuiClosed(EntityPlayer entityPlayer) {

        super.onCraftGuiClosed(entityPlayer);
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
		float costs = BankManager.getItemPrice(stack.itemID, stack.getItemDamage()) * amount;
		if(player.worldObj.isRemote && BankHelper.getMoney(crystal) >= costs && BankManager.getItemAmount(stack.itemID, stack.getItemDamage()) > 0){
			
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketBuyItem(stack.itemID, stack.getItemDamage(), amount, this.entity.xCoord, this.entity.yCoord, this.entity.zCoord)));
			
			BankHelper.setMoney(crystal, BankHelper.getMoney(crystal) - costs);
			ItemStack output = new ItemStack(stack.itemID, amount, stack.getItemDamage());
			player.playSound(Sounds.CHEST_CLOSE, 1.0F, 1.0F);
			InventoryHelper.addItemStackToInventory(player.inventory, output);
			player.inventory.setItemStack(null);
			return output;
		}
		return null;
	}
	
	public ItemStack sell(ItemStack stack, ItemStack crystal, EntityPlayer player){
		float money = BankManager.getItemPrice(stack.itemID, stack.getItemDamage()) * stack.stackSize;
		if(BankManager.getItemTradeable(stack.itemID, stack.getItemDamage()) && player.worldObj.isRemote){
			
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketSellItem(stack.itemID, stack.getItemDamage(), stack.stackSize, this.entity.xCoord, this.entity.yCoord, this.entity.zCoord)));
			player.playSound(Sounds.CHEST_CLOSE, 1.0F, 1.0F);
			BankHelper.setMoney(crystal, BankHelper.getMoney(crystal) + money);
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
        int i = list.size() / 9 - 5 + 1;
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
                
                if (i1 >= 0 && i1 < list.size())
                {
                    this.inv.setInventorySlotContents(l + k * 9, (ItemStack)list.get(i1));
                }
                else
                {
                    this.inv.setInventorySlotContents(l + k * 9, (ItemStack)null);
                }
            }
        }
    }

}
