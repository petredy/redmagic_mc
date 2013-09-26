package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.utils.LogUtils;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntitySoulEnergyAccess extends TileEntity implements IPowerReceptor, IPowerEmitter{
	
	public int soulX, soulY, soulZ;
	
	
	public void updateEntity(){
		if(this.findSoul()){
			this.providePowerOnSide(ForgeDirection.UP, 50);
			this.providePowerOnSide(ForgeDirection.DOWN, 50);
			this.providePowerOnSide(ForgeDirection.NORTH, 50);
			this.providePowerOnSide(ForgeDirection.EAST, 50);
			this.providePowerOnSide(ForgeDirection.SOUTH, 50);
			this.providePowerOnSide(ForgeDirection.WEST, 50);
			
		}
	}
	
	private boolean findSoul() {
		if(worldObj.getBlockTileEntity(soulX, soulY, soulZ) instanceof TileEntitySoul)return true;
		return this.scanForSoul();
	}

	private boolean scanForSoul() {
		for(int i = -10; i <= 10; i++){
			for(int j = -10; j <= 10; j++){
				for(int k = -10; k <= 10; k++){
					if(worldObj.getBlockTileEntity(xCoord + i, yCoord + j, zCoord + k) instanceof TileEntitySoul){
						soulX = xCoord + i;
						soulY = yCoord + j;
						soulZ = zCoord + k;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		TileEntity entity = worldObj.getBlockTileEntity(soulX, soulY, soulZ);
		if(entity  instanceof TileEntitySoul){
			return ((TileEntitySoul)entity).handler.getPowerReceiver();
		}
		return null;
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		workProvider.update();
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return worldObj.getBlockTileEntity(soulX, soulY, soulZ) instanceof TileEntitySoul;
	}
	
	private void providePowerOnSide(ForgeDirection direction, int production) {
		if(direction == ForgeDirection.UP){
			providePower(xCoord, yCoord + 1, zCoord, ForgeDirection.DOWN, production);
		}
		if(direction == ForgeDirection.DOWN){
			providePower(xCoord, yCoord - 1, zCoord, ForgeDirection.UP, production);
		}
		if(direction == ForgeDirection.EAST){
			providePower(xCoord + 1, yCoord, zCoord, ForgeDirection.WEST, production);
		}
		if(direction == ForgeDirection.WEST){
			providePower(xCoord - 1, yCoord, zCoord, ForgeDirection.EAST, production);
		}
		if(direction == ForgeDirection.NORTH){
			providePower(xCoord, yCoord, zCoord - 1, ForgeDirection.SOUTH, production);
		}
		if(direction == ForgeDirection.SOUTH){
			providePower(xCoord, yCoord, zCoord + 1, ForgeDirection.NORTH, production);
		}
	}
	
	private void providePower(int x, int y, int z, ForgeDirection direction, int production) {
		TileEntity entity = worldObj.getBlockTileEntity(x, y, z);
		TileEntitySoul soul = (TileEntitySoul) worldObj.getBlockTileEntity(soulX, soulY, soulZ);
		if(entity instanceof IPowerReceptor){
			PowerReceiver receiver = ((IPowerReceptor)entity).getPowerReceiver(direction);
			if(receiver != null)receiver.receiveEnergy(PowerHandler.Type.ENGINE, soul.handler.useEnergy(10, production, true), direction);
		}
	}

}
