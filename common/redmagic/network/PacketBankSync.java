package redmagic.network;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import redmagic.Redmagic;
import redmagic.api.items.IKeyBound;
import redmagic.configuration.PacketIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.lib.bank.BankData;

import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

public class PacketBankSync extends PacketRedMagic {

	public BankData data;

	public PacketBankSync() {
		super(PacketIndex.BANK, false);
	}

	public PacketBankSync(BankData data) {
		super(PacketIndex.BANK, false);
		this.data = data;
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		if(this.data != null){
			NBTTagCompound tag = new NBTTagCompound();
			this.data.writeToNBT(tag);
			this.writeNBTTagCompound(tag, data);
		}
	}
	
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		NBTTagCompound tag = this.readNBTTagCompound(data);
		if(tag != null && tag.hasKey(Reference.MOD_ID)){
			this.data = new BankData();
			this.data.readFromNBT(tag);
		}
	}

	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		Logger.log("sync BankData");
		if(this.data != null)Redmagic.bankData = this.data;
		
	}
}