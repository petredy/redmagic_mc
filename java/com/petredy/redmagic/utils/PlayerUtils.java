package com.petredy.redmagic.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.DimensionManager;
import com.petredy.redmagic.player.PlayerInformation;

public class PlayerUtils {

	public static PlayerInformation getPlayerInformation(EntityPlayer player){
		PlayerInformation info = PlayerInformation.loadFromNBT(WorldSavedDataUtils.loadData(player.worldObj, PlayerInformation.TOKEN_PREFIX + player.username));
		if(info != null)info.player = player;
		return info;
	}
	
	public static void setPlayerInformation(EntityPlayer player, PlayerInformation information){
		NBTTagCompound tag = new NBTTagCompound();
		information.writeToNBT(tag);
		WorldSavedDataUtils.saveData(player.worldObj, PlayerInformation.TOKEN_PREFIX + player.username, tag);
	}
	
	public static PlayerInformation load(EntityPlayer player){
		PlayerInformation info = getPlayerInformation(player);
		if(info == null){
			return new PlayerInformation(player);
		}
		return info;
	}
	
	
	
}
