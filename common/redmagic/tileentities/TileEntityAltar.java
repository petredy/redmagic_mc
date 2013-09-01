package redmagic.tileentities;

import redmagic.lib.gods.GodManager;
import redmagic.lib.gods.Sphere;
import redmagic.lib.talent.Talent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAltar extends TileEntity{

	public float rotationX;
	public float rotationY;
	public float rotationZ;
	
	public float rotationSpeed = 0.1f;
	
	public Talent path;
	
	
	public void setPath(Talent path){
		this.path = path;
		GodManager.addSphere(new Sphere(this.xCoord, this.yCoord, this.zCoord));
	}
	
	public void unsetPath(){
		this.path = null;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.rotationSpeed = tag.getFloat("rotationSpeed");
		this.path = Talent.getTalent(tag.getString("path"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setFloat("rotationSpeed", this.rotationSpeed);
		if(this.path != null)tag.setString("path", this.path.unlockableName);
	}
	
}
