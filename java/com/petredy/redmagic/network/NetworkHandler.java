package com.petredy.redmagic.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

import com.petredy.redmagic.forge.wrapper.PacketWrapper;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.network.packets.DataPacket;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleChannelHandlerWrapper;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import cpw.mods.fml.common.network.NetworkEventFiringHandler;

public class NetworkHandler {

	protected static SimpleNetworkWrapper network;
	
	public static void init(){
		 network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		 network.registerMessage(ServerHandler.class, MessageRedmagic.class, 1, Side.SERVER);
		 network.registerMessage(ClientHandler.class, MessageRedmagic.class, 2, Side.CLIENT);
		 LogUtils.log("Initialised Redmagic Networking");
	}
	
	public static void sendTo(DataPacket packet, EntityPlayerMP player){
		network.sendTo(new MessageRedmagic(packet), player);
	}
	
	public static void sendToAll(DataPacket packet){
		network.sendToAll(new MessageRedmagic(packet));
	}
	
	public static void sendToAll(DataPacket packet, TargetPoint point){
		network.sendToAllAround(new MessageRedmagic(packet), point);
	}
	
	public static void sendToDimension(DataPacket packet, int dimension){
		network.sendToDimension(new MessageRedmagic(packet), dimension);
	}
	
	public static void sendToServer(DataPacket packet){
		network.sendToServer(new MessageRedmagic(packet));
	}
	
	public class ClientHandler implements IMessageHandler<MessageRedmagic, MessageRedmagic>{

		@Override
		public MessageRedmagic onMessage(MessageRedmagic message, MessageContext ctx) {
			LogUtils.log(message);
			return null;
		}

		
		
	}
	
	public class ServerHandler implements IMessageHandler<MessageRedmagic, MessageRedmagic>{

		@Override
		public MessageRedmagic onMessage(MessageRedmagic message, MessageContext ctx) {
			LogUtils.log(message);
			return null;
		}

		
	}

}
