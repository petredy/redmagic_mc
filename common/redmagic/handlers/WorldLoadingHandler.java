package redmagic.handlers;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import redmagic.Redmagic;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.lib.bank.BankData;
import redmagic.lib.bank.BankSavedData;
import redmagic.network.PacketBankSync;
import redmagic.network.PacketHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

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
			
			BankSavedData data = (BankSavedData) event.world.loadItemData(BankSavedData.class, Reference.BANK_TOKEN);
			if(data == null){
				Logger.log("BankData couldn't be loaded!");
				data = new BankSavedData(Reference.BANK_TOKEN);
				data.data = new BankData();
				if(data.data.getItems().size() == 0)DataHandler.loadDefault();
				event.world.setItemData(Reference.BANK_TOKEN, data);
			}
			Redmagic.bankData = data.data;
			Redmagic.initialiseData();
			Redmagic.loaded = true;
			data.markDirty();
		}
	}
	
	
	@ForgeSubscribe
	public void onSave(Save event){
		if(Redmagic.saved + Reference.SAVE_INTERVAL < new Date().getTime()){
			Logger.log("Save World BankData!");
			BankSavedData data = (BankSavedData) event.world.loadItemData(BankSavedData.class, Reference.BANK_TOKEN);
			if(data == null){
				data = new BankSavedData(Reference.BANK_TOKEN);
				data.data = new BankData();
				if(data.data.getItems().size() == 0)DataHandler.loadDefault();
				event.world.setItemData(Reference.BANK_TOKEN, data);
			}
			data.markDirty();
			PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketBankSync(data.data)));
			Redmagic.saved = new Date().getTime();
		}
	}
	
	@ForgeSubscribe
	public void onUnload(Unload event){
		if(Redmagic.saved + Reference.SAVE_INTERVAL < new Date().getTime()){
			Logger.log("Save World BankData!");
			BankSavedData data = (BankSavedData) event.world.loadItemData(BankSavedData.class, Reference.BANK_TOKEN);
			if(data == null){
				data = new BankSavedData(Reference.BANK_TOKEN);
				data.data = new BankData();
				if(data.data.getItems().size() == 0)DataHandler.loadDefault();
				event.world.setItemData(Reference.BANK_TOKEN, data);
			}
			data.markDirty();
			Redmagic.saved = new Date().getTime();
		}
	}
	
}
