package redmagic.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import redmagic.configuration.PacketIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class PacketTalentUnlocked extends PacketRedMagic{
	public String talent;

	public PacketTalentUnlocked() {
		super(PacketIndex.TALENT_UNLOCKED, false);
	}

	public PacketTalentUnlocked(String talent) {
		super(PacketIndex.TALENT_UNLOCKED, false);
		this.talent = talent;
	}

	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeUTF(talent);
	}
	
	@Override
	public void readPacketData(DataInput data) throws IOException {
		this.talent = data.readUTF();
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer)player;
		for(Talent talent: Talent.talents){
			if(talent.name.equals(this.talent)){
				talent.unlockable.unlock(thePlayer, true);
				break;
			}
		}
	}
}
