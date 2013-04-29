package redmagic.containers;

import redmagic.helpers.TamingHelper;
import redmagic.tileentities.TileEntityTaming;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTaming extends Container{
	
	TileEntityTaming entity;
	EntityPlayer player;
	public int id;
	
	public ContainerTaming(EntityPlayer player, TileEntityTaming tileEntity, int id){
		this.player = player;
		this.entity = tileEntity;
		this.id = id;
		
		// Soul
		this.addSlotToContainer(new Slot((IInventory) entity, 0, 41, 35));
		
		// Requests
		this.addSlotToContainer(new Slot((IInventory) entity, 1, 80, 17));
		this.addSlotToContainer(new Slot((IInventory) entity, 2, 80, 35));
		this.addSlotToContainer(new Slot((IInventory) entity, 3, 80, 53));
		
		// Input
		this.addSlotToContainer(new Slot((IInventory) entity, 4, 118, 35));
		
		this.bindPlayerInventory(player.inventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
	
	
	public ItemStack slotClick(int slot, int right, int shift, EntityPlayer player)
    {
		if(slot != TileEntityTaming.soulSlot && slot != TileEntityTaming.requestSlot1 && slot != TileEntityTaming.requestSlot2 && slot != TileEntityTaming.requestSlot3)return super.slotClick(slot, right, shift, player);
		return null;
    }
		
	protected void bindPlayerInventory(InventoryPlayer inventory){
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex)
    {
		return null;
    }
	
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
		super.onCraftGuiClosed(par1EntityPlayer);
		EntityLiving entity = (EntityLiving) par1EntityPlayer.worldObj.getEntityByID(id);
		TamingHelper.saveTileEntity(entity, this.entity);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
