package redmagic.api.multiblock;

import net.minecraft.nbt.NBTTagCompound;

public interface IMultiEntity {
	
	public abstract IStructure getStructure();
	
	public abstract void setStructure(IStructure structure);
	
	public abstract boolean hasStructure();
	
	public abstract void destroyStructure();
	public abstract void buildStructure();
	

	public void writeToNBT(NBTTagCompound tag);
	public void readFromNBT(NBTTagCompound tag);
}
