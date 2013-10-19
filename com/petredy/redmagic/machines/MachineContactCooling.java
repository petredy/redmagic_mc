package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;

public class MachineContactCooling extends Machine{
	
	public static final float cooling = 0.15f;
	
	public MachineContactCooling(){
		this.metadata = Machines.CONTACT_COOLING_METADATA;
		this.size = 1;
	}
	
	public void update(IMachineHandler handler) {
		if(handler.getHeat() > 0 && isAttachedBlockWater(handler.getWorld(), this.side, handler.getXCoord(), handler.getYCoord(), handler.getZCoord())){
			handler.setHeat(handler.getHeat() - cooling);
		}
	}

	private boolean isAttachedBlockWater(World world, int side, int xCoord, int yCoord, int zCoord) {
		switch(side){
		case 0: return world.getBlockId(xCoord, yCoord - 1, zCoord) == Block.waterStill.blockID;
		case 1: return world.getBlockId(xCoord, yCoord + 1, zCoord) == Block.waterStill.blockID;
		case 2: return world.getBlockId(xCoord, yCoord, zCoord - 1) == Block.waterStill.blockID;
		case 3: return world.getBlockId(xCoord, yCoord, zCoord + 1) == Block.waterStill.blockID;
		case 4: return world.getBlockId(xCoord - 1, yCoord, zCoord) == Block.waterStill.blockID;
		case 5: return world.getBlockId(xCoord + 1, yCoord, zCoord) == Block.waterStill.blockID;
		default: return false;
		}
	}
}
