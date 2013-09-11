package com.petredy.redmagic.tileentities;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityEngine extends TileEntityInventory implements IPowerEmitter, ISidedInventory{

	public ForgeDirection side;
	public float rotate = 0;
	public float speed = 0;
	public float production = 2;
	
	public TileEntityEngine() {
		super(BlockIndex.ENGINE_NAME, 1);
	}

	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return this.side == side;
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.getStrongestIndirectPower(xCoord, yCoord, zCoord) > 0){
			speed = production = (float)worldObj.getStrongestIndirectPower(xCoord, yCoord, zCoord) / 7.5f;
			providerPowerOnSide(side);
		}else{
			speed = production = 0;
		}
		
	}

	private void providerPowerOnSide(ForgeDirection direction) {
		if(direction == ForgeDirection.UP){
			providePower(xCoord, yCoord + 1, zCoord, ForgeDirection.DOWN);
		}
		if(direction == ForgeDirection.DOWN){
			providePower(xCoord, yCoord - 1, zCoord, ForgeDirection.UP);
		}
		if(direction == ForgeDirection.EAST){
			providePower(xCoord + 1, yCoord, zCoord, ForgeDirection.WEST);
		}
		if(direction == ForgeDirection.WEST){
			providePower(xCoord - 1, yCoord, zCoord, ForgeDirection.EAST);
		}
		if(direction == ForgeDirection.NORTH){
			providePower(xCoord, yCoord, zCoord - 1, ForgeDirection.SOUTH);
		}
		if(direction == ForgeDirection.SOUTH){
			providePower(xCoord, yCoord, zCoord + 1, ForgeDirection.NORTH);
		}
	}

	private void providePower(int x, int y, int z, ForgeDirection direction) {
		TileEntity entity = worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof IPowerReceptor){
			PowerReceiver receiver = ((IPowerReceptor)entity).getPowerReceiver(direction);
			if(receiver != null)receiver.receiveEnergy(PowerHandler.Type.ENGINE, production, direction);
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

}
