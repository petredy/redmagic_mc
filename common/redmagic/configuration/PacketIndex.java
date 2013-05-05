package redmagic.configuration;

import redmagic.network.*;

public class PacketIndex {
	@SuppressWarnings("rawtypes")
	public static final Class[] PACKETS = new Class[]{
		PacketKeyPressed.class,
		PacketWorkTable.class,
		PacketBuyItem.class,
		PacketSellItem.class
	};
	public static final byte KEY = 0;
	public static final byte WORK_TABLE = 1;
	public static final byte BUY = 2;
	public static final byte SELL = 3;
}
