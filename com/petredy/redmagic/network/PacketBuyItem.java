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
		stack.writeToNBT(tag);
		this.writeNBTTagCompound(tag, data);
		data.writeInt(amount);
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		float costs = TradingManager.getItemPrice(stack) * amount;
		if(TradingManager.removeItemAmount(stack, amount)){
			thePlayer.inventory.setItemStack(stack);
			TileEntityTradingChest bank = (TileEntityTradingChest) thePlayer.worldObj.getBlockTileEntity(x, y, z);
			ItemStack crystal = bank.getStackInSlot(0);
			if(!thePlayer.inventory.addItemStackToInventory(stack))InventoryUtils.dropItemStack(stack, thePlayer.worldObj,  thePlayer.posX, thePlayer.posY, thePlayer.posZ);
			if(crystal != null){
				TradingUtils.setMoney(crystal, TradingUtils.getMoney(crystal) - costs);
				PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketTradingSync(TradingManager.getData())));
				thePlayer.inventory.setItemStack(null);
			}
		}else{
			amount = TradingManager.getItemAmount(stack);
			if(TradingManager.removeItemAmount(stack, amount)){
				costs = TradingManager.getItemPrice(stack) * amount;
				thePlayer.inventory.setItemStack(stack);
				TileEntityTradingChest bank = (TileEntityTradingChest) thePlayer.worldObj.getBlockTileEntity(x, y, z);
				ItemStack crystal = bank.getStackInSlot(0);
				if(!thePlayer.inventory.addItemStackToInventory(stack))InventoryUtils.dropItemStack(stack, thePlayer.worldObj,  thePlayer.posX, thePlayer.posY, thePlayer.posZ);
				if(crystal != null){
					TradingUtils.setMoney(crystal, TradingUtils.getMoney(crystal) - costs);
					PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketTradingSync(TradingManager.getData())));
					thePlayer.inventory.setItemStack(null);
				}
			}
		}
	}
	
}
