package com.petredy.redmagic.lib;

import com.petredy.redmagic.network.PacketBuyItem;
import com.petredy.redmagic.network.PacketKeyPressed;
import com.petredy.redmagic.network.PacketSellItem;


public class Packets {
	public static Class[] PACKETS = new Class[]{
		PacketKeyPressed.class,
		PacketBuyItem.class,
		PacketSellItem.class
	};
	
	public static final byte KEY = 0;
	public static final byte BUY = 1;
	public static final byte SELL = 2;
}
