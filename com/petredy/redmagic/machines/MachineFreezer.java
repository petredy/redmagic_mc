package com.petredy.redmagic.machines;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineFreezer extends Machine{

	public InventoryBasic inventory;
	public ItemStack item;
	
	public int tick;
	public int neededTicks = 333;
	
	public MachineFreezer(){
		this.metadata = Machines.FREEZER_METADATA;
		this.size = 1;
		this.name = Machines.FREEZER_NAME;
		this.inventory = new InventoryBasic(Machines.FREEZER_NAME, false, 2);
		this.tribological = new Tribological(new ItemStack[]{
			new ItemStack(Items.plateRhenium), new ItemStack(Items.coolingDevice), new ItemStack(Items.plateRhenium),
			new ItemStack(Items.logicStorage), new ItemStack(Items.logicCore), new ItemStack(Items.logicStorage),
			new ItemStack(Items.plateRhenium), new ItemStack(Items.coolingDevice), new ItemStack(Items.plateRhenium)
		});
	}
	
	
	public void update(IMachineHandler handler) {
		if(item != null){
			if(tick < neededTicks && tribological.getStatus() > 0){
				tick++;
				tribological.damage(1f);
				handler.setHeat(handler.getHeat() - (getCooling(item) / neededTicks));
				this.active = true;
			}else{
				tick = 0;
				item = null;
				this.active = false;
			}
		}else{
			this.active = false;
			if(inventory.getStackInSlot(0) != null && getCooling(inventory.getStackInSlot(0)) > 0){
				item = inventory.getStackInSlot(0).copy();
				item.stackSize = 1;
				inventory.decrStackSize(0, 1);
			}else if(inventory.getStackInSlot(0) == null && handler.getHandlerSize() > 1){
				List<ItemStack> items = new ArrayList<ItemStack>();
				items.add(new ItemStack(Block.ice));
				this.transferItemToSlot(handler, this.inventory, 0, items);
			}
		}
	}
	
	


	public boolean activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.FREEZER, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
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
	
	public float getCooling(ItemStack stack){
		if(stack.isItemEqual(new ItemStack(Block.ice)))return 250F;
		return 0;
	}
}
