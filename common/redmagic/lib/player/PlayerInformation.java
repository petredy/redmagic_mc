package redmagic.lib.player;


import java.io.DataInput;
import java.io.DataOutput;

import redmagic.helpers.LogHelper;
import redmagic.lib.path.PathManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerInformation {

	
	public String username;
	public int faith; // PlayerLevel (Not vanilla level)
	public float energy;
	public float maxEnergy;
	public PathManager pathManager;
	
	public PlayerInformation(){}
	
	public PlayerInformation(EntityPlayer player) {
		this.username = player.username;
		this.faith = 0;
		this.maxEnergy = 0;
		this.energy = this.maxEnergy;
		this.pathManager = new PathManager();
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.username = tag.getString("username");
		this.faith = tag.getInteger("faith");
		this.maxEnergy = tag.getFloat("maxEnergy");
		this.energy = tag.getFloat("energy");
		NBTTagCompound pathTag = tag.getCompoundTag("pathManager");
		if(pathTag != null)	this.pathManager = PathManager.loadFromNBT(pathTag);
	}
	
	
	public static PlayerInformation loadFromNBT(NBTTagCompound tag){
		PlayerInformation information = new PlayerInformation();
		information.readFromNBT(tag);
		return information;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setString("username", this.username);
		tag.setInteger("faith", this.faith);
		tag.setFloat("maxEnergy", this.maxEnergy);
		tag.setFloat("energy", this.energy);
		if(this.pathManager != null){
			NBTTagCompound pathTag = new NBTTagCompound();
			this.pathManager.writeToNBT(pathTag);
			tag.setTag("pathManager", pathTag);
		}
	}

	
	
}
