package com.petredy.redmagic.tileentities;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.lib.BlockIndex;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityEngine extends TileEntityInventory implements IPowerEmitter{

	public ForgeDirection side;
	public float rotate = 0;
	public TileEntityEngine() {
		super(BlockIndex.ENGINE_NAME, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return true;
	}
	
	@Override
	public void updateEntity(){
		TileEntity entity = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
		if(entity instanceof IPowerReceptor){
			IPowerReceptor receptor = (IPowerReceptor)entity;
			PowerReceiver receiver = receptor.getPowerReceiver(side.DOWN);
			if(receiver != null)receiver.receiveEnergy(PowerHandler.Type.ENGINE, 100, side.DOWN);
		}
	}

}
