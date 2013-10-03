package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;

public class MachineContactCooling extends Machine{
	
	public static final float cooling = 0.15f;
	
	public MachineContactCooling(){
		this.metadata = BlockIndex.MACHINE_CONTACT_COOLING_METADATA;
	}
	
	public void update(IMachineHandler machineHandler) {
		if(isAttackedBlockWater(machineHandler.getWorld(), this.side, machineHandler.getXCoord(), machineHandler.getYCoord(), machineHandler.getZCoord())){
			machineHandler.setHeat(machineHandler.getHeat() - cooling);
		}
	}

	private boolean isAttackedBlockWater(World world, int side, int xCoord, int yCoord, int zCoord) {
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
