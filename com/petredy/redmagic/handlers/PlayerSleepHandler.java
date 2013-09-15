package com.petredy.redmagic.handlers;

import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class PlayerSleepHandler {

	
	@ForgeSubscribe
	public void onPlayerSleeps(PlayerSleepInBedEvent event){
		EntityPlayer player = event.entityPlayer;
		if(Reference.SLEEP && !player.worldObj.isDaytime()){
			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j){
	            WorldServer worldserver = MinecraftServer.getServer().worldServers[j];
	            worldserver.setWorldTime(0);
	        }
		}
	}
}
