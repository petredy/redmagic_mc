package redmagic.api.multiblock;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface IStructure {

	public abstract boolean buildFromBlock(World world, int x, int y, int z);
	
	public abstract void destroy(World world);
	
	public abstract List<IMultiBlock> getBlocks();
	
	public abstract boolean containsBlockAt(int x, int y, int z);
	
	public abstract IMultiBlock getBlockAt(int x, int y, int z);
	
	public abstract IMultiBlock getDataBlock();
	
	public abstract void createStructure(World world);
	
	
	public abstract void readFromNBT(NBTTagCompound tag);
	public abstract void writeToNBT(NBTTagCompound tag);
	
}
