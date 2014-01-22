package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.tileentities.TileEntityMachine;

import cpw.mods.fml.common.network.Player;

public class PacketUpdateMachineOnSide extends PacketRedmagic {

	public int machine, side, x, y , z;
	public boolean set;
	
	public PacketUpdateMachineOnSide() {
		super(Packets.UPDATE_MACHINE_ON_SIDE, false);
	}
	
	public PacketUpdateMachineOnSide(int x, int y, int z, int side, int machine, boolean set){
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.side = side;
		this.machine = machine;
		this.set = set;
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.side = data.readInt();
		this.machine = data.readInt();
		this.set = data.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
		data.writeInt(side);
		data.writeInt(machine);
		data.writeBoolean(set);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		IMachineHandler handler = (IMachineHandler) thePlayer.worldObj.getBlockTileEntity(x, y, z);
		if(handler != null){
			if(set){
				handler.addMachine(machine, side, thePlayer);
			}else{
				handler.removeMachine(side, thePlayer);
			}
		}
	}

}
