package com.petredy.redmagic.machines;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.api.machinery.IMachineIconProvider;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.machinery.IconHandler;
import com.petredy.redmagic.machinery.MachineHandler;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;


public class Machine implements IMachineIconProvider{
	
	protected int side;
	protected int metadata;
	protected int size;
	protected String name;
	public boolean active;
	public Tribological tribological;
	
	public void update(IMachineHandler handler) {
		
	}
	
	public int getSide(){
		return side;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getMetadata(){
		return metadata;
	}
	
	public void setSide(int i){
		side = i;
	}
	
	public boolean canPlacedOnSide(int side, int size){
		return true;
	}
	
	public void onPlacedByPlayer(IMachineHandler handler, int side, EntityPlayer player){
		this.side = side;
		if(!handler.getWorld().isRemote){
			ItemStack machine = player.getCurrentEquippedItem();
			this.tribological = ((IMachineItem)machine.getItem()).getTribological(machine);
			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		}
	}
	
	public void removeByPlayer(IMachineHandler handler, EntityPlayer player) {
		this.remove(handler);
	}
	
	public void onDisplayTick(IMachineHandler handler, Random rand) {
		
	}
	
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			ItemStack stack = new ItemStack(Items.machine, 1, getMetadata());
			((IMachineItem)stack.getItem()).setTribological(stack, tribological);
			InventoryUtils.dropItemStack(stack, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
	}
	
	public boolean activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.side = tag.getInteger("side");
		this.metadata = tag.getInteger("metadata");
	}
	
	public static Machine loadFromNBT(NBTTagCompound tag){
		int metadata = tag.getInteger("metadata");
		Machine machine = MachineHandler.getMachine(metadata);
		machine.readFromNBT(tag);
		return machine;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("side", side);
		tag.setInteger("metadata", metadata);
	}

	public void onNeighborChange(IMachineHandler handler, int blockID) {
		
	}

	@Override
	public Icon getIconForSmallMachine(IconRegister iconRegister) {
		return iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_NAME + "." + name);
	}

	@Override
	public Icon[] getIconForLargeMachineInactive(IconRegister iconRegister) {
		Icon[] icons = new Icon[9];
		int index = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				icons[index++] = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_NAME + "." + name + ".inactive." + "_" + i + "_" + j);
			}
		}
		return icons;
	}

	@Override
	public Icon[] getIconForLargeMachineActive(IconRegister iconRegister) {
		Icon[] icons = new Icon[9];
		int index = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				icons[index++] = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_NAME + "." + name + ".active." + "_" + i + "_" + j);
			}
		}
		return icons;
	}

	
	
	
	
}
