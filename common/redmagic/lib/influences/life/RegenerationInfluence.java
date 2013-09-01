package redmagic.lib.influences.life;

import redmagic.Redmagic;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.influences.PassiveInfluence;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.potions.RedmagicPotion;
import redmagic.lib.talent.Talent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RegenerationInfluence extends PassiveInfluence{

	@Override
	public void act(World world, EntityPlayer player){
		PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
		if(information.pathManager.hasPath() && information.pathManager.path.name.equals(Talent.life.unlockableName) && information.pathManager.isTalentUnlocked(Talent.selfHeal)){
			player.addPotionEffect(new PotionEffect(Redmagic.redmagicPotion.id, 10, 1));
		}
	}
	
	
}
