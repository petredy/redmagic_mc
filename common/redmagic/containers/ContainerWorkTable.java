package redmagic.containers;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import redmagic.configuration.Texture;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.network.PacketHandler;
import redmagic.network.PacketWorkTable;
import redmagic.recipes.worktable.WorkTableRegistry;
import redmagic.slots.SlotOutput;
import redmagic.slots.SlotNoInteraction;
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
		this.entity.containers.add(this);
		//Output
		this.addSlotToContainer(new SlotOutput((IInventory)entity, 0, 124, 35));
		
		this.addSlotToContainer(new SlotNoInteraction(this.entity, 10, 124, 13));
		//Input
		for (int l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new SlotNoInteraction(this.entity, 1 + i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }
		
		this.entity.showCrafting();
		this.showCrafting();
		this.bindPlayerInventory(player.inventory);
	}
	
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
		this.entity.containers.remove(this);
		super.onCraftGuiClosed(par1EntityPlayer);
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
	
	public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
		if(par1 > 0 && par1 < 10){
			Slot slot = (Slot)this.inventorySlots.get(par1);
			if(slot != null && slot.getStack() != null && WorkTableRegistry.contains(slot.getStack())){
				int index = WorkTableRegistry.getCraftingIndex(slot.getStack());
				this.entity.craftingIndex = index;
				PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketWorkTable(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord, this.entity.craftingIndex)), (Player) par4EntityPlayer);
				this.entity.showCrafting();
				this.showCrafting();
				return null;
			}
		}
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
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
        if(slot != null  && slot.getStack() != null && par2 == 0){
        	if(!this.mergeItemStack(slot.getStack(), 11, this.inventorySlots.size(), false)){
        		return null;
        	}
        }else{
        	return null;
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
