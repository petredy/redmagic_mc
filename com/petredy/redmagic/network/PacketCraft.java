package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.tileentities.TileEntityCrafter;

import cpw.mods.fml.common.network.Player;

public class PacketCraft extends PacketRedmagic {

	public int x, y, z;
	
	public PacketCraft() {
		super(Packets.CRAFT, false);
	}
	
	public PacketCraft(int x, int y, int z) {
		super(Packets.CRAFT, false);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}
	
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		TileEntity entity = thePlayer.worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof TileEntityCrafter){
			((TileEntityCrafter)entity).craft();
		}
	}

}
