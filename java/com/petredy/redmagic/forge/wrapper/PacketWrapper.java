package com.petredy.redmagic.forge.wrapper;

import com.petredy.redmagic.lib.Reference;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;

public class PacketWrapper extends FMLProxyPacket{

	public PacketWrapper(S3FPacketCustomPayload original)
    {
        super(original);
    }

    public PacketWrapper(C17PacketCustomPayload original)
    {
    	super(original);
    }
    
    
}
