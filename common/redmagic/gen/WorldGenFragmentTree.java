package redmagic.gen;

import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.core.Logger;
import redmagic.helpers.FragmentHelper;
import redmagic.tileentities.tree.fragment.TileEntityFragmentTree;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;

public class WorldGenFragmentTree extends WorldGenSoulTree{

	public int leaveHeight = 4;
	public float leaveChance = 0.4F;
	public int leaveRange = 2;
	public NBTTagCompound tag;
	
	public WorldGenFragmentTree(World world, int x, int y, int z, NBTTagCompound tag) {
		super(world, x, y, z, 5 + new Random().nextInt(3), new ItemStack(BlockManager.wood.blockID, 1, BlockIndex.WOOD_FRAGMENT_METADATA), new ItemStack(BlockManager.leaves.blockID, 1, BlockIndex.LEAVES_FRAGMENT_METADATA));
		this.tag = tag;
	}
	
	
	@Override
	public void generate(){
		for(int i = 0; i <= layerAmount; i++){
			this.generateLayer(i);
		}
		this.generateLeavesLayer(layerAmount + 1);
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
				}else if(world.getBlockId(x + i, y + height, z + k) == BlockManager.leaves.blockID && world.getBlockMetadata(x + i, y + height, z + k) == BlockIndex.LEAVES_FRAGMENT_METADATA){
					this.generateConnectedLeave(x + i, y + height, z + k);
				}
			}
		}
	}
	
	private void generateConnectedLeave(int x, int y, int z) {
		TileEntityFragmentTree entity1 = (TileEntityFragmentTree) world.getBlockTileEntity(x, y, z);
		ItemStack fragment1 = entity1.getStackInSlot(0);
		TileEntityFragmentTree entity2 = (TileEntityFragmentTree) world.getBlockTileEntity(this.x, this.y, this.z);
		ItemStack fragment2 = entity2.getStackInSlot(0);
		if(fragment1 != null && fragment2 != null){
			this.generateLeave(x, y, z);
			TileEntityFragmentTree entityFinish = (TileEntityFragmentTree)world.getBlockTileEntity(x, y, z);
			entityFinish.setInventorySlotContents(0, FragmentHelper.combine(fragment1, fragment2));
		}else{
			this.generateLeave(x, y, z);
		}
		
	}


	@Override
	public void generateWood(int x, int y, int z){
		world.setBlock(x, y, z, wood.itemID, wood.getItemDamage(), 2);
		this.setTileEntity(x, y, z);
	}
	
	

	@Override
	public void generateLeave(int x, int y, int z){
		world.setBlock(x, y, z, leaves.itemID, leaves.getItemDamage(), 2);
		this.setTileEntity(x, y, z);
	}
	
	private void setTileEntity(int x, int y, int z) {
		TileEntityFragmentTree tree = (TileEntityFragmentTree) world.getBlockTileEntity(x, y, z);
		tree.readFromNBT(tag);
		world.markBlockForUpdate(x, y, z);
	}

}
