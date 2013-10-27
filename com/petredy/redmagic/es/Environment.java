package com.petredy.redmagic.es;

import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class Environment {

	public int width, breadth, height;
	public Storage[] storages;
	public boolean valide;
	public float energy;
	
	public Environment(int width, int breadth, int height) {
		this.width = width;
		this.breadth = breadth;
		this.height = height;
		this.storages = new Storage[width * breadth * height];
	}
	
	public Environment() {
		this.storages = new Storage[0];
	}

	public void scan(World world, int xStart, int yStart, int zStart, float max){
		int index = 0;
		LogUtils.log("scan on " + world.isRemote);
		for(int y = yStart; y < yStart + height; y++){
			for(int x = xStart; x < xStart + width; x++){
				for(int z = zStart; z < zStart + breadth; z++){
					ItemStack block = BlockUtils.getBlockStack(world, x, y, z);
					if(block != null && !block.isItemEqual(new ItemStack(Block.bedrock))){
						LogUtils.log(block);
						float value = RedvalueDictionary.getRedvalue(block);
						if(value > 0)energy += value;
						else energy += 1000;
						Storage storage = new Storage();
						storage.loadBlock(world, x, y, z);
						storages[index] = storage;
					}
					index += 1;
				}
			}
		}
		if(height > 0 && width > 0 && breadth > 0)valide = true;
	}

	public void place(World world, int xStart, int yStart, int zStart) {
		if(valide){
			int index = 0;
			for(int y = yStart; y < yStart + height; y++){
				for(int x = xStart; x < xStart + width; x++){
					for(int z = zStart; z < zStart + breadth; z++){
						Storage storage = storages[index++];
						LogUtils.log(storage);
						if(storage != null)storage.setBlock(world, x, y, z);
					}
				}
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.width = tag.getInteger("width");
		this.breadth = tag.getInteger("breadth");
		this.height = tag.getInteger("height");
		this.valide = tag.getBoolean("valide");
		this.energy = tag.getFloat("energy");
		this.storages = new Storage[width * breadth * height];
		
		NBTTagList list = tag.getTagList("blocks");
		if(list != null){
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound storageTag = (NBTTagCompound) list.tagAt(i);
				try{
					int index = storageTag.getInteger("redmagic.index");
					storages[index] = Storage.loadFromNBT(storageTag);
				}catch(Exception e){
					//LogUtils.log("Couldn't read block " + i);
				}
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("width", width);
		tag.setInteger("breadth", breadth);
		tag.setInteger("height", height);
		tag.setBoolean("valide", valide);
		tag.setFloat("energy", energy);

		NBTTagList list = new NBTTagList();
		for(int i = 0; i < storages.length; i++){
			NBTTagCompound storageTag = new NBTTagCompound();
			Storage storage = storages[i];
			if(storage != null){
				storageTag.setInteger("redmagic.index", i);
				storage.writeToNBT(storageTag);
				list.appendTag(storageTag);
			}
		}
		tag.setTag("blocks", list);
	}

}
