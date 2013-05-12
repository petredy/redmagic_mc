package redmagic.api.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface IMultiEntity {
	
	public abstract IStructure getStructure();
	
	public abstract void setStructure(IStructure structure);
	public abstract void setStructure(Integer id);
	
	public abstract boolean hasStructure();
	
	public abstract void destroyStructure();
	public abstract void buildStructure();
	
	public World getWorld();
	public void writeToNBT(NBTTagCompound tag);
	public void readFromNBT(NBTTagCompound tag);
}
