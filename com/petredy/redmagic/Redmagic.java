package com.petredy.redmagic;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.core.CommonProxy;
import com.petredy.redmagic.core.CreativeTabRedMagic;
import com.petredy.redmagic.handlers.*;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.*;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.LogUtils;
@Mod( modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(channels = { Reference.PACKET_CHANNEL },clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)

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

        //Initialise Logger
        LogUtils.init(config);
        
        //Initialise Language Handling
      	LanguageHandler.init();
      	
      	//Initialise TileEntity registration
      	TileEntityHandler.init();
      	
      	//Intialise Dimension registration
      	DimensionHandler.init();
      	
      	//Initialise Entity registration
      	EntityHandler.init();
      	
      	//Initilase Redvalue configuration
      	RedvalueConfigurationHandler.init();
      	
      	//Initialise item configuration
      	Items.config(config);
      	
      	//Initialise block configuration
      	Blocks.config(config);
      	
      	//Initialise item registration
      	Items.init();
      	
      	//Initialise block registration
      	Blocks.init();
      	
      	//Initialise configuration
        ConfigHandler.config(config);
      	
        //Register GuiHandler
        NetworkRegistry.instance().registerGuiHandler(instance, this.proxy);
        
        //Register Rendering IDs
        proxy.initRendering();
  		
  		//Configurate LogicElements, KeyBindings, etc.
      	ConfigHandler.config(config);
        
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
		
		//Register TradingSystem loading/saving
		MinecraftForge.EVENT_BUS.register(new WorldLoadingHandler());
		
		//Register Instant Sleep Ability
		MinecraftForge.EVENT_BUS.register(new PlayerSleepHandler());
		
		//Register World Generation
		GameRegistry.registerWorldGenerator(new WorldGenerationHandler());
		
		//Register Player loading/saving
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		
		TickRegistry.registerTickHandler(new RenderHandler(), Side.CLIENT);
		
    }
	
	//----------------------------------------------------------------------------------------
	//PostINIT
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		// Addons
		RedvalueDictionary.initialise();
    }
}
