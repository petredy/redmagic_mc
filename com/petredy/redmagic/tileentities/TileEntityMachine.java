package com.petredy.redmagic.tileentities;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.glasses.IViewable;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.utils.LogUtils;

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
	public float energy;
	
	public float heat;
	
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
	public int getSize(){
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
	public float collect(World world, float amount, int chunkX, int chunkZ) {
		float collected = EnergyMap.consumeEnergy(world.provider.dimensionId, chunkX, chunkZ, amount);
		return store(collected);
	}

	@Override
	public float release(World world, float amount, int chunkX, int chunkZ) {
		float released = EnergyMap.releaseEnergy(new RedEnergy(world.provider.dimensionId, chunkX, chunkZ, amount));
		return use(released);
	}
	
	@Override
	public float use(float amount) {
		if(getStoredEnergy() >= amount){
			this.energy -= amount;
			return amount;
		}
		return 0;
	}
	
	@Override
	public float store(float amount){
		this.energy += amount;
		return amount;
	}

	@Override
	public float getStoredEnergy() {
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



	public boolean setMachine(int metadata, int side, EntityPlayer player) {
		if(machines[side] == null){
			Machine machine = Machine.getMachine(metadata);
			if(machine != null && !containsMachine(machine) && machine.canPlacedOnSide(side) && getSize() + machine.getSize() <= 6){
				machines[side] = machine;
				machines[side].onPlacedByPlayer(this, side, player);
				player.worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
				return true;
			}
		}
		return false;
	}
	
	public void removeMachine(EntityPlayer player, int side, float offX, float offY, float offZ) {
		Machine machine = getMachineOnSide(side);
		if(machine != null){
			machine.removeByPlayer(this, player, offX, offY, offZ);
			player.worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			machines[side] = null;
		}
	}



	public boolean activate(EntityPlayer player, int side, float offX, float offY, float offZ) {
		if(machines[side] != null){
			machines[side].activate((IMachineHandler)this, player, offX, offY, offZ);
			return true;
		}
		return false;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.energy = tag.getFloat("redmagic.energy");
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
		tag.setFloat("redmagic.energy", energy);
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
	
	
}
