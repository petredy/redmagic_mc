package com.petredy.redmagic.handlers;

import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketTradingSync;
import com.petredy.redmagic.player.PlayerInformation;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.PlayerUtils;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PlayerTracker implements IPlayerTracker{

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
		PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketTradingSync(TradingManager.getData())), (Player) player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
		PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketTradingSync(TradingManager.getData())), (Player) player);
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
		PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketTradingSync(TradingManager.getData())), (Player) player);
	}

}
