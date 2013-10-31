package com.petredy.redmagic.machines;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.container.ContainerFurnace;

public class MachineFurnace extends Machine{

	public InventoryBasic inventory;
	public float burnTick;
	public int needBurnTime = 100;
	public Composition burnTickCost;
	public float heatProduction = 0.1f;
	public ItemStack burningStack;
	
	public static int LARGE_MACHINE_BURN_TIME = 50;
	public static float LARGE_MACHINE_COST_MODIFIER = 0.8f;
	
	public MachineFurnace(){
		this.inventory = new InventoryBasic(Machines.FURNACE_NAME, false, 2);
		this.metadata = Machines.FURNACE_METADATA;
		this.size = 1;
		this.name = Machines.FURNACE_NAME;
		this.burnTickCost = Composition.getStandard(0.0f, 0.0f, 0.0f, 2f, 0f);
		this.tribological = new Tribological(new ItemStack[]{
			new ItemStack(Items.plateRhenium), new ItemStack(Items.logicStorage), new ItemStack(Items.plateRhenium),
			new ItemStack(Items.logicStorage), new ItemStack(Items.logicCore), new ItemStack(Items.logicStorage),
			new ItemStack(Items.plateRhenium), new ItemStack(Items.logicStorage), new ItemStack(Items.plateRhenium)
		});
	}
	
	public void update(IMachineHandler handler) {
		if(burningStack != null){
			if(this.burnTick < (handler.getHandlerSize() > 1 ? LARGE_MACHINE_BURN_TIME: needBurnTime)){
				this.active = true;
				Composition comp = burnTickCost.copy();
				if(handler.getHandlerSize() > 1)comp.multiply(LARGE_MACHINE_COST_MODIFIER);
				RedEnergy used = handler.getEnergyHandler().use(new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), comp));
				if(!used.isEmpty() && used.isEqual(new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), comp))){
					burnTick += (float)tribological.getStatus() / 100f;
					handler.setHeat(handler.getHeat() + heatProduction);
					tribological.damage(1);
				}else{
					this.active = false;
				}
			}else{
				burnTick = 0;
				this.active = false;
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
			this.active = false;
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
	
	public boolean canPlacedOnSide(int side, int size){
		switch(size){
		case 1: return side != 0 && side != 1;
		case 18: return true;
		default: return false;
		}
	}
	
	public boolean activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.FURNACE, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		return true;
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
		this.burnTick = tag.getFloat("burnTick");
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		if(burningStack != null)burningStack.writeToNBT(tag);
		tag.setFloat("burnTick", burnTick);
		InventoryUtils.writeToNBT(inventory, tag);
	}
}
