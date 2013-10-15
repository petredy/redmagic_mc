package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.structure.Structure;
import com.petredy.redmagic.utils.StructureUtils;

import cpw.mods.fml.common.network.Player;

public class PacketStructureSync extends PacketNBTSync{

	
	public PacketStructureSync(){
		super(Packets.STRUCTURE_SYNC);
	}
	
	public PacketStructureSync(NBTTagCompound tag){
		this();
		this.data = tag;
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		Structure structure = new Structure();
		structure.readFromNBT(data);
		StructureUtils.saveStructure(thePlayer.worldObj, structure);
	}
	
}
