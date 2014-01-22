package com.petredy.redmagic.redenergy;

public class Redkey {

	public int x, z, dimension;
	
	public Redkey(int dimension, int x, int z){
		this.dimension = dimension;
		this.x = x;
		this.z = z;
	}
	
	
	public static Redkey get(int dimension, int x, int z){
		return new Redkey(dimension, x, z);
	}
	
	@Override
    public int hashCode() {
		int hash = 23;
		hash = hash * 31 + dimension;
		hash = hash * 31 + x;
		hash = hash * 31 + z;
		return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null)return false;
        if (getClass() != obj.getClass())return false;
        Redkey other = (Redkey) obj;
        if(dimension != other.dimension)return false;
        if(x != other.x)return false;
        if(z != other.z)return false;
        return true;
    }
	
	
}
