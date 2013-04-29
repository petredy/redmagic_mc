package redmagic.tileentities;

import cpw.mods.fml.common.network.PacketDispatcher;
import redmagic.api.essence.IStorage;
import redmagic.network.PacketEnergyStorage;
import redmagic.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStorage extends TileEntity implements IStorage{

	public int maxEssences;
	public int essences = 0;
	public int updateCount = 0;
	public int updateNeed = 50;
	
	@Override
	public void updateEntity(){
		if(updateCount == updateNeed){
			PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketEnergyStorage(this.essences, this.xCoord, this.yCoord, this.zCoord)));
			updateCount = 0;
		}
		updateCount++;
	}
	
	@Override
	public int store(int amount, Object... data) {
		int load = this.maxEssences - essences >= amount ? amount : this.maxEssences - essences;
		this.essences += load;
		return load;
	}

	@Override
	public int extract(int amount, Object... data) {
		int unload = this.essences - amount >= 0 ? amount : this.essences;
		this.essences -= unload;
		return unload;
	}

	@Override
	public int getEssences(Object... data) {
		return this.essences;
	}

	@Override
	public int getMaxEssences() {
		return this.maxEssences;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
    {
		super.readFromNBT(tag);
		this.essences = tag.getInteger("essences");
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
    {
		super.writeToNBT(tag);
		tag.setInteger("essences", this.essences);
    }

}
