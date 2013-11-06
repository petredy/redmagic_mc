package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.machinery.Tribological;

public class MachineContactCooling extends Machine{
	
	public static final float cooling = 0.15f;
	
	public MachineContactCooling(){
		this.metadata = Machines.CONTACT_COOLING_METADATA;
		this.size = 1;
		this.name = Machines.CONTACT_COOLING_NAME;
		this.tribological = new Tribological(new ItemStack[]{
			new ItemStack(Items.plateRhenium), new ItemStack(Items.coolingDevice), new ItemStack(Items.plateRhenium),
			new ItemStack(Items.coolingDevice), new ItemStack(Items.coolingDevice), new ItemStack(Items.coolingDevice),
			new ItemStack(Items.plateRhenium), new ItemStack(Items.coolingDevice), new ItemStack(Items.plateRhenium)
		});
	}
	
	public void update(IMachineHandler handler) {
		if(tribological.getStatus() > 0 && handler.getHeat() > 0 && isAttachedBlockWater(handler.getWorld(), this.side, handler.getXCoord(), handler.getYCoord(), handler.getZCoord())){
			handler.setHeat(handler.getHeat() - (cooling * tribological.getStatus() / 100));
		}
	}
	
	public boolean canPlacedOnSide(int side, int size){
		return size == 1;
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
