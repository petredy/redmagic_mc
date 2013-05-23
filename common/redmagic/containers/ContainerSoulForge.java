package redmagic.containers;

import redmagic.slots.SlotOutput;
import redmagic.tileentities.TileEntitySoulForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerSoulForge extends Container{

	public TileEntitySoulForge entity;
	
	public ContainerSoulForge(EntityPlayer player, TileEntitySoulForge entity){
		super();
		this.entity = entity;
		
		this.addSlotToContainer(new SlotOutput(entity, 0, 79, 20));
		
		this.addSlotToContainer(new Slot(entity, 1, 43, 2));
		this.addSlotToContainer(new Slot(entity, 2, 116, 2));
		this.addSlotToContainer(new Slot(entity, 3, 43, 38));
		this.addSlotToContainer(new Slot(entity, 4, 116, 38));
		
		this.bindPlayerInventory(player.inventory);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventory){
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 71 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 129));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.entity.isUseableByPlayer(entityplayer);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index)
    {
		Slot slot = (Slot) this.inventorySlots.get(index);
		if(slot != null){
			ItemStack stack = slot.getStack();
			if(stack != null){
				if(index > 0 && index < 5){
					if(!this.mergeItemStack(stack, 5, this.inventorySlots.size(), false)){
						return null;
					}
				}else{
					if(!this.mergeItemStack(stack, 1, 5, false)){
						return null;
					}
				}
				if(stack.stackSize == 0){
					slot.putStack(null);
					stack = null;
				}
			}
		}
		return null;
    }

}
