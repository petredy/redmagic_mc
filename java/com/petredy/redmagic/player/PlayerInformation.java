package com.petredy.redmagic.player;

import com.petredy.redmagic.utils.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;

public class PlayerInformation {

	public static final String TOKEN_PREFIX = "redmagic.inforamtion.";
	public EntityPlayer player;
	public InventoryBasic inventory;
	
	public PlayerInformation(){
		this.inventory = new InventoryBasic("redmagic.player.inventory", false, 54);
	}
	
	public PlayerInformation(EntityPlayer player){
		this();
		this.player = player;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public static PlayerInformation loadFromNBT(NBTTagCompound tag){
		PlayerInformation information = new PlayerInformation();
		if(tag != null)information.readFromNBT(tag);
		return information;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		InventoryUtils.writeToNBT(inventory, tag);
	}
	
}
