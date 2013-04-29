package redmagic.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketHelper {

	
	public static Packet getServerUpdatePaket(int[] data)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bytes);

		try
		{
			for (int i = 0; i < data.length; i++)
			{
				dos.writeInt(data[i]);
			}

		} catch (IOException e)
		{
		}

		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "RedMagic_Server";
		pkt.data = bytes.toByteArray();
		pkt.length = bytes.size();
		pkt.isChunkDataPacket = true;

		return pkt;
	}
	
	public static Packet getClientUpdatePaket(int[] data)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bytes);

		try
		{
			for (int i = 0; i < data.length; i++)
			{
				dos.writeInt(data[i]);
			}

		} catch (IOException e)
		{
		}

		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "RedMagic_Client";
		pkt.data = bytes.toByteArray();
		pkt.length = bytes.size();
		pkt.isChunkDataPacket = true;

		return pkt;
	}
	
	public static void sendPacketToClosestPlayers(int x, int y, int z, int dimension, int[] data){
		PacketDispatcher.sendPacketToAllAround(x, y, z, 256, dimension, getClientUpdatePaket(data));
	}
	
	public static void sendPacketToServer(int[] data){
		PacketDispatcher.sendPacketToServer(getServerUpdatePaket(data));
	}

}
