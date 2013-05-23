package redmagic.containers;

import redmagic.lib.bag.BagHelper;
import redmagic.slots.SlotBag;
import redmagic.slots.SlotOutput;
import redmagic.tileentities.TileEntityBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerBag extends Container{

	public TileEntityBag entity;
	public int id;
	
	public ContainerBag(EntityPlayer player, TileEntityBag entity, int id){
		super();
		this.entity = entity;
		this.id = id;
		
		int count = 0;
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new SlotBag(entity, count++, j * 18 + 8, i * 18 - 10, id));
			}
		}
		
		this.bindPlayerInventory(player.inventory);
	}
	
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer){
		BagHelper.storeData(this.entity, id);
		super.onCraftGuiClosed(par1EntityPlayer);
    }
	
	protected void bindPlayerInventory(InventoryPlayer inventory){
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 112 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 170));
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
					if(!this.mergeItemStack(stack, this.entity.getSizeInventory(), this.inventorySlots.size(), false)){
						return null;
					}
				}else{
					if(!this.mergeItemStack(stack, 0, this.entity.getSizeInventory(), false)){
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
