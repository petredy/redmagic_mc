package redmagic.helpers;

import redmagic.core.Logger;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class InventoryHelper {
	
	public static IInventory getInventoryOnSide(World world, int x, int y, int z, int side){
		switch(side){
			case 0: return (IInventory) world.getBlockTileEntity(x, y - 1, z);
			case 1: return (IInventory) world.getBlockTileEntity(x, y + 1, z);
			case 2: return (IInventory) world.getBlockTileEntity(x, y, z - 1);
			case 3: return (IInventory) world.getBlockTileEntity(x, y, z + 1);
			case 4: return (IInventory) world.getBlockTileEntity(x - 1, y, z);
			case 5: return (IInventory) world.getBlockTileEntity(x + 1, y, z);
		}
		return null;
	}
	
	public static boolean containsInventoryItems(IInventory inventory, ItemStack[] items){
		ItemStack[] containedItems = containedItemsInInventory(inventory, items);
		for(int i = 0; i < containedItems.length; i++){
			if(containedItems[i] != null){
				return false;
			}
		}
		return true;
	}
	
	public static ItemStack[] containedItemsInInventory(IInventory inventory, ItemStack[] items){
		
		int[] used = new int[inventory.getSizeInventory()];
		ItemStack[] contained = new ItemStack[items.length];
		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack slot = inventory.getStackInSlot(i);
			if(slot != null){
				for(int j = 0; j < items.length; j++){
					ItemStack item = items[j];
					if(item != null && slot.isItemEqual(item) && used[i] < slot.stackSize){
						if(slot.stackSize >= item.stackSize){
							contained[j] = items[j];
							items[j] = null;
							used[i]++;
						}else{
							item.stackSize -= slot.stackSize;
							if(item.stackSize == 0)item = null;
							used[i]++;
						}
					}
				}
			}
		}
		return items;
	}

	public static ItemStack[] sortItems(ItemStack[] items){
		int length = 0;
		for(int i = 0; i < items.length; i++){
			ItemStack item = items[i];
			if(item != null){
				length++;
				for(int j = 0; j < items.length; j++){
					if(j != i){
						if(items[j] != null && items[j].isItemEqual(item)){
							item.stackSize += items[j].stackSize;
							items[j] = null;
						}
					}
				}
			}
		}
		
		ItemStack[] sortedItems = new ItemStack[length];
		int count = 0;
		for(int i = 0; i < items.length; i++){
			if(items[i] != null){
				sortedItems[count] = items[i];
				count++;
			}
		}
		return sortedItems;
		
	}
	
	public static void reduceItemsInInventory(IInventory inv, ItemStack[] items){
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack slot = inv.getStackInSlot(i);
			for(int j = 0; j < items.length; j++){
				if(slot != null && items[j] != null && items[j].isItemEqual(slot)){
					if(slot.stackSize >= items[j].stackSize){
						inv.decrStackSize(i, items[j].stackSize);
						items[j] = null;
					}else{
						items[j].stackSize -= slot.stackSize;
						if(items[j].stackSize <= 0)items[j] = null;
						inv.setInventorySlotContents(i, null);
					}
				}
			}
		}
	}
	
	public static IInventory itemStacksToInventory(ItemStack[] stacks){
		IInventory inv = new InventoryBasic("", true, stacks.length);
		for(int i = 0; i < stacks.length; i++){
			inv.setInventorySlotContents(i, stacks[i]);
		}
		return inv;
	}
	
	public static ItemStack addItemStackToInventory(IInventory inventory, ItemStack stackToMove){
		if(stackToMove == null)return stackToMove;
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack == null) {
				inventory.setInventorySlotContents(i, stackToMove);
				return null;
			}
			if (stackToMove.itemID == stack.itemID && (stackToMove.getItem().isDamageable() || stackToMove.getItemDamage() == stack.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, stackToMove)) {
				if (stackToMove.stackSize + stack.stackSize <= stack.getMaxStackSize()) {
					stack.stackSize += stackToMove.stackSize;
					return null;
				}
				int itemsToMove = stack.getMaxStackSize() - stack.stackSize;
				stack.stackSize += itemsToMove;
				stackToMove.stackSize -= itemsToMove;
				inventory.setInventorySlotContents(i, stack);
			}
		}
		return stackToMove;
	}
	
	public static int addItemStackToInventorySide(ISidedInventory inventory, int side, ItemStack par1ItemStack){
		int start = inventory.getStartInventorySide(ForgeDirection.getOrientation(side));
		int length = inventory.getSizeInventorySide(ForgeDirection.getOrientation(side));
		//return addItemStackToInventory(getSlots(inventory, start, length), par1ItemStack);
		return 0;
	}
	
	public static ItemStack[] getSlots(IInventory inventory){
		return getSlots(inventory, 0, inventory.getSizeInventory());
	}
	
	public static ItemStack[] getSlots(IInventory inventory, int start, int length){
		ItemStack[] stacks = new ItemStack[inventory.getSizeInventory()];
		for(int i = start; i < start + length; i++){
			stacks[i] = inventory.getStackInSlot(i);
		}
		return stacks;
	}
	
	public static int getFirstEmptyStack(ItemStack[] stacks)
    {
        for (int var1 = 0; var1 < stacks.length; ++var1)
        {
            if (stacks[var1] == null)
            {
                return var1;
            }
        }

        return -1;
    }
	
    
	private static int storeItemStack(ItemStack[] inv, ItemStack par1ItemStack)
    {
        for (int var2 = 0; var2 < inv.length; ++var2)
        {
            if (inv[var2] != null && inv[var2].itemID == par1ItemStack.itemID && inv[var2].isStackable() && inv[var2].stackSize < inv[var2].getMaxStackSize() && inv[var2].stackSize < 64 && (!inv[var2].getHasSubtypes() || inv[var2].getItemDamage() == par1ItemStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(inv[var2], par1ItemStack))
            {
                return var2;
            }
        }

        return -1;
    }
    
    public static boolean isFull(ItemStack[] inventory){
    	if(getFirstEmptyStack(inventory) == -1)return true;
    	return false;
    }
    
    public static boolean isFull(IInventory inv) {
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(inv.getStackInSlot(i) == null)return false;
		}
		return true;
	}
	
	public static boolean isEmpty(IInventory inv){
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(inv.getStackInSlot(i) != null)return false;
		}
		return true;
	}
    
    public static void dropInventory(IInventory inventory, World world, double x, double y, double z){
    	for(int i = 0; i < inventory.getSizeInventory(); i++){
    		ItemStack stack = inventory.getStackInSlot(i);
    		if(stack != null){
	    		EntityItem item = new EntityItem(world, x, y, z, stack.copy());
	    		if(!world.isRemote)world.spawnEntityInWorld(item);
	    		inventory.setInventorySlotContents(i, null);
    		}
    	}
    }

	public static int reduceItemStack(IInventory inv, ItemStack stack, int amount) {
		int used = 0;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack item = inv.getStackInSlot(i);
			if(item != null && stack.isItemEqual(item) && item.stackSize > 0){
				if(item.stackSize - amount >= 0){
					inv.decrStackSize(i, amount);
					if(item.stackSize <= 0)inv.setInventorySlotContents(i, null);
					return amount;
				}else{
					used += item.stackSize;
					inv.setInventorySlotContents(i, null);
				}
			}
			
		}
		return used;
	}
	
	
	public static int reduceIDInInventory(IInventory inv, int id, int amount) {
		int used = 0;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack item = inv.getStackInSlot(i);
			if(item != null && item.itemID == id && item.stackSize > 0){
				if(item.stackSize - amount >= 0){
					inv.decrStackSize(i, amount);
					if(item.stackSize <= 0)inv.setInventorySlotContents(i, null);
					return amount;
				}else{
					used += item.stackSize;
					inv.setInventorySlotContents(i, null);
				}
			}
			
		}
		return used;
	}
	
	public static ItemStack addToAdjacentInventory(World world, int x, int y, int z, ItemStack stack){
		TileEntity entityTo = world.getBlockTileEntity(x, y + 1, z);
		ItemStack rtn = null;
		if(entityTo instanceof IInventory){
			IInventory inv = (IInventory)entityTo;
			ItemStack drop = InventoryHelper.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityBo = world.getBlockTileEntity(x, y - 1, z);
		if(entityBo instanceof IInventory){
			IInventory inv = (IInventory)entityBo;
			ItemStack drop = InventoryHelper.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityBa = world.getBlockTileEntity(x, y, z - 1);
		if(entityBa instanceof IInventory){
			IInventory inv = (IInventory)entityBa;
			ItemStack drop = InventoryHelper.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityFr = world.getBlockTileEntity(x, y, z + 1);
		if(entityFr instanceof IInventory){
			IInventory inv = (IInventory)entityFr;
			ItemStack drop = InventoryHelper.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityLe = world.getBlockTileEntity(x - 1, y, z);
		if(entityLe instanceof IInventory){
			IInventory inv = (IInventory)entityLe;
			ItemStack drop = InventoryHelper.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityRi = world.getBlockTileEntity(x + 1, y, z);
		if(entityRi instanceof IInventory){
			IInventory inv = (IInventory)entityRi;
			ItemStack drop = InventoryHelper.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		return stack;
	}

	public static void moveInventory(IInventory source, IInventory target) {
		for(int i = 0; i < source.getSizeInventory(); i++){
			ItemStack slot = source.getStackInSlot(i);
			if(slot != null)source.setInventorySlotContents(i, InventoryHelper.addItemStackToInventory(target, slot));
		}
	}
	
	public static void writeToNBT(IInventory inventory, NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack stack = inventory.getStackInSlot(i);
			if(stack != null){
				NBTTagCompound tmpTag = new NBTTagCompound();
				tmpTag.setInteger("slot", i);
				stack.writeToNBT(tmpTag);
				list.appendTag(tmpTag);
			}
		}
		tag.setTag(inventory.getInvName(), list);
	}
	
	public static void readFromNBT(IInventory inventory, NBTTagCompound tag){
		NBTTagList list = tag.getTagList(inventory.getInvName());
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound tmpTag = (NBTTagCompound) list.tagAt(i);
			ItemStack stack = ItemStack.loadItemStackFromNBT(tmpTag);
			inventory.setInventorySlotContents(tmpTag.getInteger("slot"), stack);
		}
	}

	public static ItemStack popItemStack(IInventory inv) {
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null){
				inv.setInventorySlotContents(i, null);
				return stack.copy();
			}
		}
		return null;
	}

	public static ItemStack popBurnableItemStack(IInventory inv) {
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null && FurnaceRecipes.smelting().getSmeltingResult(stack) != null){
				inv.setInventorySlotContents(i, null);
				return stack;
			}
		}
		return null;
	}

	public static void dropItemStack(ItemStack drop, World worldObj, double xCoord, double yCoord, double zCoord) {
		EntityItem item = new EntityItem(worldObj, xCoord, yCoord, zCoord, drop);
		worldObj.spawnEntityInWorld(item);
		
	}

	public static IInventory getNextInventory(World world, int x, int y, int z, boolean top) {
		if(world.getBlockTileEntity(x + 1, y, z) instanceof IInventory)return (IInventory) world.getBlockTileEntity(x + 1, y, z);
		if(world.getBlockTileEntity(x - 1, y, z) instanceof IInventory)return (IInventory) world.getBlockTileEntity(x - 1, y, z);
		if(world.getBlockTileEntity(x, y, z + 1) instanceof IInventory)return (IInventory) world.getBlockTileEntity(x, y, z + 1);
		if(world.getBlockTileEntity(x, y, z - 1) instanceof IInventory)return (IInventory) world.getBlockTileEntity(x, y, z - 1);
		if(top && world.getBlockTileEntity(x, y + 1, z) instanceof IInventory)return (IInventory) world.getBlockTileEntity(x, y + 1, z);
		if(top && world.getBlockTileEntity(x, y - 1, z) instanceof IInventory)return (IInventory) world.getBlockTileEntity(x, y - 1, z);
		return null;


	}

	public static ItemStack splitStack(ItemStack stack, int amount) {
		if(stack != null){
			if(stack.stackSize >= amount){
				ItemStack rtn = stack.copy();
				rtn.stackSize = amount;
				return rtn;
			}else{
				return stack.copy();
			}
		}
		return null;
	}

	public static boolean containsBurnableItem(IInventory inv) {
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			ItemStack slot = inv.getStackInSlot(i);
			if(FurnaceRecipes.smelting().getSmeltingResult(slot) != null)return true;
		}
		return false;
	}
	
	public static ItemStack popBurnableItem(IInventory inv, int amount){
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			ItemStack slot = inv.getStackInSlot(i);
			if(FurnaceRecipes.smelting().getSmeltingResult(slot) != null)return splitStack(slot, amount);
		}
		return null;
	}
	
}
