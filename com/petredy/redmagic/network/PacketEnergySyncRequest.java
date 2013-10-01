package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.Redkey;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketEnergySyncRequest extends PacketRedmagic {

	public int x, z, dimension;
	public float amount;
	public boolean request;
	
	public PacketEnergySyncRequest() {
		super(Packets.REQUEST_ENERGY_SYNC, false);
	}
	
	public PacketEnergySyncRequest(int dimension, int x, int z, float amount, boolean request) {
		this();
		this.x = x;
		this.z = z;
		this.dimension = dimension;
		this.amount = amount;
		this.request = request;
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		dimension = data.readInt();
		x = data.readInt();
		z = data.readInt();
		amount = data.readFloat();
		request = data.readBoolean();
	}
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(dimension);
		data.writeInt(x);
		data.writeInt(z);
		data.writeFloat(amount);
		data.writeBoolean(request);
	}

	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		if(request){
			RedEnergy en = EnergyMap.getEnergy(Redkey.get(thePlayer.worldObj.provider.dimensionId, x, z));
			PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketEnergySyncRequest(thePlayer.worldObj.provider.dimensionId, x, z, (en != null ? en.amount : 0), false)), player);
		}else{
			EnergyMap.setEnergy(new RedEnergy(thePlayer.worldObj.provider.dimensionId, x, z, amount));
		}
	}
	
}
