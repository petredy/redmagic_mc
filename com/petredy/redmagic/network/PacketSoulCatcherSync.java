package com.petredy.redmagic.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;

import com.petredy.redmagic.lib.Packets;
import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcher;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

import cpw.mods.fml.common.network.Player;

public class PacketSoulCatcherSync extends PacketNBTSync {
	
	public PacketSoulCatcherSync() {
		super(Packets.SYNC_SOUL_CATCHER);
	}
	
	public PacketSoulCatcherSync(NBTTagCompound data) {
		super(Packets.SYNC_SOUL_CATCHER);
		this.data = data;
	}

	public void execute(INetworkManager manager, Player player) {
		NBTTagCompound structureTag = (NBTTagCompound) data.getTag("structure");
		boolean delete = data.getBoolean("delete");
		int x = data.getInteger("x");
		int y = data.getInteger("y");
		int z = data.getInteger("z");
		TileEntitySoulCatcher entity = (TileEntitySoulCatcher) ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);
		if(entity != null){
			StructureSoulCatcher structure = StructureSoulCatcher.loadFromNBT(structureTag);
			if(delete)structure.id = -1;
			entity.setStructure(structure);
			WorldSavedDataUtils.saveData(entity.worldObj, StructureSoulCatcher.TOKEN_PREFIX + structure.id, structureTag);
			entity.worldObj.markBlockForUpdate(x, y, z);
		}
	}
}
