package com.petredy.redmagic.handlers;

import com.petredy.redmagic.utils.TradingUtils;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.event.world.WorldEvent.Unload;

public class WorldLoadingHandler {

	@ForgeSubscribe
	public void onLoad(Load evt){
		World world = evt.world;
		TradingUtils.load(world);
	}
	
	@ForgeSubscribe
	public void onUnload(Unload evt){
		TradingUtils.save(evt.world);
	}
	
	@ForgeSubscribe
	public void onSave(Save evt){
		TradingUtils.save(evt.world);
	}
	
	
}
