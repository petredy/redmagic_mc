/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.petredy.redmagic.tileentities;

import java.util.LinkedList;

import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.Type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityEngine extends TileEntity implements IPowerEmitter {

	public int rotation, moving;
	public int modifier = 1;
	public ForgeDirection side;
	
	public float power = 100;
	public float energy;
	public float speed = 1.0f;
	@Override
	public boolean canEmitPowerFrom(ForgeDirection side){
		return side == this.side;
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
			if(moving >= 100){
				this.sendPower();
				modifier = -1;
			}else if(moving <= 0)modifier = 1;
		}else{
			if(moving >= 100){
				this.sendPower();
				modifier = -1;
			}
			if(moving <= 0)modifier = 0;
		}
		moving += modifier * speed;
	}

	private void sendPower() {
		speed += 0.1f;
		if(speed > 2.0f)speed = 2.0f;
		LogUtils.log(speed);
		energy = 0;
		switch(BlockUtils.forgeDirectionToInt(side)){
		case 0: {
			this.providePower(worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord));
			break;
		}
		case 1: {
			this.providePower(worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord));
			break;
		}
		case 2: {
			this.providePower(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1));
			break;
		}
		case 3: {
			this.providePower(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1));
			break;
		}
		case 4: {
			this.providePower(worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord));
			break;
		}
		case 5: {
			this.providePower(worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord));
			break;
		}
		}
	}

	private void providePower(TileEntity blockTileEntity) {
		if(blockTileEntity instanceof IPowerReceptor){
			IPowerReceptor receptor = (IPowerReceptor)blockTileEntity;
			LogUtils.log("provider power");
			receptor.getPowerReceiver(ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[BlockUtils.forgeDirectionToInt(side)])).receiveEnergy(PowerHandler.Type.ENGINE, power, side);
		}
	}

	
}
