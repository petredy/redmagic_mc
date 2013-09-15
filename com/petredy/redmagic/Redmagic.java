package com.petredy.redmagic;


import java.io.File;

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
import com.petredy.redmagic.handlers.ConfigHandler;
import com.petredy.redmagic.handlers.EntityHandler;
import com.petredy.redmagic.handlers.LanguageHandler;
import com.petredy.redmagic.handlers.PlayerSleepHandler;
import com.petredy.redmagic.handlers.PlayerTracker;
import com.petredy.redmagic.handlers.RedvalueConfigurationHandler;
import com.petredy.redmagic.handlers.ServerTickHandler;
import com.petredy.redmagic.handlers.TileEntityHandler;
import com.petredy.redmagic.handlers.WorldGenerationHandler;
import com.petredy.redmagic.handlers.WorldLoadingHandler;
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
      	
      	//Initialise Entity Registration
      	EntityHandler.init();
      	
      	//Initilase Redvalue configuration
      	RedvalueConfigurationHandler.init();
      	
      	//Initialise block configuration
      	Blocks.config(config);
      	
      	//Initialise item configuration
      	Items.config(config);
      	
      	//Initialise block registration
      	Blocks.init();
      	
      	//Initialise item registration
      	Items.init();
      	
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
		
		//Register Multi Structure ticking
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		
		//Register World Generation
		GameRegistry.registerWorldGenerator(new WorldGenerationHandler());
		
		//Register Player loading/saving
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		
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
