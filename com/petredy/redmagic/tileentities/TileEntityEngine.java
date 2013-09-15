package com.petredy.redmagic.tileentities;

import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityEngine extends TileEntityInventory implements IPowerEmitter, ISidedInventory{

	public ForgeDirection side;
	public float rotate = 0;
	public float speed = 0;
	public float need = 100;
	public float production;
	public int count = 0;
	
	
	public float plusT1 = 0.1f;
	public float plusT2 = 1.0f;
	public float plusT3 = 10.0f;
	
	public TileEntityEngine() {
		super(BlockIndex.ENGINE_NAME, 1);
	}

	@Override
	public boolean canEmitPowerFrom(ForgeDirection side) {
		return this.side == side;
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.getStrongestIndirectPower(xCoord, yCoord, zCoord) > 0 && this.getItem() != null){
			speed = (float)worldObj.getStrongestIndirectPower(xCoord, yCoord, zCoord) / 7.5f;
			providerPowerOnSide(side);
			production = (float) Math.max(5, Math.sqrt(RedvalueDictionary.getRedvalue(getItem())));
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
	public ItemStack getStackInSlot(int par1){
        return null;
    }
	
	public boolean hasItem(){
		return inv[0] != null;
	}
	
	public ItemStack getItem(){
		return inv[0];
	}
	
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack){
		if(inv[par1] == null)inv[par1] = par2ItemStack;
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
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt){
		this.readFromNBT(pkt.data);
    }
	
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }

}
