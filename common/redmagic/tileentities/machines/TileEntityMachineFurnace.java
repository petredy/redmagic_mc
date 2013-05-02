package redmagic.tileentities.machines;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import redmagic.api.frame.*;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.tileentities.TileEntityConsumer;

public class TileEntityMachineFurnace extends TileEntityConsumer implements ISoulFrame, IInventory{
	
	public ItemStack[] inv = new ItemStack[2];
	
	public int side = 0;
	public int soulSlot = 0;
	public int burn = 0;
	
	public static final int inputSlot = 1;
	
	public TileEntityMachineFurnace(){
		super(LogicIndex.FURNACE_MAX_ESSENCES);
	}
	
	public void updateEntity(){
		if(this.isBurning()){
			if(this.inv[inputSlot] == null)burn = 0;
			else this.burnItem();
		}else{
			this.startBurn();
		}
	}
	
	private void burnItem() {
		this.burn++;
		LiquidStack cost = this.consume(LogicIndex.FURNACE_COSTS / this.getIntelligence());
		if(burn > LogicIndex.SOUL_MAX_INTELLIGENCE / this.getIntelligence() && this.inv[inputSlot] != null && cost != null){
			this.burn = 0;
			TileEntity entity = this.getTarget();
			if(entity instanceof IInventory){
				ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(this.inv[inputSlot]);
				if(output != null){
					output = output.copy();
					int burnAmount = output.getMaxStackSize() > this.getStrength() ? output.getMaxStackSize() / this.getStrength() : output.getMaxStackSize();
					int twice = this.getIllusion() / 10 > new Random().nextInt(100) ? 2 : 1;
					output.stackSize = burnAmount * output.stackSize * twice;
					if(output.stackSize > this.inv[inputSlot].stackSize)output.stackSize = this.inv[inputSlot].stackSize;
					Logger.log("-----------");
					Logger.log(output.stackSize);
					Logger.log(this.inv[inputSlot]);
					Logger.log(burnAmount);
					Logger.log(this.inv[inputSlot].stackSize);
					ItemStack drop = InventoryHelper.addItemStackToInventory((IInventory) entity, output);
					if(drop != null)InventoryHelper.dropItemStack(drop, worldObj, (double)xCoord + 0.5, (double)yCoord + 0.5, (double)zCoord + 0.5);
					Logger.log(output);
					if(this.inv[inputSlot].stackSize - burnAmount <= 0)this.inv[inputSlot] = null;
					else {
						this.burn++;
						this.decrStackSize(inputSlot, burnAmount);
					}
				}	
			}
		}
	}

	private void startBurn() {
		this.burn = 0;
		TileEntity entity = this.getTarget();
		if(entity instanceof IInventory){
			this.inv[inputSlot] = InventoryHelper.popBurnableItemStack((IInventory)entity);
			this.burn++;
		}
	}

	private TileEntity getTarget() {
		switch(side){
		case 0: return this.worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord);
		case 1: return this.worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
		case 2: return this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1);
		case 3: return this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1);
		case 4: return this.worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord);
		case 5: return this.worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
		default: return null;
		}
	}

	private boolean isBurning() {
		return burn > 0;
	}

	public int getIntelligence(){
		return this.getSoul() != null ? ((ISoul)this.getSoul().getItem()).getIntelligence(this.getSoul()) : 0;
	}
	
	public int getStrength(){
		return this.getSoul() != null ? ((ISoul)this.getSoul().getItem()).getStrength(this.getSoul()) : 0;
	}
	
	public int getIllusion(){
		return this.getSoul() != null ? ((ISoul)this.getSoul().getItem()).getIllusion(this.getSoul()) : 0;
	}
	
	public void setMaxEssences(){
		if(this.getSoul() != null){
			tank.setCapacity(LogicIndex.FURNACE_MAX_ESSENCES * ((ISoul)this.getSoul().getItem()).getCapacity(this.getSoul()));
		}
	}
	
	@Override
	public void storeSoul(ItemStack soul) {
		if(soul.getItem() instanceof ISoul){
			this.setInventorySlotContents(this.soulSlot, soul);
			this.setMaxEssences();
		}
		
	}

	@Override
	public ItemStack getSoul() {
		return this.getStackInSlot(this.soulSlot);
	}
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inv[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null){
			if(stack.stackSize < amount){
				this.setInventorySlotContents(slot, null);
			}else{
				stack = stack.splitStack(amount);
				if(stack.stackSize == 0){
					this.setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		ItemStack stack = getStackInSlot(var1);
		if(stack != null){
			this.setInventorySlotContents(var1, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		inv[var1] = var2;
		if(var2 != null && var2.stackSize > this.getInventoryStackLimit()){
			var2.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "TileEntityMachineFilter";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for(int i = 0; i < tagList.tagCount(); i++){
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inv.length){
				this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tag));
			}
		}
		this.setMaxEssences();
		this.side = tagCompound.getInteger("side");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < inv.length; i++){
			ItemStack stack = this.getStackInSlot(i);
			if(stack != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte)i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		
		tagCompound.setInteger("side", this.side);
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
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
