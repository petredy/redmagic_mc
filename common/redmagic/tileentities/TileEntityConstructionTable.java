package redmagic.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityConstructionTable extends TileEntityInventory{

	public int side;
	
	
	public int[] craftingSideNorth = new int[]{
		0, 1, 2,
		3, 4, 5,
		6, 7, 8
	};
	
	public int[] craftingSideEast = new int[]{
		2, 5, 8,
		1, 4, 7,
		0, 3, 6
	};
	
	public int[] craftingSideSouth = new int[]{
		8, 7, 6,
		5, 4, 3,
		2, 1, 0
	};
	
	public int[] craftingSideWest = new int[]{
		6, 3, 0,
		7, 4, 1,
		8, 5, 2
	};
	
	public TileEntityConstructionTable() {
		super(9, TileEntityConstructionTable.class.getSimpleName());
	}
	
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		this.readFromNBT(pkt.customParam1);
    }
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.side = tag.getInteger("side");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("side", this.side);
	}
	
}
