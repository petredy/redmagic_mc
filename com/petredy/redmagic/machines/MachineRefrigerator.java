package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineRefrigerator extends Machine{
	
	public int baseID, coolingID, topID;
	
	public int tick = 0;
	public int neededTicks = 1000;
	public ItemStack freezing;
	
	public MachineRefrigerator(){
		this.metadata = Machines.REFRIGERATOR_METADATA;
		this.size = 6;
		this.baseID = Block.blockIron.blockID;
		this.coolingID = Block.ice.blockID;
		this.topID = this.baseID;
	}
	
	public boolean canPlacedOnSide(int side){
		return side != 0 && side != 1;
	}
	
	public void update(IMachineHandler handler) {
		if(tick++ >= neededTicks && !handler.getWorld().isRemote){
			tick = 0;
			if(this.isBuilt(handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord())){
				ItemStack stack = this.getFreezingItem(handler.getWorld(), handler.getXCoord(), handler.getYCoord() + 1, handler.getZCoord());
				if(freezing == null){
					if(stack != null && stack.itemID > 0){
						freezing = stack.copy();
					}
				}else{
					if(stack != null && stack.itemID > 0 && freezing.itemID == stack.itemID){
						this.freeze(handler.getWorld(), handler.getXCoord(), handler.getYCoord() + 1, handler.getZCoord());
					}else{
						freezing = null;
					}
				}
			}
		}else if(tick >= neededTicks && handler.getWorld().isRemote)tick = 0;
	}
	
	private void freeze(World world, int xCoord, int yCoord, int zCoord) {
		ItemStack stack = null;
		if(freezing.itemID == Block.waterMoving.blockID || freezing.itemID == Block.waterStill.blockID){
			stack = new ItemStack(Block.ice);
		}
		if(stack != null){
			switch(ForgeDirection.OPPOSITES[side]){
				case 2: {
					this.setFreezingBlock(world, xCoord, yCoord, zCoord - 1, stack.itemID, stack.getItemDamage());
					break;
				}
				case 3: {
					this.setFreezingBlock(world, xCoord, yCoord, zCoord + 1, stack.itemID, stack.getItemDamage());
					break;
				}
				case 4: {
					this.setFreezingBlock(world, xCoord - 1, yCoord, zCoord, stack.itemID, stack.getItemDamage());
					break;
				}
				case 5: {
					this.setFreezingBlock(world, xCoord + 1, yCoord, zCoord, stack.itemID, stack.getItemDamage());
					break;
				}
			}
		}else{
			switch(ForgeDirection.OPPOSITES[side]){
				case 2: {
					this.destroyFreezingBlockAndTransferTo(world, xCoord, yCoord, zCoord - 1, xCoord, yCoord - 1, zCoord + 1);
					break;
				}
				case 3: {
					this.destroyFreezingBlockAndTransferTo(world, xCoord, yCoord, zCoord + 1, xCoord, yCoord - 1, zCoord - 1);
					break;
				}
				case 4: {
					this.destroyFreezingBlockAndTransferTo(world, xCoord - 1, yCoord, zCoord, xCoord + 1, yCoord - 1, zCoord);
					break;
				}
				case 5: {
					this.destroyFreezingBlockAndTransferTo(world, xCoord + 1, yCoord, zCoord, xCoord - 1, yCoord - 1, zCoord);
					break;
				}
			}
		}
	}

	private void destroyFreezingBlockAndTransferTo(World world, int xCoord, int yCoord, int zCoord, int chestX, int chestY, int chestZ) {
		ItemStack stack = this.getFreezingItemAt(world, xCoord, yCoord, zCoord);
		world.setBlockToAir(xCoord, yCoord, zCoord);
		if(stack != null){
			TileEntity entity = world.getBlockTileEntity(chestX, chestY, chestZ);
			if(entity instanceof IInventory){
				ItemStack drop = InventoryUtils.addItemStackToInventory((IInventory)entity, stack);
				if(drop != null)InventoryUtils.dropItemStack(drop, world, chestX, chestY, chestZ);
			}
		}
		
	}

	private void setFreezingBlock(World world, int xCoord, int yCoord, int zCoord, int itemID, int itemDamage) {
		world.setBlock(xCoord, yCoord, zCoord, itemID, itemDamage, 3);
	}
	
	

	private ItemStack getFreezingItem(World world, int x, int y, int z) {
		switch(ForgeDirection.OPPOSITES[side]){
		case 2: return this.getFreezingItemAt(world, x, y, z - 1);
		case 3: return this.getFreezingItemAt(world, x, y, z + 1);
		case 4: return this.getFreezingItemAt(world, x - 1, y, z);
		case 5: return this.getFreezingItemAt(world, x + 1, y, z);
		default: return null;
		}
	}

	private ItemStack getFreezingItemAt(World world, int x, int y, int z) {
		return new ItemStack(world.getBlockId(x, y, z), 1, world.getBlockMetadata(x, y, z));
	}

	private boolean isBuilt(World world, int x, int y, int z) {
		switch(ForgeDirection.OPPOSITES[side]){
		case 2: return isBuilt(world, -1,-2, 1,0, 0,-1, x,y,z);
		case 3: return isBuilt(world, -1,0, 1,2, 0,1, x,y,z);
		case 4: return isBuilt(world, -2,-1, 0,1, -1,0, x,y,z);
		case 5: return isBuilt(world, 1,0, 2,1, 1,0, x,y,z);
		}
		return false;
	}

	private boolean isBuilt(World world, int startX, int startZ, int endX, int endZ, int holeX, int holeZ, int xCoord, int yCoord, int zCoord) {
		for(int y = 0; y <= 2; y++){
			for(int x = startX; x <= endX; x++){
				for(int z = startZ; z <= endZ; z++){
					if(y == 0){
						if(!(x == 0 && z == 0) && world.getBlockId(xCoord + x, yCoord + y, zCoord + z) != baseID){
							return false;
						}
					}else if(!(x == holeX && z == holeZ)){
						if(y == 1){
							if(world.getBlockId(xCoord + x, yCoord + y, zCoord + z) != coolingID){
								return false;
							}
						}else if(y == 2){
							if(world.getBlockId(xCoord + x, yCoord + y, zCoord + z) != topID){
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}
