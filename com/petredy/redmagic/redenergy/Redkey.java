package com.petredy.redmagic.redenergy;

public class Redkey {

	public int x, y, z;
	
	public Redkey(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	public static Redkey get(int x, int y, int z){
		return new Redkey(x, y, z);
	}
	
	@Override
    public int hashCode() {
		int hash = 23;
		hash = hash * 31 + x;
		hash = hash * 31 + y;
		hash = hash * 31 + z;
		return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null)return false;
        if (getClass() != obj.getClass())return false;
        Redkey other = (Redkey) obj;
        if(x != other.x)return false;
        if(y != other.y)return false;
        if(z != other.z)return false;
        return true;
    }
	
	
}
