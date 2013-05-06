package redmagic.containers;

import redmagic.configuration.Texture;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.recipes.worktable.WorkTableRegistry;
import redmagic.slots.SlotOutput;
import redmagic.slots.SlotWorkTable;
import redmagic.tileentities.TileEntityWorkTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWorkTable extends Container{

	public TileEntityWorkTable entity;
	public String[] slotTextures = new String[9];
	public EntityPlayer player;
	public int showSlot;
	
	
	public ContainerWorkTable(EntityPlayer player, TileEntityWorkTable entity){
		super();
		this.entity = entity;
		this.player = player;
		//Output
		this.addSlotToContainer(new SlotOutput((IInventory)entity, 0, 124, 35));
		
		this.addSlotToContainer(new SlotWorkTable(this.entity, 10, 124, 13));
		//Input
		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new SlotWorkTable(this.entity, 1 + i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }
		
		this.entity.showCrafting();
		this.showCrafting();
		this.bindPlayerInventory(player.inventory);
	}
	
	public void showCrafting() {
		Logger.log("show");
		this.clearMatrix();
		ItemStack[] input = new ItemStack[9];
		ItemStack[] review = new ItemStack[9];
		int count = 0;
		for(int i = this.entity.outputSlot + 1; i < this.entity.showSlot; i++){
			review[count] = this.entity.getStackInSlot(i);
			input[count++] = this.entity.getStackInSlot(i);
		}
		ItemStack[] containedItems = InventoryHelper.containedItemsInInventory(this.player.inventory, input);
		for(int i = 0; i < containedItems.length; i++){
			ItemStack stack = containedItems[i];
		    if(stack == null){
		    	if(review[i] != null){
		    		this.slotTextures[i] = Texture.GREEN;
		    	}
			}else if(stack != null && WorkTableRegistry.contains(stack)){
				this.slotTextures[i] = Texture.ORANGE;
			}else{
				this.slotTextures[i] = Texture.RED;
			}
				
		}
		
	}
	public void clearMatrix(){
		for(int i = 0; i < this.slotTextures.length; i++){
			this.slotTextures[i] = Texture.GRAY;
		}
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
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){
        Slot slot = (Slot)this.inventorySlots.get(par2);
        if(slot != null  && par2 <= 9){
        	if(!this.mergeItemStack(slot.getStack(), 10, this.inventorySlots.size(), false)){
        		return null;
        	}
        }
        
        if(slot != null){
        	if (slot.getStack().stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return slot != null ? slot.getStack() : null;
    }
	
	
}
