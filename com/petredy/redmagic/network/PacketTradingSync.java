package com.petredy.redmagic.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.trading.TradingManager;

import cpw.mods.fml.common.network.Player;

public class PacketTradingSync extends PacketNBTSync {

	public PacketTradingSync() {
		super(Packets.SYNC_TRADING);
	}
	
	public PacketTradingSync(NBTTagCompound data) {
		super(Packets.SYNC_TRADING);
		this.data = data;
	}

	public void execute(INetworkManager manager, Player player) {
		TradingManager.setData(data);
	}
	
}
