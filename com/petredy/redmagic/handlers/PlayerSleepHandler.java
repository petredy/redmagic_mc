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
		if(Reference.SLEEP && !event.entityPlayer.worldObj.isRemote){
			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; j++){
	            WorldServer server = MinecraftServer.getServer().worldServers[j];
	            if(!server.isDaytime() || Reference.SLEEP_ALWAYS)server.setWorldTime(0);
	        }
		}
	}
}
