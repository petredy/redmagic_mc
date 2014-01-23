package com.petredy.redmagic.network.packets;

import com.petredy.redmagic.lib.DataPackets;
import com.petredy.redmagic.utils.LogUtils;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class KeyPressedDataPacket extends DataPacket{
	
	public int keyCode;
	
	public KeyPressedDataPacket(){
		super(DataPackets.KEY_PRESSED);
	}
	
	public KeyPressedDataPacket(int keyCode){
		super(DataPackets.KEY_PRESSED);
		this.keyCode = keyCode;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.keyCode = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeInt(keyCode);
	}
	
	@Override
	public DataPacket execute(){
		
		LogUtils.log("pressed key " + keyCode);
		
		return null;
	}
	
}
