package com.petredy.redmagic.structures.soulcatcher;

import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketSoulCatcherSync;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class StructureSoulCatcherBlock {

	public int x, y, z;
	
	
	public StructureSoulCatcherBlock(){}
	
	public StructureSoulCatcherBlock(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static boolean isSoulCatcherBlock(TileEntity entity){
		return entity instanceof TileEntitySoulCatcher && ((TileEntitySoulCatcher)entity).id == -1;
	}

	public void notify(World worldObj, StructureSoulCatcher structure) {
		TileEntitySoulCatcher entity = (TileEntitySoulCatcher) worldObj.getBlockTileEntity(x, y, z);
		if(entity != null){
			entity.setStructure(structure);
			NBTTagCompound tag = new NBTTagCompound(), structureTag = new NBTTagCompound();
			if(structure != null){
				structure.writeToNBT(structureTag);
				tag.setBoolean("delete", false);
			}else{
				tag.setBoolean("delete", true);
			}
			
			tag.setTag("structure", structureTag);
			tag.setInteger("x", x);
			tag.setInteger("y", y);
			tag.setInteger("z", z);
			PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketSoulCatcherSync(tag)));
		}
	}

	public static boolean isSoulCatcherStructure(TileEntity entity) {
		if(entity instanceof TileEntitySoulCatcher)LogUtils.log("look after " + ((TileEntitySoulCatcher)entity).id);
		return entity instanceof TileEntitySoulCatcher && ((TileEntitySoulCatcher)entity).id > -1;
	}

	public static StructureSoulCatcherBlock loadFromNBT(NBTTagCompound blockTag) {
		StructureSoulCatcherBlock block = new StructureSoulCatcherBlock();
		block.readFromNBT(blockTag);
		return block;
	}

	public void readFromNBT(NBTTagCompound blockTag) {
		this.x = blockTag.getInteger("x");
		this.y = blockTag.getInteger("y");
		this.z = blockTag.getInteger("z");
	}

	public void writeToNBT(NBTTagCompound blockTag) {
		blockTag.setInteger("x", x);
		blockTag.setInteger("y", y);
		blockTag.setInteger("z", z);
	}
	
}
