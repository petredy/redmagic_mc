package redmagic.configuration;

public class Reference {
	//Mod Metdata
	public static final String MOD_ID = "redmagic";
	public static final String MOD_NAME = "Redmagic";
	public static final String VERSION = "0.0.40";
	
	//Proxies
	public static final String PROXY_CLIENT = "redmagic.client.ClientProxy";
	public static final String PROXY_SERVER = "redmagic.core.CommonProxy";
	
	//PacketHandler Channels
	public static final String PACKET_CHANNEL_CLIENT = "redmagic_client";
	public static final String PACKET_CHANNEL_SERVER = "redmagic_server";
	
	//Debug
	public static final String DEBUG = "debug";
	
	//Keys
	public static final String KEY_EXTRA_NAME = MOD_NAME + " key";
	public static final int KEY_EXTRA_DEFAULT = 47;
	public static int KEY_EXTRA_ID;
	
	//Storage
	public static final int SAVE_INTERVAL = 10000;
	public static final String BANK_TOKEN = MOD_ID + "_bank";
}
