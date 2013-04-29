package redmagic.tileentities;

import redmagic.api.essence.IStorage;

public class TileEntityTransporter extends TileEntityStorage{

	public int essencesPerTick;
	public int count = 0;
	public int need = 10;
	
	public void updateEntity(){
		super.updateEntity();
		if(this.essences > 0 && count >= need){
			count = 0;
			int connections = this.getConnections();
			int essencesPerConnection = (this.essencesPerTick > this.essences) ? this.essences / ((connections > 0) ? connections : 1) : this.essencesPerTick / ((connections > 0) ? connections : 1);
			this.loadConnections(essencesPerConnection);
		}
		count++;
	}
	
	@SuppressWarnings("unused")
	private void loadConnections(int essencesPerConnection) {
		int rtn1 = 0, rtn2 = 0, rtn3 = 0, rtn4 = 0, rtn5 = 0, rtn6 = 0;
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof IStorage)rtn1 = this.loadConnection(this.xCoord, this.yCoord - 1, this.zCoord, essencesPerConnection);
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof IStorage)rtn2 = this.loadConnection(this.xCoord, this.yCoord + 1, this.zCoord, essencesPerConnection + rtn1);
		if(this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof IStorage)rtn3 = this.loadConnection(this.xCoord - 1, this.yCoord, this.zCoord, essencesPerConnection + rtn2);
		if(this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof IStorage)rtn4 = this.loadConnection(this.xCoord + 1, this.yCoord, this.zCoord, essencesPerConnection + rtn3);
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof IStorage)rtn5 = this.loadConnection(this.xCoord, this.yCoord, this.zCoord - 1, essencesPerConnection + rtn4);
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof IStorage)this.loadConnection(this.xCoord, this.yCoord, this.zCoord + 1, essencesPerConnection + rtn5);
	}

	private int loadConnection(int x, int y, int z, int essences) {
		IStorage storage = (IStorage) this.worldObj.getBlockTileEntity(x, y, z);
		int unloaded = this.extract(essences);
		int loaded = storage.store(unloaded);
		this.store(unloaded - loaded);
		return unloaded - loaded;
	}

	public int getConnections(){
		int connections = 0;
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof IStorage)connections++;
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof IStorage)connections++;
		if(this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof IStorage)connections++;
		if(this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof IStorage)connections++;
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof IStorage)connections++;
		if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof IStorage)connections++;
		return connections;
	}
}
