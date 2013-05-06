package redmagic.network;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import redmagic.api.items.IKeyBound;
import redmagic.configuration.PacketIndex;
import redmagic.core.Logger;
import redmagic.helpers.BankHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.lib.bank.BankManager;
import redmagic.tileentities.bank.TileEntityBank;

import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;

public class PacketBuyItem extends PacketRedMagic {

	public int id, damage, amount, x, y, z;

	public PacketBuyItem() {
		super(PacketIndex.BUY, false);
	}

	public PacketBuyItem(int id, int damage, int amount, int x, int y, int z) {
		super(PacketIndex.BUY, false);
		this.id = id;
		this.damage = damage;
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(id);
		data.writeInt(damage);
		data.writeInt(amount);
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
	}
	
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		this.id = data.readInt();
		this.damage = data.readInt();
		this.amount = data.readInt();
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}

	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		Logger.log("buy an item");
		ItemStack stack = new ItemStack(id, amount, damage);
		float costs = BankManager.getItemPrice(id, damage) * amount;
		if(BankManager.removeItemAmount(id, damage, amount)){
			thePlayer.inventory.setItemStack(stack);
			TileEntityBank bank = (TileEntityBank) thePlayer.worldObj.getBlockTileEntity(x, y, z);
			ItemStack crystal = bank.getStackInSlot(0);
			InventoryHelper.addItemStackToInventory(thePlayer.inventory, stack);
			if(crystal != null){
				BankHelper.setMoney(crystal, BankHelper.getMoney(crystal) - costs);
			}
		}
	}
}