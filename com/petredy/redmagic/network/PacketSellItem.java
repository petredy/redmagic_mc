package com.petredy.redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.tileentities.TileEntityTradingChest;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.TradingUtils;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketSellItem extends PacketRedMagic {

	public ItemStack stack;
	public int amount, x, y, z;
	
	public PacketSellItem() {
		super(Packets.SELL, false);
	}

	public PacketSellItem(ItemStack stack, int amount, int xCoord, int yCoord, int zCoord) {
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
		EntityPlayer thePlayer = (EntityPlayer) player;
		float money = TradingManager.getItemPrice(stack) * amount;
		if(TradingManager.addItemAmount(stack, amount)){
			TileEntityTradingChest bank = (TileEntityTradingChest) thePlayer.worldObj.getBlockTileEntity(x, y, z);
			ItemStack crystal = bank.getStackInSlot(0);
			if(crystal != null){
				if(thePlayer.inventory.getItemStack() == null)InventoryUtils.reduceItemStack(thePlayer.inventory, stack, amount);
				else thePlayer.inventory.setItemStack(null);
				TradingUtils.setMoney(crystal, TradingUtils.getMoney(crystal) + money);
				PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketTradingSync(TradingManager.getData())));
			}
		}
	}
}
