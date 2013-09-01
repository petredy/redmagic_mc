package redmagic.lib.abilities;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import redmagic.api.talent.IUnlockable;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.player.PlayerInformation;

public class Ability implements IUnlockable {

	protected boolean unlocked;
	protected boolean unlockable;
	public String name;
	
	public Ability(){
		unlocked = false;
		unlockable = false;
	}
	
	public boolean isUnlocked(){
		return unlocked;
	}
	
	public boolean isUnlockable(){
		return unlockable;
	}
	
	public void setUnlockable(){
		unlockable = true;
	}
	
	public void setUnlocked(){
		unlocked = true;
	}
	
	
	public void readFromNBT(NBTTagCompound tag){
		this.unlockable = tag.getBoolean("unlockable");
		this.unlocked = tag.getBoolean("unlocked");
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("unlockable", this.unlockable);
		tag.setBoolean("unlocked", this.unlocked);
	}
	
	

	@Override
	public void unlock(EntityPlayer player) {
		PlayerInformation info = PlayerInformationHelper.getPlayerInformation(player);
		info.pathManager.path.abilities.get(this.name).setUnlocked();
		PlayerInformationHelper.setPlayerInformation(info, player.worldObj);
	}

	@Override
	public String getHelp() {
		return StatCollector.translateToLocal("talent." + this.name + ".help");
	}
	
	
}
