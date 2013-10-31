package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachinery extends TileEntity {

	public VirtualBlock reference;
	
	public int position;

	public void reset() {
		reference = null;
		position = 0;
		
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
