package redmagic.gen;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WorldGenSoulTree {

	public int layerAmount;
	public World world;
	public int x, y, z;
	public ItemStack wood, leaves;
	public Random rand;
	
	public WorldGenSoulTree(World world, int x, int y, int z, int layers, ItemStack wood, ItemStack leaves){
		this.layerAmount = layers;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.wood = wood;
		this.leaves = leaves;
		this.rand = new Random();
	}
	
	public void generate(){
		
		for(int i = 0; i <= layerAmount; i++){
			this.generateLayer(i);
		}
	}
	
	public void generateLayer(int height){
		if(world.isAirBlock(x, y + height, z)){
			this.generateWood(x, y + height, z);
			
			
		}
	}
	
	public void generateWood(int x, int y, int z){
		world.setBlock(x, y, z, wood.itemID, wood.getItemDamage(), 2);
	}
	
	public void generateLeave(int x, int y, int z){
		world.setBlock(x, y, z, leaves.itemID, leaves.getItemDamage(), 2);
	}
	
}
