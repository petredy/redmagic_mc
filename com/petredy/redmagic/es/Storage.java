package com.petredy.redmagic.es;

import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Storage {

	public ItemStack block;
	public boolean hasEntity;
	public NBTTagCompound data = new NBTTagCompound();
	
	public Storage(){
		
	}
	
	public void loadBlock(World world, int x, int y, int z){
		block = BlockUtils.getBlockStack(world, x, y, z);
		if(block != null){
			TileEntity entity = world.getBlockTileEntity(x, y, z);
			if(entity != null){
				hasEntity = true;
				entity.writeToNBT(data);
			}
		}
	}
	
	public void setBlock(World world, int x, int y, int z){
		int id = world.getBlockId(x, y, z);
		if(id != Block.bedrock.blockID){
			world.setBlock(x, y, z, block.itemID, block.getItemDamage(), 3);
			if(hasEntity){
				TileEntity entity = Block.blocksList[block.itemID].createTileEntity(world, block.getItemDamage());
				entity.readFromNBT(data);
				entity.xCoord = x;
				entity.yCoord = y;
				entity.zCoord = z;
				world.setBlockTileEntity(x, y, z, entity);
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.hasEntity = tag.getBoolean("hasEntity");
		this.block = ItemStack.loadItemStackFromNBT(tag);
		this.data = tag.getCompoundTag("data");
	}
	
	public static Storage loadFromNBT(NBTTagCompound tag){
		Storage storage = new Storage();
		storage.readFromNBT(tag);
		return storage;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setBoolean("hasEntity", hasEntity);
		if(block != null)block.writeToNBT(tag);
		if(data != null)tag.setTag("data", data);
	}
}
