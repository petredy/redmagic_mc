package com.petredy.redmagic.redhole;

import java.util.HashMap;

import com.petredy.redmagic.lib.Redholes;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Hole {
	
	public static HashMap<Integer, Hole> holes = new HashMap<Integer, Hole>();
	
	public static void register(Hole hole){
		holes.put(hole.id, hole);
	}
	
	public static Hole getHole(Integer id){
		return holes.get(id);
	}
	
	public static Hole getHoleByNeededBlock(ItemStack stack) {
		for(Hole hole: holes.values()){
			if(hole.getSurroundingBlock().isItemEqual(stack))return hole;
		}
		return null;
	}
	
	static{
		register(new HoleStoreable());
	}
	
	public String name;
	public int id;
	
	public void activate(ItemStack stack, World world, EntityPlayer player){
		
	}
	
	public ItemStack getSurroundingBlock(){
		return new ItemStack(0, 1, 0);
	}
	
	public String getHoleColor(){
		return "0#0#0";
	}
	
	public int getMaxHeight(){
		return 256;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.id = tag.getInteger("id");
	}
	
	public static Hole loadFromNBT(NBTTagCompound tag){
		Hole mod = null;
		try{
			int id = tag.getInteger("id");
			mod = (Hole) Redholes.HOLES[id].newInstance();
			mod.readFromNBT(tag);
		}catch(Exception e){
			//e.printStackTrace();
		}
		return mod;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("id", id);
	}
	
}
