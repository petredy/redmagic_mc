package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.lib.Packets;

import cpw.mods.fml.common.network.Player;

public class PacketMachineSync extends PacketRedmagic{

	public int x, y, z;
	public float energy;
	
	public PacketMachineSync() {
		super(Packets.MACHINE_SYNC, false);
	}
	
	public PacketMachineSync(int x, int y, int z, float energy){
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.energy = energy;
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.energy = data.readFloat();
	}
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
		data.writeFloat(energy);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		IEnergyHandler handler = (IEnergyHandler) thePlayer.worldObj.getBlockTileEntity(x, y, z);
		if(handler != null){
			handler.use(handler.getStoredEnergy());
			handler.store(energy);
		}
	}

}
