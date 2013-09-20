package com.petredy.redmagic.soul;

import java.util.HashMap;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SoulInventory implements IInventory{

	public HashMap<Integer, SoulStack>stacks = new HashMap<Integer, SoulStack>();
	
	@Override
	public int getSizeInventory() {
		return Integer.MAX_VALUE;
	}

	public SoulStack getSoulStack(int i){
		return (stacks.size() > 0 && stacks.size() >= i - 1) ? stacks.get(i) : null;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		SoulStack stack = getSoulStack(i);
		if(stack == null)return null;
		ItemStack itemstack = new ItemStack(stack.stack.itemID, stack.amount, stack.stack.getItemDamage());
		return itemstack;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = getStackInSlot(i);
		stack.stackSize--;
		if(stack.stackSize <= 0){
			setInventorySlotContents(i, null);
			return stack;
		}
		setInventorySlotContents(i, stack);
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		SoulStack stack = getSoulStack(i);
		if(stack != null){
			
			if(itemstack == null){
				stacks.remove(stack);
			}else{
				SoulStack soulstack = new SoulStack(itemstack, itemstack.stackSize);
				stacks.put(i, soulstack);
			}
		}else{
			SoulStack soulstack = new SoulStack(itemstack, itemstack.stackSize);
			stacks.put(i, soulstack);
		}
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "soul.inventory";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void onInventoryChanged() {
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		int count = 0;
		for(SoulStack stack: stacks.values()){
			NBTTagCompound soulTag = new NBTTagCompound();
			stack.writeToNBT(soulTag);
			soulTag.setInteger("index", count++);
			list.appendTag(soulTag);
		}
		tag.setTag("redmagic.stacks", list);
	}
	
	public static SoulInventory loadFromNBT(NBTTagCompound tag){
		SoulInventory inv = new SoulInventory();
		inv.readFromNBT(tag);
		return inv;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList("redmagic.stacks");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound soulTag = (NBTTagCompound) list.tagAt(i);
			stacks.put(soulTag.getInteger("index"), SoulStack.loadFromNBT(soulTag));
		}
	}

}
