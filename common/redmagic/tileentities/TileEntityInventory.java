package redmagic.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInventory extends TileEntity implements IInventory{
	public ItemStack[] inv;
	public String name;
	
	public TileEntityInventory(int size, String name){
		inv = new ItemStack[size];
		this.name = name;
	}
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inv[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null){
			if(stack.stackSize <= amount){
				this.setInventorySlotContents(slot, null);
			}else{
				stack = stack.splitStack(amount);
				if(stack.stackSize <= 0){
					this.setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		ItemStack stack = getStackInSlot(var1);
		if(stack != null){
			this.setInventorySlotContents(var1, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		inv[var1] = var2;
		if(var2 != null && var2.stackSize > this.getInventoryStackLimit()){
			var2.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return this.name;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for(int i = 0; i < tagList.tagCount(); i++){
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inv.length){
				this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tag));
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < inv.length; i++){
			ItemStack stack = this.getStackInSlot(i);
			if(stack != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
}
