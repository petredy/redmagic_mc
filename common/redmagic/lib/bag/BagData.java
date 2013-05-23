package redmagic.lib.bag;

import redmagic.tileentities.TileEntityBag;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class BagData extends WorldSavedData{

	public TileEntityBag bag;
	
	public BagData(String par1Str) {
		super(par1Str);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		bag = new TileEntityBag();
		bag.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		if(bag != null)bag.writeToNBT(nbttagcompound);
	}

}
