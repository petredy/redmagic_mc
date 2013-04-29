package redmagic.network;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import redmagic.api.essence.IStorage;
import redmagic.configuration.PacketIndex;

import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;

public class PacketEnergyStorage extends PacketRedMagic {

	public int essences, x, y, z;

	public PacketEnergyStorage() {
		super(PacketIndex.ENERGY_STORAGE, false);
	}

	public PacketEnergyStorage(int essences, int x, int y, int z) {
		super(PacketIndex.ENERGY_STORAGE, false);
		this.essences = essences;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(this.essences);
		data.writeInt(this.x);
		data.writeInt(this.y);
		data.writeInt(this.z);
	}
	
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		this.essences = data.readInt();
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}

	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		TileEntity entity = thePlayer.worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof IStorage){
			IStorage storage = (IStorage)entity;
			storage.extract(storage.getEssences());
			storage.store(essences);
		}
	}
}