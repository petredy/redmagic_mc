package redmagic.lib.path;

import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.PathIndex;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.abilities.AltarAbility;
import redmagic.lib.abilities.life.SelfHealAbility;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class WaterPath extends Path{

	public WaterPath(){
		this.registerAbility(AbilityIndex.ALTAR + "." + PathIndex.WATER, new AltarAbility(PathIndex.WATER));
		this.name = PathIndex.WATER;
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
		info.pathManager.setTalentUnlockable(Talent.waterAltar);
		PlayerInformationHelper.setPlayerInformation(info, player.worldObj);
		
		this.syncClient(info, (Player)player);
	}
}
