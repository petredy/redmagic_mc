package com.petredy.redmagic.redhole;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.dimension.TeleporterOverworld;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Redholes;

public class HolePortable extends Hole{
	
	public HolePortable(){
		name = Redholes.PORTABLE_NAME;
		id = Redholes.PORTABLE_ID;
	}
	
	public void activate(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote){
			EntityPlayerMP thePlayer = (EntityPlayerMP)player;
			MinecraftServer server = MinecraftServer.getServer();
			thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterOverworld(server.worldServerForDimension(0)));
		}
	}
	
	public ItemStack getSurroundingBlock(){
		return new ItemStack(Block.glowStone);
	}
	
	public String getHoleColor(){
		return "0.25#0.65#0.32";
	}
	
}
