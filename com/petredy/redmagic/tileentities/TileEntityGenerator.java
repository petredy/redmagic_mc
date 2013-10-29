package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityGenerator extends TileEntity implements IEnergySource{

	public double energy = 0;
	public double max = 1000;
	public boolean load = false;
	public boolean run = false;
	
	public double output = 5;
	
	public void updateEntity(){
		
		if(load){
			load = false;
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
		}

		RedEnergy energy = new RedEnergy(worldObj.provider.dimensionId, xCoord / 16, zCoord / 16, Composition.getStandard(0, 0, 0, 15f, 0));
		if(this.energy + output < max && EnergyMap.consumeEnergy(energy.copy()).isEqual(energy.copy())){
			this.energy += output;
			this.run = true;
		}else{
			if(run)worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			this.run = false;
		}
	}
	
	public void validate()
    {
		super.validate();
		load = true;
    }
	
	public void onChunkUnload(){
		delete();
    }
	
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		double rtn = Math.min(energy, output);
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return rtn;
	}

	@Override
	public void drawEnergy(double amount) {
		energy -= amount;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		energy = tag.getDouble("energy");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setDouble("energy", energy);
	}

	public void delete() {
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}

}
