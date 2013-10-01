package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.Redkey;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketEnergySyncRequest extends PacketRedmagic {

	public int x, y, z;
	public float amount;
	public boolean request;
	
	public PacketEnergySyncRequest() {
		super(Packets.REQUEST_ENERGY_SYNC, false);
	}
	
	public PacketEnergySyncRequest(int x, int y, int z, float amount, boolean request) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.amount = amount;
		this.request = request;
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();
		amount = data.readFloat();
		request = data.readBoolean();
	}
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
		data.writeFloat(amount);
		data.writeBoolean(request);
	}

	public void execute(INetworkManager manager, Player player) {
		if(request){
			RedEnergy en = EnergyMap.getEnergy(Redkey.get(x, y, z));
			PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketEnergySyncRequest(x, y, z, (en != null ? en.amount : 0), false)), player);
		}else{
			EnergyMap.setEnergy(new RedEnergy(x, y, z, amount));
		}
	}
	
}
