package redmagic.configuration;

import redmagic.network.*;

public class PacketIndex {
	@SuppressWarnings("rawtypes")
	public static final Class[] PACKETS = new Class[]{
		PacketKeyPressed.class,
		PacketEnergyStorage.class,
		PacketWorkTable.class
	};
	public static final byte KEY = 0;
	public static final byte ENERGY_STORAGE = 1;
	public static final byte WORK_TABLE = 2;
}
