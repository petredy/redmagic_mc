package redmagic.api.multiblock;

import net.minecraft.nbt.NBTTagCompound;

public interface IMultiEntity {
	
	public abstract IStructure getStructure();
	
	public abstract void setStructure(IStructure structure);
	
	public abstract boolean hasStructure();
	

	public void writeToNBT(NBTTagCompound tag);
	public void readFromNBT(NBTTagCompound tag);
}
