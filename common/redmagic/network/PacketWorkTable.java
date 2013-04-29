package redmagic.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;

import redmagic.configuration.PacketIndex;
import redmagic.tileentities.TileEntityWorkTable;

public class PacketWorkTable extends PacketRedMagic{

	public int craftingIndex = 0, x, y, z;
	
	public PacketWorkTable() {
		super(PacketIndex.WORK_TABLE, false);
	}
	
	public PacketWorkTable(int x, int y, int z, int craftingIndex) {
		super(PacketIndex.WORK_TABLE, false);
		this.craftingIndex = craftingIndex;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(this.craftingIndex);
		data.writeInt(this.x);
		data.writeInt(this.y);
		data.writeInt(this.z);
	}
	
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		this.craftingIndex = data.readInt();
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;
		TileEntity entity = thePlayer.worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof TileEntityWorkTable){
			TileEntityWorkTable table = (TileEntityWorkTable)entity;
			table.craftingIndex += this.craftingIndex;
			if(table.craftingIndex < 0)table.craftingIndex = 0;
			if(table.craftingIndex > table.getMaxCraftingIndex())table.craftingIndex = table.getMaxCraftingIndex();
			table.showCrafting();
		}
	}

}
