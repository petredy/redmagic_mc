package redmagic.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySoulBlock extends TileEntity{
	public int[] blockID = new int[6];
	public int[] blockMetadata = new int[6];
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		
		this.blockID = tag.getIntArray("redmagic.id");
		this.blockMetadata = tag.getIntArray("redmagic.metadata");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		
		tag.setIntArray("redmagic.id", this.blockID);
		tag.setIntArray("redmagic.metadata", blockMetadata);
	}
	
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
    }
}
