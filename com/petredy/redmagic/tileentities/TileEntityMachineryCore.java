package com.petredy.redmagic.tileentities;

import java.util.HashMap;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.items.ItemMachine;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.machinery.MachineHandler;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketMachineSync;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class TileEntityMachineryCore extends TileEntity implements IMachineHandler, IEnergyHandler{

	protected HashMap<Integer, Machine> machines = new HashMap<Integer, Machine>();
	
	public boolean found = false;
	public int tick = 0, neededTicks = 100;

	
	protected RedEnergy energy = new RedEnergy();
	public float heat;
	
	public int MAX_SIZE = 5;
	
	@Override
	public boolean addMachine(int metadata, int side, EntityPlayer player) {
		Machine machine = MachineHandler.getMachine(metadata);
		if(machine != null && side > 0 && side < 6 && machine.canPlacedOnSide(side, getHandlerSize()) && getUsedSize() + machine.getSize() <= MAX_SIZE){
			Machine atm = machines.get(side);
			if(atm == null){
				machines.put(side, machine);
				machine.onPlacedByPlayer((IMachineHandler)this, side, player);
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				this.markAllForRenderUpdate();
				return true;
			}
		}
		return false;
	}
	
	public boolean removeMachine(int side, EntityPlayer player){
		Machine machine = getMachineOnSide(side);
		if(machine != null){
			machine.removeByPlayer((IMachineHandler)this, player);
			machines.put(side, null);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			this.markAllForRenderUpdate();
			return true;
		}
		return false;
	}

	@Override
	public void updateEntity(){
		if(found){
			update();
		}
		if(tick++ >= neededTicks){
			tick = 0;
			found = findOrBuildMachinery();
			PacketDispatcher.sendPacketToAllInDimension(PacketHandler.populatePacket(new PacketMachineSync(xCoord, yCoord, zCoord, energy)), worldObj.provider.dimensionId);
		}
	}
	
	private void update(){
		for(Machine machine: machines.values()){
			if(machine != null){
				machine.update((IMachineHandler)this);
			}
		}
	}
	
	
	private boolean findOrBuildMachinery() {
		int position = 0;
		for(int y = 0; y < 2; y++){
			for(int z = -1; z <= 1; z++){
				for(int x = -1; x <= 1; x++){
						TileEntity entity = worldObj.getBlockTileEntity(x + xCoord, y + yCoord, z + zCoord);
						if(entity instanceof TileEntityMachinery){
							((TileEntityMachinery) entity).reference = new VirtualBlock(xCoord, yCoord, zCoord);
							((TileEntityMachinery) entity).position = position++;
							worldObj.markBlockForUpdate(x + xCoord, y + yCoord, z + zCoord);
						}else if(entity instanceof TileEntityMachineryCore){
							if(entity.xCoord != xCoord && entity.yCoord != yCoord && entity.zCoord != zCoord)return false;
						}else{
							resetAll();
							return false;
						}
				}
			}
		}
		return true;
	}

	private void resetAll() {
		for(int y = 0; y < 2; y++){
			for(int z = -1; z <= 1; z++){
				for(int x = -1; x <= 1; x++){
					TileEntity entity = worldObj.getBlockTileEntity(x + xCoord, y + yCoord, z + zCoord);
					if(entity instanceof TileEntityMachinery){
						((TileEntityMachinery) entity).reference = null;
						((TileEntityMachinery) entity).position = 0;
						worldObj.markBlockForUpdate(x + xCoord, y + yCoord, z + zCoord);
					}
				}
			}
		}
		
	}

	@Override
	public Machine getMachineOnSide(int i) {
		if(i > 0 && i < 6)return machines.get(i);
		return null;
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
	public int getUsedSize() {
		int size = 0;
		for(Machine machine: machines.values()){
			if(machine != null){
				size += machine.getSize();
			}
		}
		return size;
	}
	
	@Override
	public int getHandlerSize(){
		return 18;
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

	public boolean activate(int side, EntityPlayer player, float offX, float offY, float offZ) {
		Machine machine = this.machines.get(side);
		if(machine != null){
			return machine.activate((IMachineHandler)this, player, offX, offY, offZ);
		}
		return false;
	}
	
	private void markAllForRenderUpdate() {
		for(int y = 0; y < 2; y++){
			for(int x = -1; x <= 1; x++){
				for(int z = -1; z <= 1; z++){
					worldObj.markBlockForRenderUpdate(x + xCoord, y + yCoord, z + zCoord);
				}
			}
		}
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
	
	// IEnergyHandler

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
		return (int)(xCoord / 16);
	}

	@Override
	public int getChunkZ() {
		return (int)(zCoord / 16);
	}
	
	
}
