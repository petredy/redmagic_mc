package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.Player;

public class PacketBuyItem extends PacketRedMagic {

	public ItemStack stack;
	public int amount, x, y, z;
	
	public PacketBuyItem() {
		super(Packets.BUY, false);
	}

	public PacketBuyItem(ItemStack stack, int amount, int xCoord, int yCoord, int zCoord) {
		this();
		this.stack = stack;
		this.amount = amount;
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
	}

	@Override
	public void readPacketData(DataInput data) throws IOException {
		NBTTagCompound tag = this.readNBTTagCompound(data);
		this.stack = ItemStack.loadItemStackFromNBT(tag);
		this.amount = data.readInt();
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeNBTTagCompound(tag, data);
		data.writeInt(amount);
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
	}
	
	public void execute(INetworkManager manager, Player player) {
		LogUtils.log("buy");
	}
	
}
