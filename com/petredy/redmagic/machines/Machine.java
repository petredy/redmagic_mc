package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;

public class Machine {

	public static Machine getMachine(int metadata) {
		switch(metadata){
		case BlockIndex.MACHINE_COLLECTOR_METADATA: return new MachineCollector();
		default: return new Machine();
		}
	}
	
	
	protected int side;
	protected int metadata;
	
	
	
	public void update(IMachineHandler machineHandler) {
		
	}
	
	public int getSide(){
		return side;
	}
	
	public int getMetadata(){
		return metadata;
	}
	
	public void setSide(int i){
		side = i;
	}
	
	public boolean canPlacedOnSide(int side){
		return true;
	}
	
	public void onPlacedByPlayer(IMachineHandler handler, int side, EntityPlayer player){
		this.side = side;
	}
	
	public void activate(EntityPlayer player, float offX, float offY, float offZ) {
		
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.side = tag.getInteger("side");
		this.metadata = tag.getInteger("metadata");
	}
	
	public static Machine loadFromNBT(NBTTagCompound tag){
		int metadata = tag.getInteger("metadata");
		Machine machine = Machine.getMachine(metadata);
		machine.readFromNBT(tag);
		return machine;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("side", side);
		tag.setInteger("metadata", metadata);
	}
	
	
	
}
