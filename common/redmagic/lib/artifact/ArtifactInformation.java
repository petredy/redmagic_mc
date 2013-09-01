package redmagic.lib.artifact;

import net.minecraft.nbt.NBTTagCompound;
import redmagic.lib.talent.Talent;

public class ArtifactInformation {
	
	protected Talent path;
	
	public ArtifactInformation(){
		
	}
	
	public ArtifactInformation(Talent path){
		this.path = path;;
	}
	
	public Talent getPath(){
		return path;
	}
	
	public void setPath(Talent talent){
		if(talent.isPath()){
			path = talent;
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.path = Talent.getTalent(tag.getString("path"));
	}
	
	public static ArtifactInformation loadFromNBT(NBTTagCompound tag){
		ArtifactInformation info = new ArtifactInformation();
		info.readFromNBT(tag);
		return info;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setString("path", path.unlockableName);
	}
	
	
	
}
