package redmagic.configuration;

import redmagic.network.*;

public class PacketIndex {
	@SuppressWarnings("rawtypes")
	public static final Class[] PACKETS = new Class[]{
		PacketKeyPressed.class,
		PacketTalentUnlocked.class,
		PacketSyncPlayerInformation.class
	};
	public static final byte KEY = 0;
	public static final byte TALENT_UNLOCKED = 1;
	public static final byte MANAGE_PLAYER_INFORMATION = 2;
}
