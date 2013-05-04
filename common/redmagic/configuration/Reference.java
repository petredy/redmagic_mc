package redmagic.configuration;

public class Reference {
	//Mod Metdata
	public static final String MOD_ID = "redmagic";
	public static final String MOD_NAME = "Redmagic";
	public static final String VERSION = "0.0.30";
	
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
	public static final String SAVE_DIR = "saves";
	public static final String WORLD_STORAGE_FILE = "data.cfg";
	public static final int SAVE_INTERVAL = 1000;
	public static final String BANK_DATA_TYPE = "bank";
	public static final String BANK_DATA_SPLITTER = ";";
	public static final String BANK_DATA_COUNT = "ItemCount";
}
