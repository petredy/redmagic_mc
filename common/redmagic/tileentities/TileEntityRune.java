package redmagic.tileentities;

import redmagic.helpers.LogHelper;
import redmagic.lib.rune.Marker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRune extends TileEntity{

	
	protected Marker[] markers = new Marker[16];
	public int side;
	
	public TileEntityRune(){
	}
	
	
	public Marker[] getMarkers() {
		return markers;
	}
	
	public void setMarker(int index, int id){
		LogHelper.log("set " + index);
		this.markers[index] = new Marker(id);
	}
	
	public boolean isMarker(int index){
		return this.markers[index] != null;
	}
	
	public int getMarkerId(int index){
		if(this.isMarker(index)){
			return this.markers[index].id;
		}
		return -1;
	}
	
	public void removeMarker(int index){
		this.markers[index] = null;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		NBTTagList list = tag.getTagList("redmagic.markers");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound marker = (NBTTagCompound) list.tagAt(i);
			if(marker != null){
				byte id = marker.getByte("index");
				markers[id] = Marker.loadFromNBT(marker);
			}
		}
		this.side = tag.getInteger("redmagic.side");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < markers.length; i++){
			if(markers[i] != null){
				NBTTagCompound marker = new NBTTagCompound();
				marker.setByte("index", (byte) i);
				markers[i].writeToNBT(marker);
				list.appendTag(marker);
			}
		}
		tag.setTag("redmagic.markers", list);
		tag.setInteger("redmagic.side", this.side);
	}
	
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.customParam1);
    }
}
