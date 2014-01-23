package com.petredy.redmagic.network;

import com.petredy.redmagic.lib.DataPackets;
import com.petredy.redmagic.network.packets.DataPacket;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageRedmagic implements IMessage{

	public DataPacket packet = new DataPacket();
	
	public MessageRedmagic(DataPacket packet){
		this.packet = packet;
	}

	
	@Override
	public void fromBytes(ByteBuf buf) {
		/*
		ByteBuf data = buf.copy();
		packet = new DataPacket();
		packet.fromBytes(buf);
		try{
			packet = (DataPacket) DataPackets.PACKETS[packet.type].newInstance();
			packet.fromBytes(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
	}

	@Override
	public void toBytes(ByteBuf buf) {
		/*
		packet.toBytes(buf);
		*/
	}
	
	public MessageRedmagic execute(){
		DataPacket response = packet.execute();
		return new MessageRedmagic(response);
	}

	public String toString(){
		return "MessageRedmagic: Contains => " + DataPackets.PACKETS[packet.type].getSimpleName();
	}
}
