package redmagic.lib.abilities.life;

import net.minecraft.entity.player.EntityPlayer;
import redmagic.configuration.AbilityIndex;
import redmagic.handlers.TalentRenderHandler;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.abilities.Ability;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class SelfHealAbility extends Ability{

	
	
	public SelfHealAbility() {
		this.name = AbilityIndex.SELFHEAL;
	}
	
	@Override
	public void unlock(EntityPlayer player, boolean byClick) {
		super.unlock(player, byClick);
		TalentRenderHandler.guiTalent.queueTakenTalent(Talent.lifeSelfHeal);
	}

}
