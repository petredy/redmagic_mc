package com.petredy.redmagic.forge.helper;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockHelper {

	public static boolean setBlockToAir(World world, int x, int y, int z){
		return world.func_147468_f(x, y, z);
	}
	
	public static Block getBlock(World world, int x, int y, int z){
		return world.func_147439_a(x, y, z);
	}
	
}
