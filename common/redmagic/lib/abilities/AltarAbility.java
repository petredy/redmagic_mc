package redmagic.lib.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import redmagic.api.talent.IUnlockable;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.PathIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class AltarAbility extends Ability{

	public String path = null;
	
	public AltarAbility(String path) {
		this.path = path;
		this.name = AbilityIndex.ALTAR + "." + path;
	}
	
	@Override
	public void unlock(EntityPlayer player) {
		
	}
	
	public void unlockAltar(EntityPlayer player){
		PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
		if(path.equals(PathIndex.LIFE)){
			information.pathManager.setTalentUnlocked(Talent.lifeAltar);
			information.pathManager.setTalentUnlockable(Talent.selfHeal);
		}
		if(path.equals(PathIndex.EARTH)){
			information.pathManager.setTalentUnlocked(Talent.earthAltar);
			information.pathManager.setTalentUnlockable(Talent.compress);
		}
		if(path.equals(PathIndex.WATER)){
			information.pathManager.setTalentUnlocked(Talent.waterAltar);
		}
		PlayerInformationHelper.setPlayerInformation(information, player.worldObj);
		
	}

}
