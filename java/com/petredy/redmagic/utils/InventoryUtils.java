package com.petredy.redmagic.utils;

import java.util.Collection;

import com.petredy.redmagic.forge.helper.NBTTagHelper;
import com.petredy.redmagic.forge.helper.WorldHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class InventoryUtils {
	
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
	
	public static ItemStack[] reduceItemsInInventory(IInventory inv, ItemStack[] items){
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
		return items;
	}
	
	public static IInventory itemStacksToInventory(ItemStack[] stacks){
		return itemStacksToInventory(stacks, "redmagic.inventorybasic");
	}
	
	public static IInventory itemStacksToInventory(ItemStack[] stacks, String name){
		IInventory inv = new InventoryBasic(name, true, stacks.length);
		for(int i = 0; i < stacks.length; i++){
			inv.setInventorySlotContents(i, stacks[i]);
		}
		return inv;
	}
	
	public static ItemStack addItemStackToInventory(IInventory inventory, ItemStack stackToMove){
		if(stackToMove == null)return stackToMove;
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			
			if (stack == null && inventory.isItemValidForSlot(i, stackToMove)) {
				inventory.setInventorySlotContents(i, stackToMove);
				return null;
			}
			if (stackToMove.isItemEqual(stack) && (stackToMove.getItem().isDamageable() || stackToMove.getItemDamage() == stack.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, stackToMove)) {
				if (stackToMove.stackSize + stack.stackSize <= stack.getMaxStackSize()) {
					stack.stackSize += stackToMove.stackSize;
					inventory.setInventorySlotContents(i, stack);
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
	
	public static ItemStack[] getSlots(IInventory inventory){
		if(inventory == null)return new ItemStack[0];
		return getSlots(inventory, 0, inventory.getSizeInventory());
	}
	
	public static ItemStack[] getSlots(IInventory inventory, int start, int length){
		if(inventory == null)return new ItemStack[0];
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
            if (inv[var2] != null && inv[var2].isItemEqual(par1ItemStack) && inv[var2].isStackable() && inv[var2].stackSize < inv[var2].getMaxStackSize() && inv[var2].stackSize < 64 && (!inv[var2].getHasSubtypes() || inv[var2].getItemDamage() == par1ItemStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(inv[var2], par1ItemStack))
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
					amount -= item.stackSize;
					inv.setInventorySlotContents(i, null);
				}
			}
			
		}
		return used;
	}
	
	public static ItemStack addToAdjacentInventory(World world, int x, int y, int z, ItemStack stack){
		TileEntity entityTo = WorldHelper.getBlockTileEntity(world, x, y + 1, z);
		ItemStack rtn = null;
		if(entityTo instanceof IInventory){
			IInventory inv = (IInventory)entityTo;
			ItemStack drop = InventoryUtils.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityBo = WorldHelper.getBlockTileEntity(world, x, y - 1, z);
		if(entityBo instanceof IInventory){
			IInventory inv = (IInventory)entityBo;
			ItemStack drop = InventoryUtils.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityBa = WorldHelper.getBlockTileEntity(world, x, y, z - 1);
		if(entityBa instanceof IInventory){
			IInventory inv = (IInventory)entityBa;
			ItemStack drop = InventoryUtils.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityFr = WorldHelper.getBlockTileEntity(world, x, y, z + 1);
		if(entityFr instanceof IInventory){
			IInventory inv = (IInventory)entityFr;
			ItemStack drop = InventoryUtils.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityLe = WorldHelper.getBlockTileEntity(world, x - 1, y, z);
		if(entityLe instanceof IInventory){
			IInventory inv = (IInventory)entityLe;
			ItemStack drop = InventoryUtils.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		TileEntity entityRi = WorldHelper.getBlockTileEntity(world, x + 1, y, z);
		if(entityRi instanceof IInventory){
			IInventory inv = (IInventory)entityRi;
			ItemStack drop = InventoryUtils.addItemStackToInventory(inv, stack);
			if(drop == null)return null;
		}
		return stack;
	}

	public static void moveInventory(IInventory source, IInventory target) {
		for(int i = 0; i < source.getSizeInventory(); i++){
			ItemStack slot = source.getStackInSlot(i);
			if(slot != null)source.setInventorySlotContents(i, InventoryUtils.addItemStackToInventory(target, slot));
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
		tag.setTag(inventory.func_145825_b(), list);
	}
	
	public static void readFromNBT(IInventory inventory, NBTTagCompound tag){
		NBTTagList list = (NBTTagList) tag.getTag(inventory.func_145825_b());
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound tmpTag = (NBTTagCompound) NBTTagHelper.getTagAt(list, i);
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
			if(stack != null && FurnaceRecipes.smelting().func_151395_a(stack) != null){
				inv.setInventorySlotContents(i, null);
				return stack;
			}
		}
		return null;
	}

	public static void dropItemStack(ItemStack drop, World worldObj, double xCoord, double yCoord, double zCoord) {
		EntityItem item = new EntityItem(worldObj, xCoord, yCoord, zCoord, drop);
		if(!worldObj.isRemote)worldObj.spawnEntityInWorld(item);
	}

	public static IInventory getNextInventory(World world, int x, int y, int z, boolean top) {
		if(WorldHelper.getBlockTileEntity(world, x + 1, y, z) instanceof IInventory)return (IInventory) WorldHelper.getBlockTileEntity(world, x + 1, y, z);
		if(WorldHelper.getBlockTileEntity(world, x - 1, y, z) instanceof IInventory)return (IInventory) WorldHelper.getBlockTileEntity(world, x - 1, y, z);
		if(WorldHelper.getBlockTileEntity(world, x, y, z + 1) instanceof IInventory)return (IInventory) WorldHelper.getBlockTileEntity(world, x, y, z + 1);
		if(WorldHelper.getBlockTileEntity(world, x, y, z - 1) instanceof IInventory)return (IInventory) WorldHelper.getBlockTileEntity(world, x, y, z - 1);
		if(top && WorldHelper.getBlockTileEntity(world, x, y + 1, z) instanceof IInventory)return (IInventory) WorldHelper.getBlockTileEntity(world, x, y + 1, z);
		if(top && WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory)return (IInventory) WorldHelper.getBlockTileEntity(world, x, y - 1, z);
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
			if(FurnaceRecipes.smelting().func_151395_a(slot) != null)return true;
		}
		return false;
	}
	
	public static ItemStack popBurnableItem(IInventory inv, int amount){
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			ItemStack slot = inv.getStackInSlot(i);
			if(FurnaceRecipes.smelting().func_151395_a(slot) != null)return splitStack(slot, amount);
		}
		return null;
	}
	
	public static ItemStack popItemMatches(IInventory inv, int amount, Collection<ItemStack> matches){
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack slot = inv.getStackInSlot(i);
			if(contains(matches, slot)){
				inv.decrStackSize(i, amount);
				return splitStack(slot, amount);
			}
		}
		return null;
	}
	
	public static boolean contains(Collection<ItemStack> matches, ItemStack slot) {
		for(ItemStack match: matches){
			if(match == null && slot == null)return true;
			if(match != null && slot != null && match.isItemEqual(slot))return true;
		}
		return false;
	}

	public static IInventory[] getAdjecentInventories(World world, int x, int y, int z){
		IInventory[] inventories = new IInventory[6];
		inventories[0] = WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory ? (IInventory) WorldHelper.getBlockTileEntity(world, x, y - 1, z) : null;
		inventories[1] = WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory ? (IInventory) WorldHelper.getBlockTileEntity(world, x, y + 1, z) : null;
		inventories[2] = WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory ? (IInventory) WorldHelper.getBlockTileEntity(world, x, y, z - 1) : null;
		inventories[3] = WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory ? (IInventory) WorldHelper.getBlockTileEntity(world, x, y, z + 1) : null;
		inventories[4] = WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory ? (IInventory) WorldHelper.getBlockTileEntity(world, x - 1, y, z) : null;
		inventories[5] = WorldHelper.getBlockTileEntity(world, x, y - 1, z) instanceof IInventory ? (IInventory) WorldHelper.getBlockTileEntity(world, x + 1, y, z) : null;
		return inventories;
	}
	
	public static ItemStack[] inventoryToArray(IInventory[] invs){
		
		int size = InventoryUtils.getSizeOfInventories(invs);
		ItemStack[] items = new ItemStack[size];
		int count = 0;
		for(int i = 0; i < invs.length; i++){
			if(invs[i] != null){
				ItemStack[] slots = InventoryUtils.getSlots(invs[i]);
				for(int j = 0; j < slots.length; j++){
					if(slots[j] != null){
						items[count++] = slots[j];
					}
				}
			}
		}
		return items;
	}

	public static boolean containInventoriesItems(IInventory[] invs, ItemStack[] items){
		ItemStack[] restItems = getItemStackReducedByContainedItemsInInventories(invs, items);
		for(int i = 0; i < restItems.length; i++){
			if(restItems[i] != null)return false;
		}
		return true;
	}
	
	public static ItemStack[] getItemStackReducedByContainedItemsInInventories(IInventory[] invs, ItemStack[] items) {
		ItemStack[] containedItems = new ItemStack[items.length];
		int invcount = 0;
		for(IInventory inv: invs){
			if(inv != null){
				if(invcount == 1){
					containedItems = InventoryUtils.containedItemsInInventory(inv, items);
				}else{
					containedItems = InventoryUtils.containedItemsInInventory(inv, containedItems);
				}
			}
			invcount++;
		}
		return containedItems;
	}
	
	public static boolean reduceItemsFromInventories(IInventory[] invs, ItemStack[] items){
		for(IInventory inv: invs){
			items = reduceItemsInInventory(inv, items);
		}
		for(int i = 0; i < items.length; i++){
			if(items[i] != null)return false;
		}
		return true;
	}

	public static int getSizeOfInventories(IInventory[] inventories) {
		int count = 0;
		for(IInventory inv: inventories){
			if(inv != null){
				count += inv.getSizeInventory();
			}
		}
		return count;
	}

	public static ItemStack getStackInSlotFromInventories(int slot, IInventory[] inventories) {
		int count = 0;
		for(IInventory inv: inventories){
			if(inv != null){
				if(slot < count + inv.getSizeInventory()){
					LogUtils.log(inv.getStackInSlot(slot - count));
					return inv.getStackInSlot(slot - count);
				}
				count += inv.getSizeInventory();
			}
		}
		return null;
	}

	public static void setStackInSlotFromInventories(int slot, ItemStack stack, IInventory[] inventories) {
		int count = 0;
		for(IInventory inv: inventories){
			if(inv != null){
				if(slot < count + inv.getSizeInventory()){
					inv.setInventorySlotContents(slot - count, stack);
					break;
				}
				count += inv.getSizeInventory();
			}
		}
	}
	
	
	
	
	
}
