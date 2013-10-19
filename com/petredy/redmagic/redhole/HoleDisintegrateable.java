package com.petredy.redmagic.redhole;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Elements;
import com.petredy.redmagic.lib.Redholes;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.ItemUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class HoleDisintegrateable extends Hole{

	public Composition energy = Composition.getStandard(0, 0, 0, 0, 0);
	
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
		EnergyMap.releaseEnergy(new RedEnergy(world.provider.dimensionId,(int)(player.posX / 16), (int)(player.posZ / 16), energy));
	}
	
	public void update(ItemStack redhole, World world, Entity user, int idk, boolean idk2) {
		energy.decreaseValue(Elements.EARTH, 0.01f);
		energy.decreaseValue(Elements.NATURE, 0.01f);
		energy.decreaseValue(Elements.WATER, 0.01f);
		energy.decreaseValue(Elements.FIRE, 0.01f);
		energy.decreaseValue(Elements.METAL, 0.01f);
	}

	public void keyPressed(ItemStack redhole, EntityPlayer player, String keyName) {
		MovingObjectPosition location = ItemUtils.getMovingObjectPosition(player.worldObj, player, false);
		if(location != null){
			if(player.worldObj.getBlockTileEntity(location.blockX, location.blockY, location.blockZ) == null){
				ItemStack target = new ItemStack(player.worldObj.getBlockId(location.blockX, location.blockY, location.blockZ), 1, player.worldObj.getBlockMetadata(location.blockX, location.blockY, location.blockZ));
				if(target != null){
					Composition value = RedvalueDictionary.getComposition(target);
					player.worldObj.destroyBlock(location.blockX, location.blockY, location.blockZ, false);
					energy.merge(value);
					player.swingItem();
				}
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.energy = Composition.loadFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		this.energy.writeToNBT(tag);
	}
	
	public void addInformation(ItemStack redhole, EntityPlayer player, List list) {
		list.add("Stored Reds: " + energy);
	}
	
}
