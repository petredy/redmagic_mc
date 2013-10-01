package com.petredy.redmagic.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.trading.TradingManager;

import cpw.mods.fml.common.network.Player;

public class PacketEnergyMapSync extends PacketNBTSync {

	public PacketEnergyMapSync() {
		super(Packets.SYNC_ENERGY_MAP);
	}
	
	public PacketEnergyMapSync(NBTTagCompound data) {
		super(Packets.SYNC_ENERGY_MAP);
		this.data = data;
	}

	public void execute(INetworkManager manager, Player player) {
		EnergyMap.readFromNBT(data);
	}
	
}
