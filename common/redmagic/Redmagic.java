package redmagic;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import redmagic.blocks.*;
import redmagic.configuration.*;
import redmagic.core.*;
import redmagic.handlers.*;
import redmagic.items.*;
import redmagic.lib.bank.BankData;
import redmagic.network.*;

@Mod( modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(channels = { Reference.PACKET_CHANNEL_CLIENT, Reference.PACKET_CHANNEL_SERVER },clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)

public class Redmagic{

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
	public static CommonProxy proxy;
	
	@Instance(Reference.MOD_NAME)
	public static Redmagic instance;
	
	public static CreativeTabs tabRedMagic = new CreativeTabRedMagic(CreativeTabs.getNextID(), Reference.MOD_NAME);
	
	public static boolean DEBUG;
	
	public static boolean loaded = false;
	public static long saved = 0;
	
	
	public static Configuration data;
	public static BankData bankData;
	
	//-------------------------------------------------------------------------------------------------------------
	//PRE INIT
	@SuppressWarnings("static-access")
	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{		
		instance = this;
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());
        config.load();
        
        //Initialise Logger
        Logger.init(config);
        
        //Initialise Language Handling
      	LanguageHandler.init();
      	
      	//Initialise TileEntity Registration
      	TileEntityHandler.init();
      	
      	//Configurate LogicElements
      	ConfigHandler.config(config);
        
        //Register GuiHandler
        NetworkRegistry.instance().registerGuiHandler(instance, this.proxy);
        
        //Configurate Items
        ItemManager.config(config);
        
        //Configurate Blocks
        BlockManager.config(config);
        
        //Initialise Items
  		ItemManager.init();
  				
  		//Initialise Blocks
  		BlockManager.init();
        
        //Configurates Keybinding
        KeyBindingHandler.config(config);
        
        //Initialise Liquid Registration
      	LiquidHandler.init();
        
        config.save();
	}
	
	
	
	//----------------------------------------------------------------------------------------
	//INIT
	@Init
    public void init(FMLInitializationEvent event)
    {
		proxy.registerAll();
		
		//Handle Recipe Registration
		RecipeHandler.registry();
		
		//Initialise World Generation
		GameRegistry.registerWorldGenerator(new WorldGenerationHandler());
		
		
		// Initialise Liquid Texture Mapping
		MinecraftForge.EVENT_BUS.register(new LiquidHandler());
		
		//Initialise WorldLoad Handler
      	MinecraftForge.EVENT_BUS.register(new WorldLoadingHandler());
		
		proxy.registerRendering();
		
    }
	
	//----------------------------------------------------------------------------------------
	//PostINIT
	@PostInit
    public void init(FMLPostInitializationEvent event)
    {

    }
	
	public static void initialiseData() {
		DataHandler.init(Redmagic.data);
	}
}
