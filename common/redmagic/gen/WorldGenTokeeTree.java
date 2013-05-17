package redmagic.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import redmagic.api.frame.ISoul;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.blocks.BlockManager;
import redmagic.blocks.multi.tree.TreeBlock;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.BlockIndex;
import redmagic.helpers.TreeHelper;
import redmagic.tileentities.tree.TileEntityTreeWood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WorldGenTokeeTree extends WorldGenSoulTree{

	public int leaveHeight = 4;
	public float leaveChance = 0.4F;
	public int leaveRange = 2;
	List<IMultiBlock> blocks = new ArrayList<IMultiBlock>();
	
	public WorldGenTokeeTree(World world, int x, int y, int z) {
		super(world, x, y, z, new Random().nextInt(9) + 5, new ItemStack(BlockManager.wood, 1, BlockIndex.WOOD_TOKEE_METADATA), new ItemStack(BlockManager.leaves, 1, BlockIndex.LEAVES_TOKEE_METADATA));
	}
	
	@Override
	public void generate(){
		for(int i = 0; i <= layerAmount; i++){
			this.generateLayer(i);
		}
		this.generateLeavesLayer(layerAmount + 1);
		TileEntityTreeWood entity = (TileEntityTreeWood) world.getBlockTileEntity(x, y, z);
		int id = TreeHelper.getNextID(world);
		TreeStructure structure = new TreeStructure();
		structure.blocks = blocks;
		structure.id = id;
		structure.storage.calculateCapacity(structure.getBlockType(TreeStructure.woodKey).size());
		TreeHelper.saveStructure(world, id, structure);
		entity.setStructure(id);
		entity.buildStructure();
	}
	
	public void generateLayer(int height){
		if(world.isAirBlock(x, y + height, z) || world.getBlockId(x, y + height, z) == BlockManager.sapling.blockID){
			this.generateWood(x, y + height, z);
			if(height == layerAmount){
				for(int i = -1; i <= 1; i++){
					for(int j = -1; j <= 1; j++){
						if(world.isAirBlock(x + i, y + height, z + j)){
							this.generateWood(x + i, y + height, z + j);
						}
					}
				}
				this.generateLeavesLayer(height);
			}
			if(height != layerAmount && height >= leaveHeight && this.rand.nextFloat() < leaveChance){
				this.generateLeavesLayer(height);
			}
		}
	}


	private void generateLeavesLayer(int height) {
		for(int i = -leaveRange; i <= leaveRange; i++){
			for(int k = -leaveRange; k <= leaveRange; k++){
				if(this.rand.nextFloat() < 0.95){
					if(world.isAirBlock(x + i, y + height, z + k))
					this.generateLeave(x + i, y + height, z + k);
				}
			}
		}
	}
	
	public void generateWood(int x, int y, int z){
		world.setBlock(x, y, z, wood.itemID, wood.getItemDamage(), 2);
		TreeBlock block = new TreeBlock(x, y, z);
		block.type = TreeStructure.woodKey;
		this.blocks.add(block);
	}
	
	public void generateLeave(int x, int y, int z){
		world.setBlock(x, y, z, leaves.itemID, leaves.getItemDamage(), 2);
		TreeBlock block = new TreeBlock(x, y, z);
		block.type = TreeStructure.leaveKey;
		this.blocks.add(block);
	}

}
