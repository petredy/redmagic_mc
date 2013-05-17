package redmagic.lib.tree;

import redmagic.helpers.SoulHelper;
import redmagic.lib.souls.Soul;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SoulBlock {
	
	public int x, y, z;
	public ItemStack soulStack;
	public Soul soul;
	
	public SoulBlock(){}
	public SoulBlock(int x, int y, int z, ItemStack soulStack){
		this.x = x;
		this.y = y;
		this.z = z;
		this.soulStack = soulStack;
		this.soul = SoulHelper.getSoulByStack(soulStack);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.x = tag.getInteger("x");
		this.y = tag.getInteger("y");
		this.z = tag.getInteger("z");
		this.soulStack = ItemStack.loadItemStackFromNBT(tag);
		if(soulStack != null)this.soul = SoulHelper.getSoulByStack(soulStack);
		if(this.soul != null)this.soul.readFromNBT(tag.getCompoundTag("soul"));
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
		if(this.soulStack != null)this.soulStack.writeToNBT(tag);
		if(this.soul != null){
			NBTTagCompound soulData = new NBTTagCompound();
			this.soul.writeToNBT(soulData);
			tag.setTag("soul", soulData);
		}
	}
}
