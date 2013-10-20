/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.petredy.redmagic.tileentities;

import java.util.LinkedList;

import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.Redkey;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import buildcraft.api.core.Position;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityEngine extends TileEntity implements IPowerEmitter {

	public int rotation, moving;
	public int modifier = 1;
	public ForgeDirection side;
	
	public float power = 50;
	public float costs = 100;
	public float speed = 1.0f;
	
	public Composition cost = Composition.getStandard(0, 0, 0, 10, 0);
	
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
			}else if(moving <= 0){
				modifier = 1;
			}
		}else{
			if(moving >= 100){
				this.sendPower();
				modifier = -1;
			}
			if(moving <= 0){
				modifier = 0;
				speed = 1.0f;
			}
		}
		moving += modifier * speed;
		speed += 0.01f;
		if(speed > 2.0f)speed = 2.0f;
	}

	private void sendPower() {
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
			if(receptor != null && !blockTileEntity.worldObj.isRemote && side != null){
				RedEnergy consumed = EnergyMap.consumeEnergy(new RedEnergy(worldObj.provider.dimensionId, xCoord / 16, zCoord / 16, cost));
				if(consumed.isEqual(new RedEnergy(worldObj.provider.dimensionId, xCoord / 16, zCoord / 16, cost))){
					PowerReceiver receiver = receptor.getPowerReceiver(side.getOpposite());
					if(receiver != null)receiver.receiveEnergy(PowerHandler.Type.ENGINE, power, side.getOpposite());
				}
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.side = ForgeDirection.getOrientation(tag.getInteger("side"));
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("side", BlockUtils.forgeDirectionToInt(side));
	}
	
	
	public boolean switchOrientation() {
		if(side == null)side = ForgeDirection.DOWN;
		for (int i = side.ordinal() + 1; i <= side.ordinal() + 6; ++i) {
			ForgeDirection o = ForgeDirection.VALID_DIRECTIONS[i % 6];

			Position pos = new Position(xCoord, yCoord, zCoord, o);
			pos.moveForwards(1);
			TileEntity tile = worldObj.getBlockTileEntity((int) pos.x, (int) pos.y, (int) pos.z);

			if (tile instanceof IPowerReceptor) {
				side = o;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlockId(xCoord, yCoord, zCoord));

				return true;
			}
		}
		return false;
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
}
