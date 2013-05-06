package redmagic.tileentities;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import redmagic.Redmagic;
import redmagic.api.recipes.IRecipe;
import redmagic.containers.ContainerWorkTable;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.network.PacketHandler;
import redmagic.network.PacketWorkTable;
import redmagic.recipes.worktable.WorkTableRegistry;

public class TileEntityWorkTable extends TileEntityInventory{

	public static final int outputSlot = 0;
	public static final int showSlot = 10;
	public static final int craftingMatrixStart = 1;
	public static final int craftingMatrixLength = 9;
	
	
	public int displayCount = 0;
	public int displayLength = 50;
	public int displayMode = 0;
	
	public int craftingIndex = 0;
	
	public TileEntityWorkTable() {
		super(11, "Work Table");
	}
	
	public void updateEntity(){
		
	}
	
	public void craft(EntityPlayer player){
		
		ItemStack[] testinput = new ItemStack[this.craftingMatrixLength];
		for(int i = this.craftingMatrixStart; i < this.craftingMatrixStart + this.craftingMatrixLength; i++){
			if(this.getStackInSlot(i) != null)testinput[i - this.craftingMatrixStart] = this.getStackInSlot(i).copy();
		}
		ItemStack testoutput = WorkTableRegistry.find(testinput);
		if(testoutput != null && InventoryHelper.containsInventoryItems(player.inventory, testinput)){
			if(this.getStackInSlot(this.outputSlot) == null || (this.getStackInSlot(this.outputSlot).isItemEqual(testoutput) && this.getStackInSlot(this.outputSlot).stackSize + testoutput.stackSize <= this.getStackInSlot(this.outputSlot).getMaxStackSize())){
				displayMode++;
			}
		}
		
		if(displayMode == 3){
			displayMode = 0;
			ItemStack[] input = new ItemStack[this.craftingMatrixLength];
			ItemStack[] reduce = new ItemStack[this.craftingMatrixLength];
			for(int i = this.craftingMatrixStart; i < this.craftingMatrixStart + this.craftingMatrixLength; i++){
				input[i - this.craftingMatrixStart] = this.getStackInSlot(i);
				reduce[i - this.craftingMatrixStart] = this.getStackInSlot(i);
			}
			ItemStack output = WorkTableRegistry.find(input);
			
			ItemStack outputSlot = this.getStackInSlot(this.outputSlot);
			if(InventoryHelper.containsInventoryItems(player.inventory, input) && output != null && (outputSlot == null || (outputSlot != null && outputSlot.isItemEqual(output) && outputSlot.stackSize + output.stackSize <= outputSlot.getMaxStackSize()))){
				InventoryHelper.reduceItemsInInventory(player.inventory, reduce);
				if(outputSlot == null){
					this.setInventorySlotContents(this.outputSlot, output.copy());
				}else{
					this.getStackInSlot(this.outputSlot).stackSize++;
					if(this.getStackInSlot(this.outputSlot).stackSize > this.getStackInSlot(this.outputSlot).getMaxStackSize()) this.getStackInSlot(this.outputSlot).stackSize = this.getStackInSlot(this.outputSlot).getMaxStackSize();
				}
			}
		}
	}
	
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	public void displayMode(){
		if(this.worldObj.isRemote){
			ItemStack[] input = new ItemStack[this.craftingMatrixLength];
			for(int i = this.craftingMatrixStart; i < this.craftingMatrixStart + this.craftingMatrixLength; i++){
				input[i - this.craftingMatrixStart] = this.getStackInSlot(i);
			}
			ItemStack output = WorkTableRegistry.find(input);
			Redmagic.proxy.addEffect("worktable", this.worldObj, this.xCoord + 0.5, this.yCoord + 1.4, this.zCoord + 0.5, this.displayMode, output);
		}
	}
	
	public void showCrafting(){
		if(this.craftingIndex >= WorkTableRegistry.recipes.size()){
			//ShapelessRecipes
			this.setInventorySlotContents(showSlot, WorkTableRegistry.shapelessRecipes.get(craftingIndex - WorkTableRegistry.recipes.size()).output.copy());
			this.showMatrix(WorkTableRegistry.shapelessRecipes.get(craftingIndex - WorkTableRegistry.recipes.size()));
		}else if(this.craftingIndex <= this.getMaxCraftingIndex()){
			//NormalRecipes
			this.setInventorySlotContents(this.showSlot, WorkTableRegistry.recipes.get(this.craftingIndex).output.copy());
			this.showMatrix(WorkTableRegistry.recipes.get(craftingIndex));
		}
	}
	
	public void showMatrix(IRecipe recipe){
		this.clearMatrix();
		for(int i = 0; i < recipe.getInput().length; i++){
			ItemStack stack = recipe.getInput()[i];
			if(stack != null)this.setInventorySlotContents(i + 1, stack);
		}
	}

	private void clearMatrix() {
		for(int i = this.outputSlot + 1; i < this.showSlot; i++){
			this.setInventorySlotContents(i, null);
		}
	}
	
	public int getMaxCraftingIndex() {
		return WorkTableRegistry.recipes.size() + WorkTableRegistry.shapelessRecipes.size() - 1;
	}
	
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
    }

}
