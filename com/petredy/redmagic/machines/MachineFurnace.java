package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineFurnace extends Machine{

	public InventoryBasic inventory;
	public int burnTick;
	public int needBurnTime = 100;
	public float burnTickCost = 0.1f;
	public float heatProduction = 0.1f;
	public ItemStack burningStack;
	
	public MachineFurnace(){
		this.inventory = new InventoryBasic(BlockIndex.MACHINE_FURNACE_NAME, false, 2);
		this.metadata = BlockIndex.MACHINE_FURNACE_METADATA;
	}
	
	public void update(IMachineHandler machineHandler) {
		if(burningStack != null){
			if(this.burnTick < needBurnTime){
				if(machineHandler.getEnergyHandler().use(burnTickCost) == burnTickCost){
					burnTick++;
					machineHandler.setHeat(machineHandler.getHeat() + heatProduction);
				}
			}else{
				burnTick = 0;
				if(inventory.getStackInSlot(1) != null){
					inventory.getStackInSlot(1).stackSize++;
				}else{
					ItemStack burnStack = FurnaceRecipes.smelting().getSmeltingResult(burningStack);
					inventory.setInventorySlotContents(1, null);
					inventory.setInventorySlotContents(1, burnStack.copy());
				}
				burningStack = null;
			}
		}else{
			if(inventory.getStackInSlot(0) != null){
				ItemStack inputBurned = FurnaceRecipes.smelting().getSmeltingResult(inventory.getStackInSlot(0));
				if(inputBurned != null){
					if(inventory.getStackInSlot(1) == null || inputBurned.isItemEqual(inventory.getStackInSlot(1))){
						burningStack = inventory.getStackInSlot(0).copy();
						burningStack.stackSize = 1;
						inventory.decrStackSize(0, 1);
					}
				}
			}
		}
	}
	
	public boolean canPlacedOnSide(int side){
		return side != 0 && side != 1;
	}
	
	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.FURNACE, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.burningStack = ItemStack.loadItemStackFromNBT(tag);
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		if(burningStack != null)burningStack.writeToNBT(tag);
		InventoryUtils.writeToNBT(inventory, tag);
	}
}
