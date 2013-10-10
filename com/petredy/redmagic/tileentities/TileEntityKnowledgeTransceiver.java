package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.api.glasses.IViewable;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityKnowledgeTransceiver extends TileEntity{
	
	public ItemStack artifact;
	
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		NBTTagCompound artiTag = (NBTTagCompound) tag.getTag("artifact");
		if(artiTag != null)this.artifact = ItemStack.loadItemStackFromNBT(artiTag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		
		if(artifact != null){
			NBTTagCompound artiTag = new NBTTagCompound();
			
			artifact.writeToNBT(artiTag);
			tag.setTag("artifact", artiTag);
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
	
}
