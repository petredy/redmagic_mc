package redmagic.tileentities.machines;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

import redmagic.api.frame.ISoul;
import redmagic.api.frame.ISoulFrame;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.tileentities.TileEntityConsumer;

public class TileEntityMachineSlaugther extends TileEntityConsumer implements ISoulFrame,IInventory{

	public int side;
	public ItemStack[] inv = new ItemStack[1];
	public String name;
	public boolean active = true;
	
	public TileEntityMachineSlaugther() {
		super(LogicIndex.SLAUGTHER_MAX_ESSENCES);
		this.name = "Slaugther";
	}

	
	@Override
	public void updateEntity(){
		if(active){
			List<EntityLiving> entities = this.getEntities();
			Logger.log(side);
			Logger.log(entities);
			if(entities != null){
				Iterator<EntityLiving> it = entities.iterator();
				while(it.hasNext()){
					EntityLiving entity = it.next();
					double distance = entity.getDistance(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) * 10;
					double xAll = xCoord + 0.5 - entity.posX;
					double xOne = xAll / distance;
					double yOne = (yCoord + 0.5 - entity.posY) / distance;
					double zOne = (zCoord + 0.5 - entity.posZ) / distance;
					entity.moveEntity(xOne, yOne, zOne);
					if(entity.getDistance(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 2){
						entity.setEntityHealth(entity.getHealth() - 1);
					}
				}
			}
		}
	}
	
	private List<EntityLiving> getEntities() {
		switch(this.side){
		case 0:
			return worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord - LogicIndex.SLAUGTHER_RANGE, yCoord - LogicIndex.SLAUGTHER_RANGE * 3, zCoord - LogicIndex.SLAUGTHER_RANGE, xCoord + LogicIndex.SLAUGTHER_RANGE, yCoord, zCoord + LogicIndex.SLAUGTHER_RANGE));
		case 1:
			return worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord - LogicIndex.SLAUGTHER_RANGE, yCoord + LogicIndex.SLAUGTHER_RANGE, zCoord - LogicIndex.SLAUGTHER_RANGE, xCoord + LogicIndex.SLAUGTHER_RANGE, yCoord + LogicIndex.SLAUGTHER_RANGE*3, zCoord + LogicIndex.SLAUGTHER_RANGE));
		case 2:
			return worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord - LogicIndex.SLAUGTHER_RANGE, yCoord - LogicIndex.SLAUGTHER_RANGE, zCoord - LogicIndex.SLAUGTHER_RANGE * 3, xCoord + LogicIndex.SLAUGTHER_RANGE, yCoord + LogicIndex.SLAUGTHER_RANGE, zCoord));
		case 3:
			return worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord - LogicIndex.SLAUGTHER_RANGE, yCoord - LogicIndex.SLAUGTHER_RANGE, zCoord + LogicIndex.SLAUGTHER_RANGE, xCoord + LogicIndex.SLAUGTHER_RANGE, yCoord + LogicIndex.SLAUGTHER_RANGE, zCoord + LogicIndex.SLAUGTHER_RANGE*3));
		case 4:
			return worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord - LogicIndex.SLAUGTHER_RANGE * 3, yCoord - LogicIndex.SLAUGTHER_RANGE, zCoord - LogicIndex.SLAUGTHER_RANGE, xCoord + LogicIndex.SLAUGTHER_RANGE, yCoord + LogicIndex.SLAUGTHER_RANGE, zCoord + LogicIndex.SLAUGTHER_RANGE));
		case 5: //TODO
			return worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord + LogicIndex.SLAUGTHER_RANGE, yCoord - LogicIndex.SLAUGTHER_RANGE, zCoord - LogicIndex.SLAUGTHER_RANGE, xCoord + LogicIndex.SLAUGTHER_RANGE*4, yCoord + LogicIndex.SLAUGTHER_RANGE, zCoord + LogicIndex.SLAUGTHER_RANGE));
		}
		return null;
	}


	@Override
	public void storeSoul(ItemStack soul) {
		this.inv[0] = soul;
		this.setCapacity();
	}
	
	public void setCapacity(){
		if(this.inv[0] != null)
		this.tank.setCapacity(((ISoul)this.inv[0].getItem()).getCapacity(this.inv[0]) * LogicIndex.SLAUGTHER_MAX_ESSENCES);
	}

	@Override
	public ItemStack getSoul() {
		return this.inv[0];
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
			if(stack.stackSize <= amount){
				this.setInventorySlotContents(slot, null);
			}else{
				stack = stack.splitStack(amount);
				if(stack.stackSize <= 0){
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
		return this.name;
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
		this.side = tagCompound.getInteger("side");
		this.setCapacity();
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
		return true;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}


	public void activate() {
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;		
	}

}
