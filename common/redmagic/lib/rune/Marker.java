package redmagic.lib.rune;

import net.minecraft.nbt.NBTTagCompound;

public class Marker {

	public int id;
	
	public Marker(){}
	
	public Marker(int id){
		this.id = id;
	}
	
	public double getX(double x, int index){
		int col = index % 4;
		
		return x + (col * 0.25f) + 0.125d; 
	}
	
	public double getZ(double z, int index){
		int row = index / 4;
		return z + (row * 0.25f) + 0.125d;
	}
	
	
	public void readFromNBT(NBTTagCompound tag){
		this.id = tag.getInteger("id");
	}
	
	public static Marker loadFromNBT(NBTTagCompound tag){
		Marker marker = new Marker();
		marker.readFromNBT(tag);
		return marker;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("id", id);
	}
	
	public boolean equals(Marker marker){
		return id == marker.id;
	}
	
}
