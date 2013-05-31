package redmagic.lib.souls.inventory;

import redmagic.core.Logger;
import net.minecraft.item.ItemStack;

public abstract class SoulInventory {

	public ItemStack[] inv;
	
	public int getSizeInventory(){
		return inv.length;
	}
	
	public ItemStack getStackInSlot(int i){
		return inv[i];
	}

	public ItemStack decrStackSize(int slot, int amount){
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

	public ItemStack getStackInSlotOnClosing(int i){
		return getStackInSlot(i);
	}

	public void setInventorySlotContents(int i, ItemStack itemstack){
		inv[i] = itemstack;
	}

	public abstract String getInvName();

	public abstract boolean isInvNameLocalized();

	public int getInventoryStackLimit() {
		return 64;
	}

	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	public abstract boolean isStackValidForSlot(int i, ItemStack itemstack);

	public abstract int[] getAccessibleSlotsFromSide(int var1);

	public abstract boolean canInsertItem(int i, ItemStack itemstack, int j);

	public abstract boolean canExtractItem(int i, ItemStack itemstack, int j);
	
}
