package com.petredy.redmagic.network.packets;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class DataPacket {

	public int type;
	
	public DataPacket(){
		
	}
	
	public DataPacket(int type){
		this.type = type;
	}
	
	public void fromBytes(ByteBuf buf) {
		this.type = buf.readInt();
	}

	public void toBytes(ByteBuf buf) {
		buf.writeInt(type);
	}
	
	public DataPacket execute(){
		return null;
	}
	
}
