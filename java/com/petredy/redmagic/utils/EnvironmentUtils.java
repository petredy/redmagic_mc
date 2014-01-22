package com.petredy.redmagic.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnvironmentUtils {

	
	public static int containedBlocksInArea(World world, int minx, int miny, int minz, int maxx, int maxy, int maxz, int blockID, int metadata){
		int count = 0;
		for(int i = minx; i < maxx; i++){
			for(int j = miny; j < maxy; j++){
				for(int k = minz; k < maxz; k++){
					if(world.getBlockId(i, j, k) == blockID && world.getBlockMetadata(i, j, k) == metadata)count++; 
				}
			}
		}
		return count;
	}

	public static ItemStack surroundedOnLayerZByBlock(World worldObj, int xCoord, int yCoord, int zCoord) {
		ItemStack[] blocks = new ItemStack[8];
		int count = 0;
		for(int i = - 1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(i != 0 || j != 0){
					blocks[count++] = new ItemStack(worldObj.getBlockId(xCoord + i, yCoord, zCoord + j), 1, worldObj.getBlockMetadata(xCoord + i, yCoord, zCoord + j));
				}
			}
		}
		ItemStack last = blocks[0];
		for(ItemStack stack: blocks){
			if(last != null && stack != null && !last.isItemEqual(stack))return null;
			if(last == null || stack == null)return null;
			last = stack;
		}
		return last;
	}
	
}
