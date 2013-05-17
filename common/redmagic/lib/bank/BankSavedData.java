package redmagic.lib.bank;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class BankSavedData extends WorldSavedData{

	public BankData data;
	
	public BankSavedData(String token){
		super(token);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		this.data = new BankData();
		this.data.readFromNBT(nbttagcompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		if(this.data != null)data.writeToNBT(nbttagcompound);
	}
	

}
