package redmagic.lib.path;

import java.util.HashMap;
import java.util.Iterator;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import redmagic.api.talent.IUnlockable;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.abilities.Ability;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import redmagic.network.PacketHandler;
import redmagic.network.PacketSyncPlayerInformation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;

public class Path implements IUnlockable{

	
	public HashMap<String, Ability> abilities = new HashMap<String, Ability>();
	public String name;
	
	
	protected void registerAbility(String name, Ability ability){
		abilities.put(name, ability);
	}
	
	
	public void syncClient(PlayerInformation info, Player player){
		PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketSyncPlayerInformation(info)), player);
	}
	
	
	
	
	
	public void readFromNBT(NBTTagCompound tag){
		Iterator<String> it = abilities.keySet().iterator();
		while(it.hasNext()){
			String name = it.next();
			Ability ability = abilities.get(name);
			NBTTagCompound abilityTag = tag.getCompoundTag(name);
			ability.readFromNBT(abilityTag);
		}
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setString("pathname", name);
		Iterator<String> it = abilities.keySet().iterator();
		while(it.hasNext()){
			String name = it.next();
			Ability ability = abilities.get(name);
			NBTTagCompound abilityTag = new NBTTagCompound();
			ability.writeToNBT(abilityTag);
			tag.setTag(name, abilityTag);
		}
	}



	public boolean hasAbilityUnlocked(String name) {
		Ability ability = abilities.get(name);
		if(ability != null)return ability.isUnlocked();
		return false;
	}

	public boolean isAbilityUnlockable(String name){
		Ability ability = abilities.get(name);
		if(ability != null)return ability.isUnlockable();
		return false;
	}





	/**
	 * ----------------------------------------------------------------------------------------------
	 * IUnlockable
	 */
	@Override
	public void unlock(EntityPlayer player, boolean byClick) {
		PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
		if(information != null){
			information.pathManager.choosePath(name);
			PlayerInformationHelper.setPlayerInformation(information, player.worldObj);
		}
		
	}
	
	@Override
	public String getHelp() {
		return StatCollector.translateToLocal("talent." + this.name + ".help");
	}
	
	// ----------------------------------------------------------------------------------------------
	
}
