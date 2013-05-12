package redmagic.lib.tree;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SoulBlock {
	
	public int x, y, z;
	public ItemStack soul;
	
	public SoulBlock(){}
	public SoulBlock(int x, int y, int z, ItemStack soul){
		this.x = x;
		this.y = y;
		this.z = z;
		this.soul = soul;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.x = tag.getInteger("x");
		this.y = tag.getInteger("y");
		this.z = tag.getInteger("z");
		this.soul = ItemStack.loadItemStackFromNBT(tag);
	}
	
	public static SoulBlock loadFromNBT(NBTTagCompound tag){
		SoulBlock block = new SoulBlock();
		block.readFromNBT(tag);
		return block;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("x", this.x);
		tag.setInteger("y", this.y);
		tag.setInteger("z", this.z);
		if(this.soul != null)this.soul.writeToNBT(tag);
	}
}
