package redmagic.tileentities.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.liquids.LiquidTank;
import redmagic.api.frame.ISoulFrame;
import redmagic.api.frame.ISoul;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.EnvironmentHelper;
import redmagic.tileentities.TileEntityProducer;


public class TileEntityMachineFilter extends TileEntityProducer implements ISoulFrame, IInventory{
	
	public ItemStack[] inv;
	public int updateCount = 0;
	
	private int soulSlot = 0;
	
	public TileEntityMachineFilter(){
		super(LogicIndex.FILTER_STORAGE);
		this.inv = new ItemStack[1];
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.getUpdatesNeeded() > 0 && updateCount >= this.getUpdatesNeeded()){
			EnvironmentHelper environment = new EnvironmentHelper(this.worldObj, this.xCoord, this.yCoord, this.zCoord, LogicIndex.FILTER_RANGE);
			updateCount = 0;
			int production = this.getStrength() * environment.avg;
			this.produce(production);
		}
		updateCount++;
	}
	@Override
	public void storeSoul(ItemStack soul) {
		if(soul.getItem() instanceof ISoul){
			this.setInventorySlotContents(this.soulSlot, soul);
			this.setMaxEssences();
		}
	}
	
	public void setMaxEssences(){
		Logger.log(this.getCapacity() * LogicIndex.FILTER_STORAGE);
		tank.setCapacity(this.getCapacity() * LogicIndex.FILTER_STORAGE);
	}
	
	
	public int getUpdatesNeeded(){
		return this.getStackInSlot(this.soulSlot) != null ? LogicIndex.SOUL_MAX_INTELLIGENCE / ((ISoul)this.getStackInSlot(this.soulSlot).getItem()).getIntelligence(this.getStackInSlot(this.soulSlot)) : 0;
	}
	
	public int getStrength(){
		return this.getStackInSlot(this.soulSlot) != null ? ((ISoul)this.getStackInSlot(this.soulSlot).getItem()).getStrength(this.getStackInSlot(this.soulSlot)) : 0;
	}
	
	public int getCapacity(){
		return this.getStackInSlot(this.soulSlot) != null ? ((ISoul)this.getStackInSlot(this.soulSlot).getItem()).getCapacity(this.getStackInSlot(this.soulSlot)) : 0;
	}
	
	public int getIllusion(){
		return this.getStackInSlot(this.soulSlot) != null ? ((ISoul)this.getStackInSlot(this.soulSlot).getItem()).getIllusion(this.getStackInSlot(this.soulSlot)) : 0;
	}
	
	@Override
	public ItemStack getSoul() {
		return this.getStackInSlot(0);
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
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
}
