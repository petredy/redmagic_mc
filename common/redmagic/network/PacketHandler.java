package redmagic.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import redmagic.configuration.PacketIndex;
import redmagic.configuration.Reference;


import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals(Reference.PACKET_CHANNEL_SERVER)) {
			PacketRedMagic packetRM = PacketHandler.buildPacket(packet.data);
	    	
			packetRM.execute(manager, player);
		}
	}
	@SuppressWarnings("unchecked")
	public static PacketRedMagic buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		PacketRedMagic packet = null;
		try {
			packet = (PacketRedMagic) PacketIndex.PACKETS[selector].getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}
	
	public static Packet populatePacket(PacketRedMagic packetRM) {
		byte[] data = packetRM.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.PACKET_CHANNEL_SERVER;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetRM.isChunkDataPacket;

		return packet250;
	}
	
	
}
