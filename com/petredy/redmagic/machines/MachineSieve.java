package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.recipes.sieve.SieveDictionary;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineSieve extends Machine{

	
	public InventoryBasic inventoryInput;
	public InventoryBasic inventoryOutput;
	public ItemStack item;
	
	public int tick = 0, neededTicks = 100;
	public Composition tickCost = Composition.getStandard(2f, 0, 0, 0, 0);
	
	public MachineSieve(){
		metadata = Machines.SIEVE_METADATA;
		size = 1;
		this.inventoryInput = new InventoryBasic(Machines.SIEVE_NAME + ".input", false, 9);
		this.inventoryOutput = new InventoryBasic(Machines.SIEVE_NAME + ".output", false, 9);
	}
	
	@Override
	public void update(IMachineHandler handler) {
		if(item != null){
			if(this.tick < neededTicks){
				RedEnergy used = handler.getEnergyHandler().use(new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), tickCost));
				if(!used.isEmpty() && used.isEqual(new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), tickCost))){
					tick++;
					
				}
			}else{
				if(InventoryUtils.addItemStackToInventory(inventoryOutput, SieveDictionary.findOutput(item).copy()) == null){
					tick = 0;
					item = null;
				}
				
			}
		}else{
			ItemStack next = InventoryUtils.popItemMatches(inventoryInput, 1, SieveDictionary.getInputs());
			item = next;
		}
	}
	
	@Override
	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.SIEVE, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
	}
	
	@Override
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			InventoryUtils.dropInventory(inventoryInput, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
			InventoryUtils.dropInventory(inventoryOutput, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
			if(item != null)InventoryUtils.dropItemStack(item, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
		super.remove(handler);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.item = ItemStack.loadItemStackFromNBT(tag);
		InventoryUtils.readFromNBT(inventoryInput, tag);
		InventoryUtils.readFromNBT(inventoryOutput, tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		if(item != null)item.writeToNBT(tag);
		InventoryUtils.writeToNBT(inventoryInput, tag);
		InventoryUtils.writeToNBT(inventoryOutput, tag);
	}
	
}
