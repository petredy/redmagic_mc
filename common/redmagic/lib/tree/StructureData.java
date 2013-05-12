package redmagic.lib.tree;

import redmagic.blocks.multi.tree.TreeStructure;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class StructureData extends WorldSavedData{
	
	public TreeStructure structure;
	
	public StructureData(String par1Str) {
		super(par1Str);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		this.structure = TreeStructure.loadFromNBT(nbttagcompound);
		if(this.structure != null && this.structure.blocks.size() <= 0)this.structure = null;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		if(this.structure != null)this.structure.writeToNBT(nbttagcompound);
	}

}
