package redmagic;


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

import redmagic.blocks.*;
import redmagic.configuration.*;
import redmagic.core.*;
import redmagic.entities.EntityRedmagicItem;
import redmagic.handlers.*;
import redmagic.helpers.LogHelper;
import redmagic.items.*;
import redmagic.lib.gods.GodManager;
import redmagic.lib.potions.RedmagicPotion;
import redmagic.network.*;

@Mod( modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
@NetworkMod(channels = { Reference.PACKET_CHANNEL_CLIENT, Reference.PACKET_CHANNEL_SERVER },clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)

public class Redmagic{

	@SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
	public static CommonProxy proxy;
	
	@Instance(Reference.MOD_NAME)
	public static Redmagic instance;
	
	public static CreativeTabs tabRedmagic = new CreativeTabRedMagic(CreativeTabs.getNextID(), Reference.MOD_NAME);
	
	public static RedmagicPotion redmagicPotion = new RedmagicPotion();
	
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
        LogHelper.init(config);
        
        //Initialise Language Handling
      	LanguageHandler.init();
      	
      	//Initialise TileEntity Registration
      	TileEntityHandler.init();
      	
      	//Initialise Path Registration
      	PathHandler.init();
      	
      	//Initialise Entity Registration
      	EntityHandler.init();
      	
      	//Initialise God Registration
      	GodHandler.init();
        
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
  		
  		//Initialise Custom Crafting Registration
      	CraftingHandler.init();
  		
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
		
		//Initialise World Generation
		GameRegistry.registerWorldGenerator(new WorldGenerationHandler());
		
		//Initialise Player Tracking
		GameRegistry.registerPlayerTracker(new PlayerHandler());
		
		//Initialise Player Interaction with EntityRedmagicItem
		MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());
		
		//Initialise God Management
		TickRegistry.registerTickHandler(new GodManager(), Side.SERVER);
		TickRegistry.registerTickHandler(new GodManager(), Side.CLIENT);
		
		//Initialise Talent Rendering
		TickRegistry.registerTickHandler(new TalentRenderHandler(), Side.CLIENT);
				
		proxy.registerRendering();
		
    }
	
	//----------------------------------------------------------------------------------------
	//PostINIT
	@EventHandler
    public void init(FMLPostInitializationEvent event)
    {
		// Addons
    }
}
