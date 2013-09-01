package redmagic.handlers;

import redmagic.client.guis.talent.GuiTalentPage;
import redmagic.configuration.PathIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.path.PathManager;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import redmagic.network.PacketHandler;
import redmagic.network.PacketSyncPlayerInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PlayerHandler implements IPlayerTracker{

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PlayerInformation info = PlayerInformationHelper.load(player);
		PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketSyncPlayerInformation(info)), (Player) player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		PlayerInformationHelper.save(player);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		PlayerInformationHelper.save(player);
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		PlayerInformationHelper.save(player);
	}

}
