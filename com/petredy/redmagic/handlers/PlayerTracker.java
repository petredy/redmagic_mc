package com.petredy.redmagic.handlers;

import com.petredy.redmagic.utils.PlayerUtils;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker{

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PlayerUtils.load(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		PlayerUtils.setPlayerInformation(player, PlayerUtils.load(player));
	}

}
