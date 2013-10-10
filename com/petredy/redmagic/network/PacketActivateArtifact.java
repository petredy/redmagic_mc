package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;
import com.petredy.redmagic.utils.ArtifactUtils;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketActivateArtifact extends PacketRedmagic{

	public int x, y, z;
	public String name;
	
	public PacketActivateArtifact() {
		super(Packets.ACTIVATE_ARTIFACT, false);
	}
	
	public PacketActivateArtifact(int x, int y, int z, String name) {
		super(Packets.ACTIVATE_ARTIFACT, false);
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.name = data.readUTF();
	}
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
		data.writeUTF(name);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		TileEntity tile = thePlayer.worldObj.getBlockTileEntity(x, y, z);
		if(tile instanceof TileEntityKnowledgeTransceiver){
			TileEntityKnowledgeTransceiver entity = (TileEntityKnowledgeTransceiver)tile;
			if(entity.artifact != null){
				ArtifactUtils.setKnowledge(entity.artifact, KnowledgeSystem.getKnowledge(name));
			}
		}
	}
}