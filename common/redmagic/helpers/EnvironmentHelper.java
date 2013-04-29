package redmagic.helpers;

import redmagic.configuration.LogicIndex;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class EnvironmentHelper {
	
	private World world;
	private int x, y, z, range;
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
		this.scan();
	}
	
	public String toString(){
		return "Environment: a" + air + " w" + water + " l" + lava;
	}
	
	public void scan(){
		this.scanAir();
		this.scanWater();
		this.scanLava();
		avg = (int) ((air * LogicIndex.FILTER_AIR_RAITING + water * LogicIndex.FILTER_WATER_RAITING + lava * LogicIndex.FILTER_LAVA_RAITING) / 3);
	}

	private void scanAir() {
		for(int x = -this.range; x <= this.range; x++){
			for(int y = -this.range; y <= this.range; y++){
				for(int z = -this.range; z <= this.range; z++){
					if(this.world.isAirBlock(this.x + x, this.y + y, this.z + z))air++;
				}
			}
		}
	}
	
	private void scanWater(){
		for(int x = -this.range; x <= this.range; x++){
			for(int y = -this.range; y <= this.range; y++){
				for(int z = -this.range; z <= this.range; z++){
					if(this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.waterMoving.blockID || this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.waterStill.blockID)water++;
				}
			}
		}
	}
	
	private void scanLava(){
		for(int x = -this.range; x <= this.range; x++){
			for(int y = -this.range; y <= this.range; y++){
				for(int z = -this.range; z <= this.range; z++){
					if(this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.lavaMoving.blockID || this.world.getBlockId(this.x + x, this.y + y, this.z + z) == Block.lavaStill.blockID)lava++;
				}
			}
		}
	}
}
