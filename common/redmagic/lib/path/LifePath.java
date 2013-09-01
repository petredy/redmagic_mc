package redmagic.lib.path;

import cpw.mods.fml.common.network.Player;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.PathIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.abilities.Ability;
import redmagic.lib.abilities.AltarAbility;
import redmagic.lib.abilities.ConstructionTableAbility;
import redmagic.lib.abilities.life.*;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class LifePath extends Path{

	
	
	public LifePath(){ 
		this.registerAbility(AbilityIndex.ALTAR + "." + PathIndex.LIFE, new AltarAbility(PathIndex.LIFE));
		this.registerAbility(AbilityIndex.CONSTRUCTION_TABLE + "." + PathIndex.LIFE, new ConstructionTableAbility(PathIndex.LIFE));
		this.registerAbility(AbilityIndex.SELFHEAL, new SelfHealAbility());
		this.name = PathIndex.LIFE;
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
	public void unlock(EntityPlayer player, boolean byClick) {
		super.unlock(player, byClick);
		PlayerInformation info = PlayerInformationHelper.getPlayerInformation(player);
		info.pathManager.setTalentUnlockable(Talent.lifeAltar);
		PlayerInformationHelper.setPlayerInformation(info, player.worldObj);
		
		this.syncClient(info, (Player)player);
	}
}
