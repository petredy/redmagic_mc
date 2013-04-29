package redmagic.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

import cpw.mods.fml.common.network.Player;

public class PacketRedMagic extends Packet{
	
	protected byte packetType;
	protected boolean isChunkDataPacket;

	public PacketRedMagic(byte packetType, boolean isChunkDataPacket) {
		this.packetType = packetType;
		this.isChunkDataPacket = isChunkDataPacket;
	}
	
	public byte[] populate() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try {
			dos.writeByte(this.packetType);
			this.writePacketData(dos);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

		return bos.toByteArray();
	}

	public void readPopulate(DataInputStream data) {
		try {
			this.readPacketData(data);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	@Override
	public void readPacketData(DataInputStream var1) throws IOException {}
	@Override
	public void writePacketData(DataOutputStream var1) throws IOException {}
	@Override
	public void processPacket(NetHandler var1) {}
	@Override
	public int getPacketSize() {return 0;}

	public void execute(INetworkManager manager, Player player) {
		
	}

}
