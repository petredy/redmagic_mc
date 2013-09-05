package redmagic.lib.abilities;

import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import redmagic.api.talent.IUnlockable;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.PathIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class ConstructionTableAbility extends Ability{

	public String path = null;
	
	public ConstructionTableAbility(String path) {
		this.path = path;
		this.name = AbilityIndex.CONSTRUCTION_TABLE + "." + path;
	}
	
	@Override
	public void unlock(EntityPlayer player, boolean byClick) {
		if(!byClick){
			PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
			if(path.equals(PathIndex.LIFE)){
				information.pathManager.setTalentUnlocked(Talent.lifeConstructionTable);
				information.pathManager.setTalentUnlockable(Talent.lifeSelfHeal);
				information.pathManager.setTalentUnlockable(Talent.lifeExorcism);
			}
			if(path.equals(PathIndex.EARTH)){
				information.pathManager.setTalentUnlocked(Talent.earthConstructionTable);
				information.pathManager.setTalentUnlockable(Talent.earthCompress);
			}
			if(path.equals(PathIndex.WATER)){
				information.pathManager.setTalentUnlocked(Talent.waterConstructionTable);
			}
			PlayerInformationHelper.setPlayerInformation(information, player.worldObj);
			this.syncClient(information, (Player) player);
		}
	}

}
