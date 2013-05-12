package redmagic.helpers;

import redmagic.configuration.LogicIndex;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class EnvironmentHelper {
	
	private World world;
	public int x, y, z, range;
	public int air = 0;
	public int water = 0;
	public int lava = 0;
	public int avg = 0;
	
	public EnvironmentHelper(World world, int x, int y, int z, int range){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.range = range;
	}
	
	public boolean isWater(){
		return this.world.getBlockId(this.x, this.y, this.z) == Block.waterMoving.blockID || this.world.getBlockId(this.x, this.y, this.z) == Block.waterStill.blockID;
	}
	
	public boolean isAir(){
		return this.world.isAirBlock(x, y, z);
	}
	
	public boolean isLava(){
		return this.world.getBlockId(x, y, z) == Block.lavaStill.blockID || this.world.getBlockId(x, y, z) == Block.lavaMoving.blockID;
	}
	
	public String toString(){
		return "Environment: a" + air + " w" + water + " l" + lava;
	}
	
	public void scan(){
		this.scanAir();
		this.scanWater();
		this.scanLava();
		avg = (int) ((air * LogicIndex.FILTER_AIR_RAITING + water * LogicIndex.FILTER_WATER_RAITING + lava * LogicIndex.FILTER_LEAVES_RAITING) / 3);
	}

	public void scanAir() {
		for(int x = -this.range; x <= this.range; x++){
			for(int y = -this.range; y <= this.range; y++){
				for(int z = -this.range; z <= this.range; z++){
					if(this.world.isAirBlock(this.x + x, this.y + y, this.z + z))air++;
				}
			}
		}
	}
	
	public void scanWater(){
		for(int x = -this.range; x <= this.range; x++){
			for(int y = -this.range; y <= this.range; y++){
				for(int z = -this.range; z <= this.range; z++){
					if(this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.waterMoving.blockID || this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.waterStill.blockID)water++;
				}
			}
		}
	}
	
	public void scanLava(){
		for(int x = -this.range; x <= this.range; x++){
			for(int y = -this.range; y <= this.range; y++){
				for(int z = -this.range; z <= this.range; z++){
					if(this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.lavaMoving.blockID || this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.lavaStill.blockID)lava++;
				}
			}
		}
	}
}
