package redmagic.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerOneSlot extends Container{

	public IInventory inv;
	
	public ContainerOneSlot(EntityPlayer player, IInventory inv){
		super();
		this.inv = inv;
		
		this.addSlotToContainer(new Slot(this.inv, 0, 80, 32));
		
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
		return this.inv.isUseableByPlayer(entityplayer);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index)
    {
		Slot slot = (Slot) this.inventorySlots.get(index);
		if(slot != null){
			ItemStack stack = slot.getStack();
			if(stack != null){
				if(index == 0){
					if(!this.mergeItemStack(stack, 1, this.inventorySlots.size(), false)){
						return null;
					}
				}else{
					if(!this.mergeItemStack(stack, 0, 1, false)){
						return null;
					}
					if(stack.stackSize == 0){
						slot.putStack(null);
						stack = null;
					}
				}
			}
		}
		return null;
    }

}
