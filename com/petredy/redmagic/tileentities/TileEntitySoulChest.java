package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.entities.EntitySoul;
import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.soul.SoulInventory;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.SoulUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySoulChest extends TileEntity implements IInventory{

	 public float lidAngle;

    /** The angle of the chest lid last tick */
    public float prevLidAngle;

    /** The number of players currently using this chest */
    public int numUsingPlayers;
    
    public EntitySoul soul;

	private int ticksSinceSync;

    public TileEntitySoulChest() {

        super();
        EntitySoul soul = SoulUtils.getNextSoul(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public int getSizeInventory() {

        if(soul != null)return soul.inventory.getSizeInventory();
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        if(soul != null)return soul.inventory.getStackInSlot(slot);
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

    	if(soul != null)return soul.inventory.decrStackSize(slot, amount);
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

    	if(soul != null)return soul.inventory.getStackInSlotOnClosing(slot);
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
    	if(soul != null){
    		soul.inventory.setInventorySlotContents(slot, itemStack);
    	}
        this.onInventoryChanged();
    }

    @Override
    public String getInvName() {

        return Blocks.soulChest.getUnlocalizedName();
    }

    @Override
    public int getInventoryStackLimit() {

        if(soul != null)return soul.inventory.getInventoryStackLimit();
        return 64;
    }

    /**
     * Called when a client event is received with the event number and
     * argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int eventID, int numUsingPlayers) {

        if (eventID == 1) {
            this.numUsingPlayers = numUsingPlayers;
            return true;
        }
        else
            return super.receiveClientEvent(eventID, numUsingPlayers);
    }

    @Override
    public void openChest() {

        ++numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, Blocks.trading.blockID, 1, numUsingPlayers);
    }

    @Override
    public void closeChest() {

        --numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, Blocks.trading.blockID, 1, numUsingPlayers);
    }

    @Override
    public void updateEntity() {

        super.updateEntity();
        
        if(soul == null){
        	soul = SoulUtils.getNextSoul(worldObj, xCoord, yCoord, zCoord);
        	
        }
        

        prevLidAngle = lidAngle;
        float angleIncrement = 0.1F;
        double adjustedXCoord, adjustedZCoord;

        if (numUsingPlayers > 0 && lidAngle == 0.0F) {
            adjustedXCoord = xCoord + 0.5D;
            adjustedZCoord = zCoord + 0.5D;
            worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_OPEN, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
            float var8 = lidAngle;

            if (numUsingPlayers > 0) {
                lidAngle += angleIncrement;
            }
            else {
                lidAngle -= angleIncrement;
            }

            if (lidAngle > 1.0F) {
                lidAngle = 1.0F;
            }

            if (lidAngle < 0.5F && var8 >= 0.5F) {
                adjustedXCoord = xCoord + 0.5D;
                adjustedZCoord = zCoord + 0.5D;
                worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_CLOSE, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (lidAngle < 0.0F) {
                lidAngle = 0.0F;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
    }

    @Override
    public boolean isInvNameLocalized() {

        return false;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack){

        return true;
    }

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	
}
