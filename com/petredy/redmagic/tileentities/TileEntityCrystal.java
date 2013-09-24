package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityCrystal extends TileEntity {

	public ForgeDirection direction;
	
	public int range;
	public int state;
	
	public int moved;
	
	public int tick, neededTicks = 100; 
	
	public void updateEntity(){
		if(tick > neededTicks){
			tick = 0;
			this.move();
		}
		tick++;
	}

	private void move() {
		int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(metadata != BlockIndex.CRYSTAL_LEFTOVER_METADATA && state >= 2){
			worldObj.destroyBlock(xCoord, yCoord, zCoord, false);
		}
		if(state == 1){
			this.createLeftOver();
			state++;
		}
		if(state == 0 && moved < range){
			if(direction == ForgeDirection.DOWN){
				createBlock(xCoord, yCoord - 1, zCoord, metadata);
			}
			if(direction == ForgeDirection.UP){
				createBlock(xCoord, yCoord + 1, zCoord, metadata);
			}
			if(direction == ForgeDirection.NORTH){
				createBlock(xCoord, yCoord, zCoord - 1, metadata);
			}
			if(direction == ForgeDirection.SOUTH){
				createBlock(xCoord, yCoord, zCoord + 1, metadata);
			}
			if(direction == ForgeDirection.WEST){
				createBlock(xCoord - 1, yCoord, zCoord, metadata);
			}
			if(direction == ForgeDirection.EAST){
				createBlock(xCoord + 1, yCoord, zCoord, metadata);
			}
			state++;
		}else if(moved >= range && range != 0){
			state++;
		}
		
		
	}
	
	private void createLeftOver() {
		if(direction == ForgeDirection.DOWN || direction == ForgeDirection.UP){
			createLeftoverBlock(xCoord + 1, yCoord, zCoord);
			createLeftoverBlock(xCoord - 1, yCoord, zCoord);
			createLeftoverBlock(xCoord, yCoord, zCoord + 1);
			createLeftoverBlock(xCoord, yCoord, zCoord - 1);
		}
		if(direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH){
			createLeftoverBlock(xCoord + 1, yCoord, zCoord);
			createLeftoverBlock(xCoord - 1, yCoord, zCoord);
			createLeftoverBlock(xCoord, yCoord + 1, zCoord);
			createLeftoverBlock(xCoord, yCoord - 1, zCoord);
		}
		if(direction == ForgeDirection.WEST || direction == ForgeDirection.EAST){
			createLeftoverBlock(xCoord, yCoord + 1, zCoord);
			createLeftoverBlock(xCoord, yCoord - 1, zCoord);
			createLeftoverBlock(xCoord, yCoord, zCoord + 1);
			createLeftoverBlock(xCoord, yCoord, zCoord - 1);
		}
	}

	private void createLeftoverBlock(int x, int y, int z) {
		this.setBlock(x, y, z, Blocks.crystal.blockID, BlockIndex.CRYSTAL_LEFTOVER_METADATA);
	}
	
	private void setBlock(int x, int y, int z, int id, int metadata){
		if(worldObj.getBlockId(x, y, z) != Blocks.crystal.blockID &&
		worldObj.getBlockId(x, y, z) != Block.bedrock.blockID &&
		worldObj.getBlockId(x, y, z) != Blocks.cage.blockID){
			worldObj.setBlock(x, y, z, id, metadata, 3);
		}
	}

	private void createBlock(int x, int y, int z, int metadata){
		this.setBlock(x, y, z, Blocks.crystal.blockID, metadata);
		TileEntityCrystal crystal = (TileEntityCrystal) worldObj.getBlockTileEntity(x, y, z);
		if(crystal != null){
			crystal.direction = direction;
			crystal.range = range;
			crystal.moved = moved + 1;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.direction = ForgeDirection.getOrientation(tag.getInteger("redmagic.direction"));
		this.range = tag.getInteger("redmagic.range");
		this.moved = tag.getInteger("redmagic.moved");
		this.state = tag.getInteger("redmagic.state");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("redmagic.direction", BlockUtils.forgeDirectionToInt(direction));
		tag.setInteger("redmagic.range", range);
		tag.setInteger("redmagic.moved", moved);
		tag.setInteger("redmagic.state", state);
	}
	
}
