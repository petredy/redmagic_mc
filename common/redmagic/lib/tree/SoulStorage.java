package redmagic.lib.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.SoulHelper;
import redmagic.api.frame.ISoul;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SoulStorage {
	
	public List<SoulBlock> souls = new ArrayList<SoulBlock>();
	public int capacity;
	
	public SoulStorage(){}
	
	public void add(int x, int y, int z, ItemStack soul){
		if(soul != null && soul.getItem() instanceof ISoul){
			souls.add(new SoulBlock(x, y, z, soul));
		}
	}
	
	public void remove(int x, int y, int z){
		Iterator<SoulBlock> it = souls.iterator();
		while(it.hasNext()){
			SoulBlock block = it.next();
			if(block.x == x && block.y == y && block.z == z){
				souls.remove(block);
				break;
			}
		}
	}
	
	public int calculateCapacity(int wood){
		this.capacity = wood / LogicIndex.TREE_CAPACITY_CALCULATER;
		return this.capacity;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList(Reference.MOD_ID + "_souls");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound soul = (NBTTagCompound) list.tagAt(i);
			souls.add(SoulBlock.loadFromNBT(soul));
		}
		this.capacity = tag.getInteger(Reference.MOD_ID + "_capacity");
	}
	
	public static SoulStorage loadFromNBT(NBTTagCompound tag){
		SoulStorage storage = new SoulStorage();
		storage.readFromNBT(tag);
		return storage;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		
		Iterator<SoulBlock> it = souls.iterator();
		while(it.hasNext()){
			SoulBlock block = it.next();
			NBTTagCompound soulTag = new NBTTagCompound();
			block.writeToNBT(soulTag);
			list.appendTag(soulTag);
		}
		
		tag.setTag(Reference.MOD_ID + "_souls", list);
		tag.setInteger(Reference.MOD_ID + "_capacity", this.capacity);
	}

	public SoulBlock getBlockAt(int x, int y, int z) {
		Iterator<SoulBlock> it = souls.iterator();
		while(it.hasNext()){
			SoulBlock block = it.next();
			if(block.x == x && block.y == y && block.z == z)return block;
		}
		return null;
	}
	
	public boolean contains(int x, int y, int z){
		return getBlockAt(x, y, z) != null;
	}
	
	
}
