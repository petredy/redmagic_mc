package com.petredy.redmagic.tileentities;

import java.util.Random;

import com.petredy.redmagic.blocks.Blocks;
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

public class TileEntityEarthwire extends TileEntity {
	
	public Hole hole;
	public static final int wireID = Block.fenceIron.blockID;
	
	
	
	public void updateEntity(){
		if(worldObj.isRaining() && !worldObj.isRemote){
			ItemStack stack = EnvironmentUtils.surroundedOnLayerZByBlock(worldObj, xCoord, yCoord, zCoord);
			if(stack != null){
				hole = Hole.getHoleByNeededBlock(stack);
				if(hole != null && hole.getMaxHeight() >= yCoord && stack.isItemEqual(hole.getSurroundingBlock())){
					int layers = this.hasWire();
					if(layers > 1){
						this.createLightning(layers, zCoord);
					}
				}
			}
		}
	}



	private void createLightning(int layers, int height) {
		Random rand = new Random();
		if(rand.nextInt(1000) < layers){
			int x = rand.nextInt(256 - height) * (rand.nextFloat() < 0.5 ? -1 : 1);
			int y = rand.nextInt(256 - height) * (rand.nextFloat() < 0.5 ? -1 : 1);
			int z = rand.nextInt(256 - height) * (rand.nextFloat() < 0.5 ? -1 : 1);
			if(x >= -10 && x <= 10 && z >= -10 && y <= 10){
				this.onHit();
				x = 0;
				z = 0;
				y = yCoord;
			}
			EntityLightningBolt bolt = new EntityLightningBolt(worldObj, xCoord + x, y, zCoord + z);
			worldObj.addWeatherEffect(bolt);
			
		}
	}



	private void onHit() {
		this.destroyWire();
		this.createHole();
	}



	private void createHole() {
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
}
