package com.petredy.redmagic.redhole;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Redholes;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.utils.ItemUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class HoleDisintegrateable extends Hole{

	public float energy;
	
	public final int MAX_ENERGY = 2000;
	
	public HoleDisintegrateable(){
		name = Redholes.DISINTEGRATEABLE_NAME;
		id = Redholes.DISINTEGRATEABLE_ID;
	}
	
	public ItemStack getSurroundingBlock(){
		return new ItemStack(Blocks.decoration, 1, BlockIndex.RHENIUM_BLOCK_METADATA);
	}
	
	public String getHoleColor(){
		return "0.85#0.85#0.85";
	}
	
	public int getMaxHeight(){
		return 32;
	}
	
	public void activate(ItemStack stack, World world, EntityPlayer player){
		energy -= EnergyMap.releaseEnergy(new RedEnergy(world.provider.dimensionId,(int)(player.posX / 16), (int)(player.posZ / 16), energy));
	}
	
	public void update(ItemStack redhole, World world, Entity user, int idk, boolean idk2) {
		energy -= 0.01f;
		if(energy < 0)energy = 0;
	}

	public void keyPressed(ItemStack redhole, EntityPlayer player, String keyName) {
		MovingObjectPosition location = ItemUtils.getMovingObjectPosition(player.worldObj, player, false);
		if(location != null){
			if(player.worldObj.getBlockTileEntity(location.blockX, location.blockY, location.blockZ) == null){
				ItemStack target = new ItemStack(player.worldObj.getBlockId(location.blockX, location.blockY, location.blockZ), 1, player.worldObj.getBlockMetadata(location.blockX, location.blockY, location.blockZ));
				if(target != null){
					float value = RedvalueDictionary.getRedvalue(target);
					if(value > 0 && energy + value / 2 <= MAX_ENERGY){
						player.worldObj.destroyBlock(location.blockX, location.blockY, location.blockZ, false);
						energy += value / 2;
						player.swingItem();
					}
				}
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.energy = tag.getFloat("energy");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setFloat("energy", energy);
	}
	
	public void addInformation(ItemStack redhole, EntityPlayer player, List list) {
		list.add("Stored Reds: " + energy);
	}
	
}
