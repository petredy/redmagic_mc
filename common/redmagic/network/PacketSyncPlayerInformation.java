package redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import redmagic.configuration.PacketIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class PacketSyncPlayerInformation extends PacketRedMagic{

	public PlayerInformation info;
	
	public PacketSyncPlayerInformation(){
		super(PacketIndex.MANAGE_PLAYER_INFORMATION, false);
	}
	
	public PacketSyncPlayerInformation(PlayerInformation info){
		super(PacketIndex.MANAGE_PLAYER_INFORMATION, false);
		this.info = info;
	}
	
	
	@Override
	public void writePacketData(DataOutput data) throws IOException {
		NBTTagCompound tag = new NBTTagCompound();
		this.info.writeToNBT(tag);
		PacketHelper.writeToNBT(tag, data);
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		NBTTagCompound tag = PacketHelper.readFromNBT(data);
		this.info = PlayerInformation.loadFromNBT(tag);
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		PlayerInformationHelper.setPlayerInformation(this.info, thePlayer.worldObj);
	}
}
