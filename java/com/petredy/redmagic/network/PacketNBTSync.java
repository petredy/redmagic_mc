package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.trading.TradingManager;

import cpw.mods.fml.common.network.Player;

public class PacketNBTSync extends PacketRedmagic{

	public NBTTagCompound data;

	public PacketNBTSync(){
		super(Packets.SYNC, false);
	}
	
	public PacketNBTSync(byte type) {
		super(type, false);
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.data = this.readNBTTagCompound(data);
	}
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		this.writeNBTTagCompound(this.data, data);
	}
	
	

}
