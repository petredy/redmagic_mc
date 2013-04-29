package redmagic.api.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface IMultiBlock {

	public abstract boolean isMultiBlock(World world);
	public abstract IMultiEntity getBasicEntity(World world);
	public abstract int getX();
	public abstract int getY();
	public abstract int getZ();
	
	public abstract void readFromNBT(NBTTagCompound tag);
	public abstract void writeToNBT(NBTTagCompound tag);
	
}
