package redmagic;


import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
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
import redmagic.helpers.LogHelper;
import redmagic.items.*;
import redmagic.network.*;

@Mod( modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(channels = { Reference.PACKET_CHANNEL_CLIENT, Reference.PACKET_CHANNEL_SERVER },clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)

public class Redmagic{

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
	public static CommonProxy proxy;
	
	@Instance(Reference.MOD_NAME)
	public static Redmagic instance;
	
	public static CreativeTabs tabRedmagic = new CreativeTabRedMagic(CreativeTabs.getNextID(), Reference.MOD_NAME);
	
	public static boolean DEBUG;
	
	
	//-------------------------------------------------------------------------------------------------------------
	//PRE INIT
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{		
		instance = this;
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());
        config.load();

        System.out.println(config);
        //Initialise Logger
        LogHelper.init(config);
        
        //Initialise Language Handling
      	LanguageHandler.init();
      	
      	//Initialise TileEntity Registration
      	TileEntityHandler.init();
        
        //Register GuiHandler
        NetworkRegistry.instance().registerGuiHandler(instance, this.proxy);
        
        //Register Rendering IDs
        proxy.initRendering();
        
        //Configurate Items
        ItemManager.config(config);
        
        //Configurate Blocks
        BlockManager.config(config);
        
        //Initialise Items
  		ItemManager.init();
  				
  		//Initialise Blocks
  		BlockManager.init();
  		
  		//Configurate LogicElements, KeyBindings, etc.
      	ConfigHandler.config(config);
        
        //Initialise Liquid Registration
      	//LiquidHandler.init();
        
        config.save();
	}
	
	
	
	//----------------------------------------------------------------------------------------
	//INIT
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerSound();
		proxy.registerKeyBinding();
		proxy.registerRendering();
		
		//Initialise World Generation
		GameRegistry.registerWorldGenerator(new WorldGenerationHandler());
		
		
		// Initialise Liquid Texture Mapping
		//MinecraftForge.EVENT_BUS.register(new LiquidHandler());
		
		// Register WorldLoading Handler
		//MinecraftForge.EVENT_BUS.register(new WorldLoadingHandler());
      	
      	//Initialise Death Handling
      	//MinecraftForge.EVENT_BUS.register(new EntityDeathHandler());
		
		proxy.registerRendering();
		
    }
	
	//----------------------------------------------------------------------------------------
	//PostINIT
	@EventHandler
    public void init(FMLPostInitializationEvent event)
    {
		// Addons
    }
	
	public static void initialiseData() {
		//DataHandler.init();
	}
}
