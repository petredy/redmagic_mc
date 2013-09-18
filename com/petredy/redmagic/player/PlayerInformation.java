package com.petredy.redmagic.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerInformation {

	public static final String TOKEN_PREFIX = "feature.manager.";
	public EntityPlayer player;
	
	public int dimensionID;
	
	public PlayerInformation(EntityPlayer player){
		this.player = player;
	}
	
	public PlayerInformation(){
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.dimensionID = tag.getInteger("dimension");
	}
	
	public static PlayerInformation loadFromNBT(NBTTagCompound tag){
		PlayerInformation information = new PlayerInformation();
		if(tag != null)information.readFromNBT(tag);
		return information;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("dimension", dimensionID);
	}
	
}
