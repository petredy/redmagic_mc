package com.petredy.redmagic.forge.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WorldHelper {

	public static TileEntity getBlockTileEntity(World world, int x, int y, int z){
		return world.func_147438_o(x, y, z);
	}
	
}
