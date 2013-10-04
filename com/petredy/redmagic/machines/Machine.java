package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.utils.InventoryUtils;

public class Machine {

	public static Machine getMachine(int metadata) {
		switch(metadata){
		case Machines.COLLECTOR_METADATA: return new MachineCollector();
		case Machines.CONTACT_COOLING_METADATA: return new MachineContactCooling();
		case Machines.FURNACE_METADATA: return new MachineFurnace();
		case Machines.DEINTEGRATOR_METADATA: return new MachineDeintegrator();
		case Machines.CHARGER_METADATA: return new MachineCharger();
		case Machines.REFRIGERATOR_METADATA: return new MachineRefrigerator();
		case Machines.FREEZER_METADATA: return new MachineFreezer();
		default: return new Machine();
		}
	}
	
	
	protected int side;
	protected int metadata;
	protected int size;
	
	
	
	public void update(IMachineHandler handler) {
		
	}
	
	public int getSide(){
		return side;
	}
	
	public int getSize(){
		return size;
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
	
	public void removeByPlayer(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		this.remove(handler);
	}
	
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			ItemStack stack = new ItemStack(Items.machine, 1, getMetadata());
			InventoryUtils.dropItemStack(stack, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
	}
	
	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		
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
