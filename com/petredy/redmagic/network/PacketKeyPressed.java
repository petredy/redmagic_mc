package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.petredy.redmagic.api.IKeyBound;
import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

public class PacketKeyPressed extends PacketRedMagic {

	public String key;

	public PacketKeyPressed() {
		super(Packets.KEY, false);
	}

	public PacketKeyPressed(String key) {
		super(Packets.KEY, false);
		this.key = key;
	}

	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeUTF(key);
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.key = data.readUTF();
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		LogUtils.log("execute KeyPressed");
		if ((thePlayer.getCurrentEquippedItem() != null) && (thePlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound)) {
		    ((IKeyBound) thePlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(thePlayer, thePlayer.getCurrentEquippedItem(), this.key);
		}else{
			for(int i = 0; i < thePlayer.inventory.armorInventory.length; i++){
				if(thePlayer.inventory.armorInventory[i] != null && thePlayer.inventory.armorInventory[i].getItem() instanceof IKeyBound){
					((IKeyBound)thePlayer.inventory.armorInventory[i].getItem()).doKeyBindingAction(thePlayer, thePlayer.inventory.armorInventory[i], this.key);
					break;
				}
			}
		}
	}
}