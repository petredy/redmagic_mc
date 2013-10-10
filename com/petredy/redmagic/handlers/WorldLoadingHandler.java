package com.petredy.redmagic.handlers;

import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.utils.PlayerUtils;
import com.petredy.redmagic.utils.RedenergyUtils;
import com.petredy.redmagic.utils.TradingUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.event.world.WorldEvent.Unload;

public class WorldLoadingHandler {

	@ForgeSubscribe
	public void onLoad(Load evt){
		TradingUtils.load(evt.world);
		RedenergyUtils.load(evt.world);
		KnowledgeSystem.load(evt.world);
	}
	
	@ForgeSubscribe
	public void onUnload(Unload evt){
		TradingUtils.save(evt.world);
		RedenergyUtils.save(evt.world);
		KnowledgeSystem.save(evt.world);
	}
	
	@ForgeSubscribe
	public void onSave(Save evt){
		TradingUtils.save(evt.world);
		RedenergyUtils.save(evt.world);
		KnowledgeSystem.save(evt.world);
	}
	
	
}
