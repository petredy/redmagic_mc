package com.petredy.redmagic.lib;

import com.petredy.redmagic.network.packets.DataPacket;
import com.petredy.redmagic.network.packets.KeyPressedDataPacket;

public class DataPackets {

	public static final Class[] PACKETS = new Class[]{
		DataPacket.class,
		KeyPressedDataPacket.class
	};
	
	public static final int DEFAULT = 0;
	public static final int KEY_PRESSED = 1;
	
}
