package com.petredy.redmagic.tileentities;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.machinery.MachineHandler;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketMachineSync;
import com.petredy.redmagic.network.PacketUpdateMachineOnSide;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMachine extends TileEntity implements IMachineHandler, IEnergyHandler{
	
	public Machine[] machines = new Machine[6];
	public RedEnergy energy = new RedEnergy();
	
	public float heat;
	
	public int updateTick = 0, neededTicksForUpdate = 1000;
	
	public void updateEntity(){
		for(Machine machine: machines){
			if(machine != null){
				machine.update((IMachineHandler)this);
			}
		}
	}

	public Machine getMachine(int metadata){
		for(Machine machine: machines){
			if(machine != null && machine.getMetadata() == metadata)return machine;
		}
		return null;
	}
	
	public boolean containsMachine(Machine machine){
		return getMachine(machine.getMetadata()) != null;
	}

	@Override
	public Machine getMachineOnSide(int i) {
		return machines[i];
	}
	
	@Override
	public int getUsedSize(){
		int size = 0;
		for(Machine machine: machines){
			if(machine != null)size += machine.getSize();
		}
		return size;
	}

	@Override
	public IEnergyHandler getEnergyHandler() {
		return (IEnergyHandler)this;
	}
	
	@Override
	public float getHeat() {
		return heat;
	}

	@Override
	public void setHeat(float heat) {
		this.heat = heat;
	}
	
	@Override
	public World getWorld() {
		return worldObj;
	}
	
	@Override
	public int getXCoord() {
		return xCoord;
	}

	@Override
	public int getYCoord() {
		return yCoord;
	}

	@Override
	public int getZCoord() {
		return zCoord;
	}
	
	@Override
	public RedEnergy collect(World world, RedEnergy energy) {
		RedEnergy collected = EnergyMap.consumeEnergy(energy);
		return store(collected);
	}

	@Override
	public RedEnergy release(World world, RedEnergy energy) {
		RedEnergy released = EnergyMap.releaseEnergy(energy);
		return use(released);
	}
	
	@Override
	public RedEnergy use(RedEnergy energy) {
		RedEnergy used = RedEnergy.disjoin(this.energy, energy);
		if(!used.isEmpty() && used.isEqual(energy)){
			this.energy.minus(used.copy());
			return used;
		}
		return new RedEnergy(energy.dimension, energy.x, energy.z, Composition.getStandard(0, 0, 0, 0, 0));
	}
	
	@Override
	public float use(String element, float amount){
		if(this.energy.composition.getValue(element) >= amount){
			return this.energy.decreaseValue(element, amount);
		}
		return 0;
	}
	
	@Override
	public RedEnergy store(RedEnergy energy){
		this.energy.merge(energy.copy());
		return energy;
	}
	
	@Override
	public float store(String element, float amount){
		this.energy.addValue(element, amount);
		return amount;
	}

	@Override
	public RedEnergy getStoredEnergy() {
		return energy;
	}

	@Override
	public int getChunkX() {
		return (int)(xCoord  / 16);
	}

	@Override
	public int getChunkZ() {
		return (int)(zCoord  / 16);
	}



	public boolean addMachine(int metadata, int side, EntityPlayer player) {
		if(machines[side] == null){
			Machine machine = MachineHandler.getMachine(metadata);
			if(machine != null && machine.canPlacedOnSide(side, getHandlerSize()) && getUsedSize() + machine.getSize() <= 6){
				machines[side] = machine;
				machines[side].onPlacedByPlayer(this, side, player);
				player.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeMachine(int side, EntityPlayer player) {
		Machine machine = getMachineOnSide(side);
		if(machine != null){
			machine.removeByPlayer((IMachineHandler)this, player);
			player.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			machines[side] = null;
			return true;
		}
		return false;
	}



	public boolean activate(EntityPlayer player, int side, float offX, float offY, float offZ) {
		player.getEntityData().setInteger("redmagic.machine.side", side);
		if(machines[side] != null){
			machines[side].activate((IMachineHandler)this, player, offX, offY, offZ);
			return true;
		}
		return false;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		
		NBTTagCompound energyTag = tag.getCompoundTag("redmagic.energy");
		this.energy = RedEnergy.loadFromNBT(energyTag);
		
		this.heat = tag.getFloat("redmagic.heat");
		NBTTagList list = tag.getTagList("redmagic.machines");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound machineTag = (NBTTagCompound) list.tagAt(i);
			Machine machine = Machine.loadFromNBT(machineTag);
			machines[machine.getSide()] = machine;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		
		NBTTagCompound energyTag = new NBTTagCompound();
		energy.writeToNBT(energyTag);
		tag.setTag("redmagic.energy", energyTag);
		
		tag.setFloat("redmagic.heat", heat);
		NBTTagList list = new NBTTagList();
		for(Machine machine: machines){
			if(machine != null){
				NBTTagCompound machineTag = new NBTTagCompound();
				machine.writeToNBT(machineTag);
				list.appendTag(machineTag);
			}
		}
		tag.setTag("redmagic.machines", list);
	}

	public Packet getDescriptionPacket()
    {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, data);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		this.readFromNBT(pkt.data);
    }

	public void onBreak() {
		for(int i = 0; i < 6; i++){
			this.removeMachine(i);
		}
	}

	public void removeMachine(int side) {
		Machine machine = getMachineOnSide(side);
		if(machine != null){
			machine.remove(this);
			worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			machines[side] = null;
		}
	}

	public void onDisplayTick(Random par5Random) {
		for(Machine machine: machines){
			if(machine != null)machine.onDisplayTick((IMachineHandler)this, par5Random);
		}
	}

	public void onNeighborChange(int blockID) {
		for(Machine machine: machines){
			if(machine != null)machine.onNeighborChange((IMachineHandler)this, blockID);
		}
	}

	@Override
	public int getHandlerSize() {
		return 1;
	}

	@Override
	public VirtualBlock getBlockInfrontMachineOnSide(int side) {
		switch(side){
		case 0: return new VirtualBlock(xCoord, yCoord - 1, zCoord);
		case 1: return new VirtualBlock(xCoord, yCoord + 1, zCoord);
		case 2: return new VirtualBlock(xCoord, yCoord, zCoord - 1);
		case 3: return new VirtualBlock(xCoord, yCoord, zCoord + 1);
		case 4: return new VirtualBlock(xCoord - 1, yCoord, zCoord);
		case 5: return new VirtualBlock(xCoord + 1, yCoord, zCoord);
		default: return new VirtualBlock(xCoord, yCoord - 1, zCoord);
		}
	}
	
	
}
