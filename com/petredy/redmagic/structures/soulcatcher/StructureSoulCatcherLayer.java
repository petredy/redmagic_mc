package com.petredy.redmagic.structures.soulcatcher;

import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcherStorage;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class StructureSoulCatcherLayer {

	public StructureSoulCatcherBlock[] blocks = new StructureSoulCatcherBlock[4];
	
	public StructureSoulCatcherLayer(){}
	public int y;
	
	public static StructureSoulCatcherLayer find(TileEntitySoulCatcher entity){
		if(StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord + 1, entity.yCoord, entity.zCoord))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord, entity.yCoord, entity.zCoord - 1))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord + 1, entity.yCoord, entity.zCoord - 1))){
			StructureSoulCatcherLayer layer = new StructureSoulCatcherLayer();
			layer.y = entity.yCoord;
			layer.blocks[0] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord);
			layer.blocks[1] = new StructureSoulCatcherBlock(entity.xCoord + 1, entity.yCoord, entity.zCoord);
			layer.blocks[2] = new StructureSoulCatcherBlock(entity.xCoord + 1, entity.yCoord, entity.zCoord - 1);
			layer.blocks[3] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord - 1);
			return layer;
		}
		
		if(StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord + 1, entity.yCoord, entity.zCoord))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord, entity.yCoord, entity.zCoord + 1))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord + 1, entity.yCoord, entity.zCoord + 1))){
			StructureSoulCatcherLayer layer = new StructureSoulCatcherLayer();
			layer.y = entity.yCoord;
			layer.blocks[0] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord);
			layer.blocks[1] = new StructureSoulCatcherBlock(entity.xCoord + 1, entity.yCoord, entity.zCoord);
			layer.blocks[2] = new StructureSoulCatcherBlock(entity.xCoord + 1, entity.yCoord, entity.zCoord + 1);
			layer.blocks[3] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord + 1);
			return layer;
		}
		
		if(StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord - 1, entity.yCoord, entity.zCoord))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord, entity.yCoord, entity.zCoord - 1))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord - 1, entity.yCoord, entity.zCoord - 1))){
			StructureSoulCatcherLayer layer = new StructureSoulCatcherLayer();
			layer.y = entity.yCoord;
			layer.blocks[0] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord);
			layer.blocks[1] = new StructureSoulCatcherBlock(entity.xCoord - 1, entity.yCoord, entity.zCoord);
			layer.blocks[2] = new StructureSoulCatcherBlock(entity.xCoord - 1, entity.yCoord, entity.zCoord - 1);
			layer.blocks[3] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord - 1);
			return layer;
		}
		
		if(StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord - 1, entity.yCoord, entity.zCoord))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord, entity.yCoord, entity.zCoord + 1))
		&& StructureSoulCatcherBlock.isSoulCatcherBlock(entity.worldObj.getBlockTileEntity(entity.xCoord - 1, entity.yCoord, entity.zCoord + 1))){
			StructureSoulCatcherLayer layer = new StructureSoulCatcherLayer();
			layer.y = entity.yCoord;
			layer.blocks[0] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord);
			layer.blocks[1] = new StructureSoulCatcherBlock(entity.xCoord - 1, entity.yCoord, entity.zCoord);
			layer.blocks[2] = new StructureSoulCatcherBlock(entity.xCoord - 1, entity.yCoord, entity.zCoord + 1);
			layer.blocks[3] = new StructureSoulCatcherBlock(entity.xCoord, entity.yCoord, entity.zCoord + 1);
			return layer;
		}
		
		return null;
	}


	public void notifyBlocks(World worldObj, StructureSoulCatcher structure) {
		for(StructureSoulCatcherBlock block: blocks){
			block.notify(worldObj, structure);
		}
		
	}

	public void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList("blocks");
		for(int i = 0; i < 4; i++){
			NBTTagCompound blockTag = (NBTTagCompound) list.tagAt(i);
			blocks[i] = StructureSoulCatcherBlock.loadFromNBT(blockTag);
		}
		this.y = tag.getInteger("y");
	}

	public static StructureSoulCatcherLayer loadFromNBT(NBTTagCompound layerTag) {
		StructureSoulCatcherLayer layer = new StructureSoulCatcherLayer();
		layer.readFromNBT(layerTag);
		return layer;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(StructureSoulCatcherBlock block: blocks){
			NBTTagCompound blockTag = new NBTTagCompound();
			block.writeToNBT(blockTag);
			list.appendTag(blockTag);
		}
		tag.setTag("blocks", list);
		tag.setInteger("y", y);
	}


	public PowerReceiver getNextPowerReceiver(World world) {
		for(StructureSoulCatcherBlock block: blocks){
			if(world.getBlockTileEntity(block.x, block.y, block.z) instanceof TileEntitySoulCatcherStorage){
				return ((TileEntitySoulCatcherStorage)world.getBlockTileEntity(block.x, block.y, block.z)).getPowerReceiver(ForgeDirection.UNKNOWN);
			}
		}
		return null;
	}
	
}
