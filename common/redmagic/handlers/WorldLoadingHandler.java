package redmagic.handlers;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import redmagic.Redmagic;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.lib.bank.BankData;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.event.world.WorldEvent.Unload;

public class WorldLoadingHandler {

	@ForgeSubscribe
	public void onLoad(Load event){
		
		if(!Redmagic.loaded){
			Logger.log("Load World BankData!");
			File saves = new File(Minecraft.getMinecraftDir().getAbsolutePath(), Reference.SAVE_DIR);
			File world = new File(saves, FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName());
			File redbank = new File(world, Reference.MOD_ID);
			Redmagic.data = new Configuration(new File(redbank, Reference.WORLD_STORAGE_FILE));
			Redmagic.data.load();
			Redmagic.initialiseData();
			Redmagic.bankData = new BankData();
			Redmagic.bankData.load(Redmagic.data);
			Redmagic.loaded = true;
		}
	}
	
	
	@ForgeSubscribe
	public void onSave(Save event){
		if(Redmagic.saved + Reference.SAVE_INTERVAL < new Date().getTime()){
			Logger.log("Save World BankData!");
			File saves = new File(Minecraft.getMinecraftDir().getAbsolutePath(), Reference.SAVE_DIR);
			File world = new File(saves, FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName());
			File redmagic = new File(world, Reference.MOD_ID);
			File dataFile = new File(redmagic, Reference.WORLD_STORAGE_FILE);
			boolean delete = dataFile.delete();
			if(!delete)Logger.log("The BankData was probably not saved right. Can't remove the old configuration file.");
			Redmagic.data = new Configuration(dataFile);
			Redmagic.data.load();
			Redmagic.bankData.save(Redmagic.data);
			Redmagic.data.save();
			Redmagic.saved = new Date().getTime();
		}
	}
	
	@ForgeSubscribe
	public void onLoad(Unload event){
		if(Redmagic.saved + Reference.SAVE_INTERVAL < new Date().getTime()){
			Logger.log("Save World BankData!");
			File saves = new File(Minecraft.getMinecraftDir().getAbsolutePath(), Reference.SAVE_DIR);
			File world = new File(saves, FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName());
			File redmagic = new File(world, Reference.MOD_ID);
			File dataFile = new File(redmagic, Reference.WORLD_STORAGE_FILE);
			boolean delete = dataFile.delete();
			if(!delete)Logger.log("The BankData was probably not saved right. Can't remove the old configuration file.");
			Redmagic.data = new Configuration(dataFile);
			Redmagic.data.load();
			Redmagic.bankData.save(Redmagic.data);
			Redmagic.data.save();
			Redmagic.saved = new Date().getTime();
		}
	}
	
}
