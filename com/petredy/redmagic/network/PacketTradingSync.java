package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.Player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

public class PacketTradingSync extends PacketRedMagic {

	public NBTTagCompound data;
	
	public PacketTradingSync(NBTTagCompound data) {
		this();
		this.data = data;
	}

	public PacketTradingSync() {
		super(Packets.SYNC, false);
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.data = this.readNBTTagCompound(data);
	}
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		this.writeNBTTagCompound(this.data, data);
	}
	
	public void execute(INetworkManager manager, Player player) {
		TradingManager.setData(data);
	}

}
