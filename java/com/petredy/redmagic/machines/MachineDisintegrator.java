package com.petredy.redmagic.machines;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

public class MachineDisintegrator extends Machine{

	public InventoryBasic inventory;
	public ItemStack item;
	
	public int tick;
	public int neededTicks = 100;
	
	public MachineDisintegrator(){
		this.metadata = Machines.DISINTEGRATOR_METADATA;
		this.inventory = new InventoryBasic(Machines.DISINTEGRATOR_NAME, false, 2);
		this.size = 2;
		this.name = Machines.DISINTEGRATOR_NAME;
		this.tribological = new Tribological(new ItemStack[]{
				new ItemStack(Items.plateRhenium), new ItemStack(Items.energyCondenser), new ItemStack(Items.plateRhenium),
				new ItemStack(Items.heatDevice), new ItemStack(Items.logicCore), new ItemStack(Items.coolingDevice),
				new ItemStack(Items.plateRhenium), new ItemStack(Items.logicStorage), new ItemStack(Items.plateRhenium)
		});
	}
	
	public boolean canPlacedOnSide(int side, int size){
		switch(size){
		case 1: return true;
		case 18: return side != 1;
		default: return false;
		}
	}
	
	public void update(IMachineHandler handler) {
		if(item != null && tribological.getStatus() > 0){
			Composition redvalue = RedvalueDictionary.getComposition(item);
			if(redvalue != null && !redvalue.isEmpty()){
				Composition production = Composition.divide(redvalue, neededTicks);
				tribological.damage(1f);
				if(tick < neededTicks){
					handler.getEnergyHandler().store(RedEnergy.getFrom(production));
					float heat = production.getRedvalue();
					handler.setHeat(handler.getHeat() + heat);
					tick++;
					this.active = true;
				}else{
					tick = 0;
					item = null;
					this.active = false;
					if(new java.util.Random().nextFloat() < 1 / redvalue.getRedvalue() / 10){
						if(inventory.getStackInSlot(1) != null){
							inventory.getStackInSlot(1).stackSize++;
						}else{
							inventory.setInventorySlotContents(1, null);
							inventory.setInventorySlotContents(1, new ItemStack(Items.matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA));
						}
					}
				}
			}
		}else if(tribological.getStatus() > 0){
			this.active = false;
			if(handler.getHeat() < -100 && inventory.getStackInSlot(0) != null && RedvalueDictionary.getComposition(inventory.getStackInSlot(0)) != null && !RedvalueDictionary.getComposition(inventory.getStackInSlot(0)).isEmpty()){
				item = inventory.getStackInSlot(0);
				inventory.decrStackSize(0, 1);
			}else if(inventory.getStackInSlot(0) == null && handler.getHandlerSize() > 1){
				this.transferItemToSlot(handler, inventory, 0);
			}
		}
	}
	
	public boolean activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.DISINTEGRATOR, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		return true;
	}
	
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			InventoryUtils.dropInventory(inventory, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
			if(item != null)InventoryUtils.dropItemStack(item, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
		super.remove(handler);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.item = ItemStack.loadItemStackFromNBT(tag);
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		if(item != null)item.writeToNBT(tag);
		InventoryUtils.writeToNBT(inventory, tag);
	}
	
	public boolean transferItemToSlot(IMachineHandler handler, IInventory inventory, int input) {
		VirtualBlock block = handler.getBlockInfrontMachineOnSide(getSide());
		TileEntity entity = handler.getWorld().getBlockTileEntity(block.x, block.y, block.z);
		if(entity instanceof IInventory){
			IInventory inv = (IInventory)entity;
			for(int i = 0; i < inv.getSizeInventory(); i++){
				ItemStack slot = inv.getStackInSlot(i);
				if(slot != null && RedvalueDictionary.getRedvalue(slot.copy()) > 0){
					inventory.setInventorySlotContents(input, slot.copy());
					inv.setInventorySlotContents(i, null);
					return true;
				}
			}
		}
		return false;
	}
}
