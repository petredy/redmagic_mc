package com.petredy.redmagic.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.LogUtils;


import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		PacketRedmagic packetRM = PacketHandler.buildPacket(packet.data);
		
		packetRM.execute(manager, player);
	}
	@SuppressWarnings("unchecked")
	public static PacketRedmagic buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		PacketRedmagic packet = null;
		try {
			packet = (PacketRedmagic) Packets.PACKETS[selector].getConstructor().newInstance();
			LogUtils.log("Build new " + Packets.PACKETS[selector].getSimpleName());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}
	
	public static Packet populatePacket(PacketRedmagic packetRM) {
		byte[] data = packetRM.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.PACKET_CHANNEL;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetRM.isChunkDataPacket;

		return packet250;
	}
	
	
}
