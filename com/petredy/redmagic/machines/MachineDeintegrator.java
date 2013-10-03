package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.utils.InventoryUtils;

public class MachineDeintegrator extends Machine{

	public InventoryBasic inventory;
	public ItemStack item;
	
	public int tick;
	public int neededTicks = 100;
	
	public MachineDeintegrator(){
		this.metadata = Machines.DEINTEGRATOR_METADATA;
		this.inventory = new InventoryBasic(Machines.DEINTEGRATOR_NAME, false, 2);
	}
	
	public void update(IMachineHandler handler) {
		if(item != null){
			float redvalue = RedvalueDictionary.getRedvalue(item);
			float production = redvalue / neededTicks;
			if(tick < neededTicks){
				handler.getEnergyHandler().store(production);
				tick++;
			}else{
				tick = 0;
				item = null;
				if(new java.util.Random().nextFloat() < 1 / redvalue / 10){
					if(inventory.getStackInSlot(1) != null){
						inventory.getStackInSlot(1).stackSize++;
					}else{
						inventory.setInventorySlotContents(1, null);
						inventory.setInventorySlotContents(1, new ItemStack(Items.matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA));
					}
				}
			}
		}else{
			if(inventory.getStackInSlot(0) != null){
				item = inventory.getStackInSlot(0);
				inventory.decrStackSize(0, 1);
			}
		}
	}
	
	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.DEINTEGRATOR, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
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
	
}
