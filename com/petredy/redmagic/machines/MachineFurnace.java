package com.petredy.redmagic.machines;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.container.ContainerFurnace;

public class MachineFurnace extends Machine{

	public InventoryBasic inventory;
	public int burnTick;
	public int needBurnTime = 100;
	public Composition burnTickCost;
	public float heatProduction = 0.1f;
	public ItemStack burningStack;
	
	public MachineFurnace(){
		this.inventory = new InventoryBasic(Machines.FURNACE_NAME, false, 2);
		this.metadata = Machines.FURNACE_METADATA;
		this.size = 1;
		this.burnTickCost = Composition.getStandard(0.1f, 0.1f, 0.1f, 0.1f, 0.0f);
	}
	
	public void update(IMachineHandler handler) {
		if(burningStack != null){
			if(this.burnTick < needBurnTime){
				RedEnergy used = handler.getEnergyHandler().use(new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), burnTickCost));
				if(!used.isEmpty() && used.isEqual(new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), burnTickCost))){
					burnTick++;
					handler.setHeat(handler.getHeat() + heatProduction);
					
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
	
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			InventoryUtils.dropInventory(inventory, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
			if(burningStack != null)InventoryUtils.dropItemStack(burningStack, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
		super.remove(handler);
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
