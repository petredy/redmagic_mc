package com.petredy.redmagic.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcher;
import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcherLayer;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

public class TileEntitySoulCatcher extends TileEntityInventory implements IPowerReceptor{

	public int id;
	
	public TileEntitySoulCatcher() {
		super(BlockIndex.SOUL_CATCHER_NAME, 9);
		id = -1;
	}

	public void buildOrFindStructure() {
		StructureSoulCatcherLayer layer = StructureSoulCatcherLayer.find(this);
		if(layer != null){
			StructureSoulCatcher structure = StructureSoulCatcher.find(this);
			if(structure != null){
				structure.addLayer(this.worldObj, layer);
			}else{
				StructureSoulCatcher.build(this.worldObj, layer);
			}
		}
	}


	public void setStructure(StructureSoulCatcher structure) {
		if(structure != null){
			this.id = structure.id;
		}
		else this.id = -1;
	}

	public void breakLayer() {
		StructureSoulCatcher structure = StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(worldObj, StructureSoulCatcher.TOKEN_PREFIX + id));
		structure.breakLayer(worldObj, yCoord);
		NBTTagCompound tag = new NBTTagCompound();
		structure.writeToNBT(tag);
		WorldSavedDataUtils.saveData(worldObj, StructureSoulCatcher.TOKEN_PREFIX + id, tag);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.id = tag.getInteger("redmagic.id");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("redmagic.id", id);
	}
	
	
	/**
	 * IInventory
	 */

	
	public ItemStack getStackInSlot(int par1){
		if(id >= 0)return StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(worldObj, StructureSoulCatcher.TOKEN_PREFIX + id)).inv.getStackInSlot(par1);
		return null;
	}
	
	public ItemStack decrStackSize(int par1, int par2){
		if(id >= 0){
			StructureSoulCatcher structure = StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(worldObj, StructureSoulCatcher.TOKEN_PREFIX + id));
			ItemStack stack = structure.inv.decrStackSize(par1, par2);
			NBTTagCompound tag = new NBTTagCompound();
			structure.writeToNBT(tag);
			WorldSavedDataUtils.saveData(worldObj, structure.TOKEN_PREFIX + id, tag);
			return stack;
		}
		return null;
    }
	
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack){
		if(id >= 0){
			StructureSoulCatcher structure = StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(worldObj, StructureSoulCatcher.TOKEN_PREFIX + id));
			structure.inv.setInventorySlotContents(par1, par2ItemStack);
			NBTTagCompound tag = new NBTTagCompound();
			structure.writeToNBT(tag);
			WorldSavedDataUtils.saveData(worldObj, structure.TOKEN_PREFIX + id, tag);
		}
		
	}
	
	
	/**
	 * IPowerReceptor
	 */
	
	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		if(id >= 0){
			StructureSoulCatcher structure = StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(worldObj, StructureSoulCatcher.TOKEN_PREFIX + id));
			return structure.getNextPowerReceiver(worldObj);
		}
		return null;
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		LogUtils.log("work");
		workProvider.update();
	}

	@Override
	public World getWorld() {
		return worldObj;
	}
	
	
	
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
		this.readFromNBT(pkt.data);
    }
	
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
}
