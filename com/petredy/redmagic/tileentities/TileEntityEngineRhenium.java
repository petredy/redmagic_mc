/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.redvalue.element.Composition;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.transport.IPipeTile.PipeType;

public class TileEntityEngineRhenium extends TileEntityEngine {

	public float power = 300;
	public float costs = 10;
	
	public Composition cost = Composition.getStandard(0, 0, 0, 300, 0);
	
}
