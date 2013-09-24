package com.petredy.redmagic.redhole;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Redholes;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HoleStoreable extends Hole{
	
	public HoleStoreable(){
		name = Redholes.STOREABLE_NAME;
		id = Redholes.STOREABLE_ID;
	}
	
	public void activate(ItemStack stack, World world, EntityPlayer player){
		player.openGui(Redmagic.instance, Guis.PLAYER_INVENTORY, world, 0, 0, 0);
	}
	
	public ItemStack getSurroundingBlock(){
		return new ItemStack(Block.wood);
	}
	
	public String getHoleColor(){
		return "0.65#0.36#0.42";
	}
	
}
