package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketMachineSync;
import com.petredy.redmagic.recipes.compactor.CompactorDictionary;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

public class MachineCompactor extends Machine{

	private static final Composition COST = Composition.getStandard(200, 0, 0, 0, 0);
	public InventoryBasic inventory;
	
	public MachineCompactor(){
		this.metadata = Machines.COMPACTOR_METADATA;
		this.size = 1;
		this.inventory = new InventoryBasic(Machines.COMPACTOR_NAME, false, 5);
	}
	
	public boolean canPlacedOnSide(int side){
		return side == 1;
	}
	
	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.COMPACTOR, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
	}
	
	public void onNeighborChange(IMachineHandler handler, int blockID) {
		if(handler.getEnergyHandler().getStoredEnergy().contains(COST) && blockID == Block.pistonExtension.blockID && isPistonPress(handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord())){
			ItemStack[] matrix = new ItemStack[4];
			for(int i = 1; i < 5; i++){
				matrix[i - 1] = this.inventory.getStackInSlot(i);
			}
			ItemStack output = CompactorDictionary.findOutput(matrix);
			if(output != null && 
				(this.inventory.getStackInSlot(0) == null || 
					(this.inventory.getStackInSlot(0).isItemEqual(output) && this.inventory.getStackInSlot(0).stackSize + output.stackSize <= output.getMaxStackSize()))
				&& handler.getEnergyHandler().use(RedEnergy.getFrom(COST)).isEqual(RedEnergy.getFrom(COST))){
				PacketDispatcher.sendPacketToAllInDimension(PacketHandler.populatePacket(new PacketMachineSync(handler.getXCoord(), handler.getYCoord(), handler.getZCoord(), handler.getEnergyHandler().getStoredEnergy())), handler.getWorld().provider.dimensionId);
				for(int i = 1; i < 5; i++){
					this.inventory.decrStackSize(i, 1);
				}
				if(this.inventory.getStackInSlot(0) != null){
					ItemStack slot = this.inventory.getStackInSlot(0);
					if(output.isItemEqual(slot)){
						output = output.copy();
						output.stackSize += slot.stackSize;
						this.inventory.setInventorySlotContents(0, output.copy());
					}
				}else{
					this.inventory.setInventorySlotContents(0, output.copy());
				}
			}
		}
	}
	
	public boolean isPistonPress(World world, int x, int y, int z){
		return world.getBlockId(x, y + 2, z) == Block.pistonMoving.blockID;
	}
	
	public void remove(IMachineHandler handler) {
		super.remove(handler);
		if(!handler.getWorld().isRemote){
			InventoryUtils.dropInventory(inventory, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		InventoryUtils.writeToNBT(inventory, tag);
	}
}
