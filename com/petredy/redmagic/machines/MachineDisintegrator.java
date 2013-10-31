package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineDisintegrator extends Machine{

	public InventoryBasic inventory;
	public ItemStack item;
	
	public int tick;
	public int neededTicks = 100;
	
	public MachineDisintegrator(){
		this.metadata = Machines.DISINTEGRATOR_METADATA;
		this.inventory = new InventoryBasic(Machines.DISINTEGRATOR_NAME, false, 2);
		this.size = 2;
	}
	
	public void update(IMachineHandler handler) {
		if(item != null){
			Composition redvalue = RedvalueDictionary.getComposition(item);
			if(redvalue != null && !redvalue.isEmpty()){
				Composition production = Composition.divide(redvalue, neededTicks);
				if(tick < neededTicks){
					handler.getEnergyHandler().store(RedEnergy.getFrom(production));
					float heat = production.getRedvalue();
					handler.setHeat(handler.getHeat() + heat);
					tick++;
				}else{
					tick = 0;
					item = null;
					if(new java.util.Random().nextFloat() < 1 / redvalue.getRedvalue() / 10){
						if(inventory.getStackInSlot(1) != null){
							inventory.getStackInSlot(1).stackSize++;
						}else{
							inventory.setInventorySlotContents(1, null);
							//inventory.setInventorySlotContents(1, new ItemStack(Items.matter, 1, ItemIndex.MATTER_LEFTOVER_METADATA));
						}
					}
				}
			}
		}else{
			if(handler.getHeat() < -100 && inventory.getStackInSlot(0) != null && RedvalueDictionary.getComposition(inventory.getStackInSlot(0)) != null && !RedvalueDictionary.getComposition(inventory.getStackInSlot(0)).isEmpty()){
				item = inventory.getStackInSlot(0);
				inventory.decrStackSize(0, 1);
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
	
}
