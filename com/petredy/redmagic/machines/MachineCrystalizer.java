package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Elements;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineCrystalizer extends Machine{

	public InventoryBasic inventory;
	
	public static float PRODUCTION_ICE = 2;
	public static float PRODUCTION_LAVA = 5;
	public static float MOVING_MODIFIER = 1f / 7f;
	
	public int tick = 0;
	public int neededTicks = 100;
	
	public int energy;
	
	public MachineCrystalizer(){
		metadata = Machines.CRYSTALIZER_METADATA;
		size = 1;
		this.inventory = new InventoryBasic(Machines.CRYSTALIZER_NAME, false, 9);
	}
	
	public void update(IMachineHandler handler) {
		if(tick++  >= neededTicks){
			tick = 0;
			LogUtils.log("tick");
			for(int x = -1; x <= 1; x++){
				for(int z = -1; z <= 1; z++){
					for(int y = -1; y <= 2; y++){
						handleBlockAt(handler, handler.getXCoord() + x, handler.getYCoord() + y, handler.getZCoord() + z);
					}
				}
			}
		}
	}
	
	private void handleBlockAt(IMachineHandler handler, int x, int y, int z) {
		int id = handler.getWorld().getBlockId(x, y, z);
		if(id == Block.waterStill.blockID){
			handleStillWater(handler, x, y, z);
		}else if(id == Block.waterMoving.blockID){
			handleMovingWater(handler, x, y, z);
		}else if(id == Block.lavaMoving.blockID){
			handleMovingLava(handler, x, y, z);
		}else if(id == Block.lavaStill.blockID){
			handleStillLava(handler, x, y, z);
		}
	}

	private boolean getEnergy() {
		if(energy > 0){
			energy--;
			return true;
		}else{
			if(InventoryUtils.reduceIDInInventory(inventory, Block.ice.blockID, 1) == 1){
				energy += 9;
				return getEnergy();
			}else if(InventoryUtils.reduceIDInInventory(inventory, Block.blockSnow.blockID, 1) == 1){
				energy += 3;
				return getEnergy();
			}
		}
		return false;
	}
	
	private void handleStillWater(IMachineHandler handler, int x, int y, int z) {
		if(this.getEnergy()){
			handler.getWorld().setBlock(x, y, z, Block.ice.blockID);
			handler.getEnergyHandler().store(Elements.WATER, PRODUCTION_ICE);
		}
	}

	private void handleMovingWater(IMachineHandler handler, int x, int y, int z) {
		if(this.getEnergy()){
			handler.getWorld().setBlock(x, y, z, Block.ice.blockID);
			handler.getEnergyHandler().store(Elements.WATER, PRODUCTION_ICE * MOVING_MODIFIER);
		}
	}
	
	private void handleStillLava(IMachineHandler handler, int x, int y, int z) {
		if(this.getEnergy()){
			handler.getWorld().setBlock(x, y, z, Block.obsidian.blockID);
			handler.getEnergyHandler().store(Elements.FIRE, PRODUCTION_LAVA);
		}
	}
	
	private void handleMovingLava(IMachineHandler handler, int x, int y, int z) {
		if(this.getEnergy()){
			handler.getWorld().setBlock(x, y, z, Block.cobblestone.blockID);
			handler.getEnergyHandler().store(Elements.FIRE, PRODUCTION_LAVA * MOVING_MODIFIER);
		}
	}

	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.CRYSTALIZER, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
	}
	
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			InventoryUtils.dropInventory(inventory, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
		super.remove(handler);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		InventoryUtils.writeToNBT(inventory, tag);
	}
	
}
