package com.petredy.redmagic.tileentities;

import java.util.Random;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketMachineSync;
import com.petredy.redmagic.network.PacketStructureSync;
import com.petredy.redmagic.structure.Structure;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.StructureUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityStructure extends TileEntity{
	
		public int index;
		public int lastIndex;
		public int position = 0;
		public int machine = -1;
	
		public void updateEntity(){
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				structure.requestUpdate(worldObj);
				StructureUtils.saveStructure(worldObj, structure);
			}
		}
		

		
		public void destroy() {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				structure.notifyAllBlocks(worldObj, 0);
			}
		}
		
		public void build(){
			Structure structure = new Structure();
			structure.build(worldObj, xCoord, yCoord, zCoord);
			if(structure.isComplete()){
				if(structure.id == 0){
					structure.id = this.index = worldObj.getUniqueDataId(StructureUtils.getTokenPrefix());
					if(index == 0)structure.id = this.index = worldObj.getUniqueDataId(StructureUtils.getTokenPrefix());
				}else{
					this.index = structure.id;
				}
				structure.notifyAllBlocks(worldObj, index);
				StructureUtils.saveStructure(worldObj, structure);
			}
		}
		
		public boolean hasStructure(){
			return index > 0;
		}
		
		public Structure getStructure(){
			return StructureUtils.loadStructure(worldObj, index);
		}

		public void notify(Integer id) {
			if(id == 0){
				//Reset local data
				this.position = -1;	
				this.machine = -1;
				this.lastIndex = this.index;
			}else{
				// Set local data
				Structure structure = StructureUtils.loadStructure(worldObj, id);
				if(structure != null){
					this.position = structure.getPosition(xCoord, yCoord, zCoord);
				}
			}
			this.index = id;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		
		public Packet getDescriptionPacket(){
	        NBTTagCompound data = new NBTTagCompound();
	        this.writeToNBT(data);
	        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, data);
	    }
		
		public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
			this.readFromNBT(pkt.data);
		}
		
		public void readFromNBT(NBTTagCompound tag){
			super.readFromNBT(tag);
			this.index = tag.getInteger("redmagic.index");
			this.position = tag.getInteger("redmagic.position");
			this.lastIndex = tag.getInteger("redmagic.lastIndex");
			this.machine = tag.getInteger("redmagic.machine");
		}
		
		public void writeToNBT(NBTTagCompound tag){
			super.writeToNBT(tag);
			tag.setInteger("redmagic.index", index);
			tag.setInteger("redmagic.position", position);
			tag.setInteger("redmagic.lastIndex", lastIndex);
			tag.setInteger("redmagic.machine", machine);
		}

		public boolean setMachine(int metadata, EntityPlayer player) {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			int side;
			if(structure != null && (side = structure.getSideByPosition(position)) >= 0){
				if(structure.machines[side] == null){
					Machine machine = Machine.getMachine(metadata);
					if(machine != null && !containsMachine(machine) && machine.canPlacedOnSide(side) && getSize() + machine.getSize() <= 6){
						structure.machines[side] = machine;
						structure.machines[side].onPlacedByPlayer((IMachineHandler) structure, side, player);
						player.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						this.machine = metadata;
						StructureUtils.saveStructure(worldObj, structure);
						return true;
					}
				}
			}
			return false;
		}

		private int getSize() {
			int size = 0;
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				for(Machine machine: structure.machines){
					if(machine != null)size += machine.getSize();
				}
			}
			return size;
		}

		public Machine getMachine(int metadata){
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				for(Machine machine: structure.machines){
					if(machine != null && machine.getMetadata() == metadata)return machine;
				}
			}
			return null;
		}

		private boolean containsMachine(Machine machine) {
			return getMachine(machine.getMetadata()) != null;
		}
		
		public void removeMachine(EntityPlayer player, float offX, float offY, float offZ) {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			int side;
			if(structure != null && (side = structure.getSideByPosition(position))> 0){
				Machine machine = structure.getMachineOnSide(side);
				if(machine != null){
					machine.removeByPlayer((IMachineHandler) structure, player, offX, offY, offZ);
					player.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					structure.machines[side] = null;
					this.machine = -1;
					StructureUtils.saveStructure(worldObj, structure);
				}
			}
		}
		
		public boolean activate(EntityPlayer player, float offX, float offY, float offZ) {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			int side = 0;
			if(structure != null && (side = structure.getSideByPosition(position)) >= 0 && structure.machines[side] != null){
				structure.machines[side].activate((IMachineHandler)this, player, offX, offY, offZ);
				StructureUtils.saveStructure(worldObj, structure);
				return true;
			}
			return false;
		}
		
		public void onBreak() {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				for(int i = 0; i < 6; i++){
					this.removeMachine(structure, i);
				}
				StructureUtils.saveStructure(worldObj, structure);
			}
		}
		
		public void removeMachine(Structure structure, int side) {
			Machine machine = structure.getMachineOnSide(side);
			if(machine != null){
				machine.remove(structure);
				worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
				structure.machines[side] = null;
				StructureUtils.saveStructure(worldObj, structure);
				this.machine = -1;
			}
		}
		
		public void onDisplayTick(Random par5Random) {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				for(Machine machine: structure.machines){
					if(machine != null)machine.onDisplayTick((IMachineHandler)this, par5Random);
				}
				StructureUtils.saveStructure(worldObj, structure);
			}
		}

		public void onNeighborChange(int blockID) {
			Structure structure = StructureUtils.loadStructure(worldObj, index);
			if(structure != null){
				for(Machine machine: structure.machines){
					if(machine != null)machine.onNeighborChange((IMachineHandler)this, blockID);
				}
				StructureUtils.saveStructure(worldObj, structure);
			}
		}
}
