package redmagic.helpers;

import redmagic.api.essence.IPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PipeHelper {
	
	public World world;
	public int x, y, z;
	public TileEntity tileEntity;
	
	public PipeHelper(World world, int x, int y, int z){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.tileEntity = world.getBlockTileEntity(x, y, z);
	}
	
	public boolean isPipe(){
		return this.tileEntity != null && this.tileEntity instanceof IPipe;
	}

	public int store(int extracted) {
		if(this.isPipe()){
			return ((IPipe)this.tileEntity).store(extracted);
		}
		return 0;
	}
}
