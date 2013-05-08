package redmagic.tileentities;

import redmagic.helpers.BlockHelper;
import redmagic.helpers.FragmentHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.SoulHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityMold extends TileEntity{
	public int intelligence, strength, capacity, illusion, satisfaction, uses;
	public int maxUses;
	
	public int count = 0, maxCount = 10;
	
	public TileEntityMold(int maxUses){
		this.maxUses = maxUses;
	}
	
	public void updateEntity(){
		if(uses > 0 && count > maxCount && this.isPressedByPistion()){
			ItemStack soul = SoulHelper.createSoul(intelligence, strength, capacity, illusion, satisfaction);
			if(!worldObj.isRemote)InventoryHelper.dropItemStack(soul, worldObj, xCoord, yCoord, zCoord);
			BlockHelper.breakBlock(worldObj, xCoord, yCoord, zCoord, 0, false);
			return;
		}
		if(count > maxCount)count = 0;
		count++;
	}
	
	private boolean isPressedByPistion() {
		if(this.isPistionWithPower(xCoord + 1, yCoord, zCoord, 5))return true;
		if(this.isPistionWithPower(xCoord - 1, yCoord, zCoord, 4))return true;
		if(this.isPistionWithPower(xCoord, yCoord + 1, zCoord, 1))return true;
		if(this.isPistionWithPower(xCoord, yCoord - 1, zCoord, 0))return true;
		if(this.isPistionWithPower(xCoord, yCoord, zCoord + 1, 3))return true;
		if(this.isPistionWithPower(xCoord, yCoord, zCoord - 1, 2))return true;
		return false;
	}

	private boolean isPistionWithPower(int x, int y, int z, int side) {
		return worldObj.getBlockId(x, y, z) == Block.pistonBase.blockID && worldObj.isBlockIndirectlyGettingPowered(x, y, z) &&
				Block.pistonBase.getOrientation(worldObj.getBlockMetadata(x, y, z)) == ForgeDirection.OPPOSITES[side];
	}

	public void readFromNBT(NBTTagCompound tag){
		this.intelligence = tag.getInteger("intelligence");
		this.strength = tag.getInteger("strength");
		this.capacity = tag.getInteger("capacity");
		this.illusion = tag.getInteger("illusion");
		this.satisfaction = tag.getInteger("satisfaction");
		this.uses = tag.getInteger("uses");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("intelligence", intelligence);
		tag.setInteger("strength", strength);
		tag.setInteger("capacity", capacity);
		tag.setInteger("illusion", illusion);
		tag.setInteger("satisfaction", satisfaction);
		tag.setInteger("uses", uses);
	}

	public void addFragment(ItemStack current) {
		if(uses + FragmentHelper.getIntelligence(current) <= maxUses){
			this.intelligence += FragmentHelper.getIntelligence(current);
			uses += FragmentHelper.getIntelligence(current);
		}else{
			int rest = maxUses - uses;
			this.intelligence += rest;
			uses = maxUses;
		}
		
		if(uses + FragmentHelper.getStrength(current) <= maxUses){
			this.strength += FragmentHelper.getStrength(current);
			uses += FragmentHelper.getStrength(current);
		}else{
			int rest = maxUses - uses;
			this.strength += rest;
			uses = maxUses;
		}
		
		if(uses + FragmentHelper.getCapacity(current) <= maxUses){
			this.capacity += FragmentHelper.getCapacity(current);
			uses += FragmentHelper.getCapacity(current);
		}else{
			int rest = maxUses - uses;
			this.capacity += rest;
			uses = maxUses;
		}
		
		if(uses + FragmentHelper.getIllusion(current) <= maxUses){
			this.illusion += FragmentHelper.getIllusion(current);
			uses += FragmentHelper.getIllusion(current);
		}else{
			int rest = maxUses - uses;
			this.illusion += rest;
			uses = maxUses;
		}
		
		if(uses + FragmentHelper.getSatisfaction(current) <= maxUses){
			this.satisfaction += FragmentHelper.getSatisfaction(current);
			uses += FragmentHelper.getSatisfaction(current);
		}else{
			int rest = maxUses - uses;
			this.satisfaction += rest;
			uses = maxUses;
		}
		
		
		
	}
}
