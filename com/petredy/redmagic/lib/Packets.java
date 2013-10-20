package com.petredy.redmagic.lib;

import com.petredy.redmagic.network.*;


public class Packets {
	public static Class[] PACKETS = new Class[]{
		PacketKeyPressed.class,
		PacketBuyItem.class,
		PacketSellItem.class,
		PacketNBTSync.class,
		PacketTradingSync.class,
		PacketEnergyMapSync.class,
		PacketMachineSync.class
	};
	
	public static final byte KEY = 0;
	public static final byte BUY = 1;
	public static final byte SELL = 2;
	public static final byte SYNC = 3;
	public static final byte SYNC_TRADING = 4;
	public static final byte SYNC_ENERGY_MAP = 5;
	public static final byte MACHINE_SYNC = 6;
}
