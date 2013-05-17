package redmagic.containers;

import redmagic.core.Logger;
import redmagic.helpers.SoulHelper;
import redmagic.helpers.TreeHelper;
import redmagic.lib.souls.container.SoulContainer;
import redmagic.tileentities.tree.TileEntityTreeWood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTree extends Container{

	public TileEntityTreeWood entity;
	
	public ContainerTree(EntityPlayer player, TileEntityTreeWood entity){
		super();
		this.entity = entity;
		
		if(this.entity.hasSoul()){
			SoulContainer container = TreeHelper.loadStructure(this.entity.worldObj, this.entity.structureID).storage.getBlockAt(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord).soul.getContainer();
			if(container != null)container.init(this, entity);
		}
		
		this.bindPlayerInventory(player.inventory);
	}
	
	public void addSlot(Slot slot){
		this.addSlotToContainer(slot);
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
		return this.entity.isUseableByPlayer(entityplayer);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index)
    {
		Slot slot = (Slot) this.inventorySlots.get(index);
		if(slot != null){
			ItemStack stack = slot.getStack();
			if(stack != null){
				if(index == 0){
					if(!this.mergeItemStack(stack, this.entity.getSizeInventory(), this.inventorySlots.size(), false)){
						return null;
					}
				}else{
					if(!this.mergeItemStack(stack, 0, this.entity.getSizeInventory(), false)){
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
	
	public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
		if(this.entity.hasSoul()){
			SoulContainer container = TreeHelper.loadStructure(this.entity.worldObj, this.entity.structureID).storage.getBlockAt(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord).soul.getContainer();
			if(container != null && par1 >= 0 && par1 < this.inventorySlots.size()){
				Slot slot = (Slot) this.inventorySlots.get(par1);
				if(container.onClick(par1, par2, par3, par4EntityPlayer, slot != null ? slot.getStack() : null)){
					return super.slotClick(par1, par2, par3, par4EntityPlayer);
				}else{
					return null;
				}
			}
		}
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

}
