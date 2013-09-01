package redmagic.lib.path;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.PathIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.abilities.AltarAbility;
import redmagic.lib.abilities.earth.*;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import redmagic.network.PacketHandler;
import redmagic.network.PacketSyncPlayerInformation;

public class EarthPath extends Path{
	
	public EarthPath(){
		this.registerAbility(AbilityIndex.ALTAR + "." + PathIndex.EARTH, new AltarAbility(PathIndex.EARTH));
		this.registerAbility(AbilityIndex.COMPRESS, new CompressAbility());
		this.name = PathIndex.EARTH;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
	}	
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
	}
	
	@Override
	public void unlock(EntityPlayer player) {
		super.unlock(player);
		
		PlayerInformation info = PlayerInformationHelper.getPlayerInformation(player);
		info.pathManager.setTalentUnlockable(Talent.earthAltar);
		PlayerInformationHelper.setPlayerInformation(info, player.worldObj);
		
		this.syncClient(info, (Player)player);
		
	}
	
}
