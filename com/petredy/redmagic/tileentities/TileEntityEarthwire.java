package com.petredy.redmagic.tileentities;

import java.util.Random;

import com.petredy.redmagic.api.redenergy.IEnergyConsumer;
import com.petredy.redmagic.api.redenergy.ILightningConsumer;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redhole.Hole;
import com.petredy.redmagic.utils.EnvironmentUtils;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEarthwire extends TileEntity implements IEnergyConsumer{
	
	public Hole hole;
	public static final int wireID = Block.fenceIron.blockID;
	public boolean loaded;
	
	public int range = 30;
	
	
	
	public void updateEntity(){
		if(!this.loaded){
			EnergyMap.registerConsumer(xCoord, yCoord, zCoord, range);
			loaded = true;
		}
	}

	private void createHole() {
		EnergyMap.removeConsumer(xCoord, yCoord, zCoord);
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.hole.blockID, hole.id, 3);
	}



	private void destroyWire() {
		for(int k = yCoord + 1; k < 256; k++){
			for(int j = zCoord - 1; j <= zCoord + 1; j++){
				for(int i = xCoord - 1; i <= xCoord + 1; i++){
					if(worldObj.getBlockId(i, k, j) == wireID){
						worldObj.destroyBlock(i, k, j, false);
					}
				}
			}
		}
		
	}

	private int hasWire() {
		int layers = 0;
		int air = EnvironmentUtils.containedBlocksInArea(worldObj, xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 1, yCoord + 50, zCoord + 1, 0, 0);
		if(air > 2 * 50){
			if(this.hasBase()){
				layers = this.getLayers();
				return layers;
			}
		}
		return layers;
	}



	private int getLayers() {
		int y = 1;
		int count = 0;
		while(count <= 50 && yCoord + y <= 256 && worldObj.getBlockId(xCoord, yCoord + y++, zCoord) == wireID){
			count++;
		}
		return count;
	}



	private boolean hasBase() {
		return worldObj.getBlockId(xCoord + 1, yCoord + 1, zCoord) == wireID &&
				worldObj.getBlockId(xCoord - 1, yCoord + 1, zCoord) == wireID &&
				worldObj.getBlockId(xCoord, yCoord + 1, zCoord + 1) == wireID &&
				worldObj.getBlockId(xCoord, yCoord + 1, zCoord - 1) ==wireID;
	}



	@Override
	public void consume() {
		if(worldObj.isRaining() && !worldObj.isRemote){
			ItemStack stack = EnvironmentUtils.surroundedOnLayerZByBlock(worldObj, xCoord, yCoord, zCoord);
			if(stack != null){
				int layers = this.hasWire();
				hole = Hole.getHoleByNeededBlock(stack);
				if(hole != null){
					if(hole.getMaxHeight() >= yCoord && stack.isItemEqual(hole.getSurroundingBlock())){
						if(layers > hole.getMinLayers())
							this.destroyWire();
							this.createHole();
						}
				}else if(stack.isItemEqual(new ItemStack(Block.obsidian))){
					this.useEnergy(layers);
				}
			}
		}
	}



	private void useEnergy(int layers) {
		TileEntity entity = worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);
		if(entity instanceof ILightningConsumer){
			((ILightningConsumer)entity).consume();
		}
	}
}
