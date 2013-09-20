package com.petredy.redmagic.utils;

import java.util.List;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.petredy.redmagic.entities.EntitySoul;

public class SoulUtils {

	public static final int RANGE = 30;
	
	public static EntitySoul getNextSoul(World worldObj, int x, int y, int z) {
		if(worldObj != null){
			List<EntitySoul> souls = worldObj.getEntitiesWithinAABB(EntitySoul.class, AxisAlignedBB.getBoundingBox(x - RANGE, y - RANGE, z - RANGE, x + RANGE, y + RANGE, z + RANGE));
			if(souls != null && souls.size() > 0){
				return souls.get(0);
			}
		}
		return null;
	}

}
