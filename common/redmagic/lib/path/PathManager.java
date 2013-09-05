package redmagic.lib.path;

import java.util.HashMap;

import redmagic.configuration.AbilityIndex;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PathHelper;
import redmagic.lib.abilities.Ability;
import redmagic.lib.talent.Talent;

import net.minecraft.nbt.NBTTagCompound;

public class PathManager {

	/*
	 * ---------------------------------------------------------------------------------
	 * Static Context 
	 */
	protected static HashMap<String, Class> paths = new HashMap<String, Class>();
	
	public static void registerPath(String name, Class pathClass){
		paths.put(name, pathClass);
	}
	
	public static Path getPath(String name){
		try {
			return (Path) paths.get(name).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/*
	 * ---------------------------------------------------------------------------------
	 */
	
	
	public Path path;
	
	
	public void choosePath(String name){
		this.path = PathManager.getPath(name);
	}
	
	public boolean hasPath(){
		return this.path != null;
	}
	
	public void useAbility(String name, Object[] data){
		if(this.hasPath()){
			Ability ab = path.abilities.get(name);
			if(ab != null){
				ab.use(data);
			}
		}
	}
	
	public void setTalentUnlockable(Talent talent){
		if(this.hasPath()){
			LogHelper.log("unlockable " + talent.unlockableName);
			Ability ability = this.path.abilities.get(talent.unlockableName);
			if(ability != null){
				ability.setUnlockable();
			}
		}
	}
	
	public void setTalentUnlocked(Talent talent){
		if(this.hasPath()){
			Ability ability = this.path.abilities.get(talent.unlockableName);
			if(ability != null){
				ability.setUnlocked();
			}
		}
	}
	
	public boolean isTalentUnlocked(Talent talent){
		if(this.hasPath()){
			if(talent.isPath() && talent.unlockableName.equals(this.path.name)){
				return true;
			}else{
				return this.path.hasAbilityUnlocked(talent.unlockableName);
			}
		}
		return false;
	}
	
	public boolean isTalentUnlockable(Talent talent){
		if(this.hasPath()){
			if(talent.isPath() && talent.unlockableName.equals(path.name))return true;
			return this.path.isAbilityUnlockable(talent.unlockableName);
		}else{
			if(talent.isPath()){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.path = PathHelper.loadFromNBT(tag);
	}
	
	public static PathManager loadFromNBT(NBTTagCompound tag){
		PathManager path = new PathManager();
		path.readFromNBT(tag);
		return path;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		if(this.hasPath())this.path.writeToNBT(tag);
	}
	
}
