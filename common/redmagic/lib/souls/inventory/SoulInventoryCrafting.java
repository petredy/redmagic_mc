package redmagic.lib.souls.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class SoulInventoryCrafting extends SoulInventory{
	
	public SoulInventoryCrafting(){
		this.inv = new ItemStack[10];
	}
	
	@Override
	public String getInvName() {
		return "Crafting";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		ForgeDirection direction = ForgeDirection.getOrientation(side);
		if(ForgeDirection.UP == direction || ForgeDirection.NORTH == direction || ForgeDirection.EAST == direction || ForgeDirection.SOUTH == direction || ForgeDirection.WEST == direction){
			return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		}else{
			return new int[]{0};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		ForgeDirection direction = ForgeDirection.getOrientation(side);
		if(ForgeDirection.UP == direction || ForgeDirection.NORTH == direction || ForgeDirection.EAST == direction || ForgeDirection.SOUTH == direction || ForgeDirection.WEST == direction){
			if(getStackInSlot(slot) == null || getStackInSlot(slot).isItemEqual(itemstack) && getStackInSlot(slot).stackSize + itemstack.stackSize <= itemstack.getMaxStackSize()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		if(ForgeDirection.DOWN == ForgeDirection.getOrientation(side) && slot == 0 && getStackInSlot(slot) != null && getStackInSlot(slot).isItemEqual(itemstack)){
			return true;
		}
		return false;
	}

}
