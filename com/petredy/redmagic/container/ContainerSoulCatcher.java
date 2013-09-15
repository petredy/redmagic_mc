package com.petredy.redmagic.container;

import com.petredy.redmagic.container.slot.SlotNoInteraction;
import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcher;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;
import com.petredy.redmagic.tileentities.TileEntityTradingChest;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSoulCatcher extends Container {

	public InventoryBasic inv;
	public TileEntitySoulCatcher entity;
	
	public ContainerSoulCatcher(EntityPlayer player, TileEntitySoulCatcher tileEntity){
		super();
		this.entity = tileEntity;

		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(tileEntity, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
		
		
		this.bindPlayerInventory(player.inventory);
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
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return entity.isUseableByPlayer(entityplayer);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
		return null;
    }

}
