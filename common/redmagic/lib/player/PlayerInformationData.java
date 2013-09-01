package redmagic.lib.player;

import redmagic.helpers.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class PlayerInformationData extends WorldSavedData{

	
	public PlayerInformation information;
	
	public PlayerInformationData(String par1Str) {
		super(par1Str);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		this.information = PlayerInformation.loadFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		this.information.writeToNBT(nbttagcompound);
	}

}
