package com.petredy.redmagic.container;

import com.petredy.redmagic.container.slot.SlotNoInteraction;
import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.knowledge.KnowledgeCrafting;
import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.tileentities.TileEntityCrafter;
import com.petredy.redmagic.utils.ArtifactUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrafter extends Container {

	public TileEntityCrafter entity;
	
	public ContainerCrafter(EntityPlayer player, TileEntityCrafter entity){
		this.entity = entity;
		this.addSlotToContainer(new Slot(entity, 0, 124, 62));
		this.addSlotToContainer(new Slot(entity, 1, 124, 35));
		
		int count = 2;
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(entity, count++, j * 18 + 8, i * 18 + 91));
			}
		}
		
		
		count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				this.addSlotToContainer(new SlotNoInteraction(entity.matrix, count++, i * 18 + 30, j * 18 + 17));
			}
		}
		
		
		this.bindPlayerInventory(player.inventory);
		this.onCraftMatrixChanged(entity);
	}
	
	private void setMatrixByArtifact(InventoryBasic matrix, ItemStack artifact) {
		if(artifact == null){
			for(int i = 1; i < 10; i++)
			{
				matrix.setInventorySlotContents(i, null);
			}
		}else{
			Knowledge knowledge = KnowledgeSystem.getKnowledge(ArtifactUtils.getKnowledge(artifact));
			if(knowledge instanceof KnowledgeCrafting){
				for(int i = 0; i < 9; i++){
					matrix.setInventorySlotContents(i + 1, ((KnowledgeCrafting)knowledge).recipe[i]);
				}
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	public void bindPlayerInventory(InventoryPlayer inv){
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inv, j + i * 9 + 9,  8 + j * 18, 134 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(inv, i, 8 + i * 18, 192));
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
