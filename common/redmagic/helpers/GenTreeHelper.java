package redmagic.helpers;

import java.util.Random;

import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.gen.WorldGenFragmentTree;
import redmagic.gen.WorldGenTokeeTree;
import redmagic.tileentities.tree.fragment.TileEntityFragmentTree;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GenTreeHelper {
	public static void generateFragmentTree(World world, int x, int y, int z, Random rand){
		TileEntityFragmentTree entity = (TileEntityFragmentTree)world.getBlockTileEntity(x, y, z);
		if(entity.getStackInSlot(0) == null)entity.setInventorySlotContents(0, FragmentHelper.createNewFragment(rand.nextInt(LogicIndex.SOUL_FRAGMENT_TYPES.length)));
		NBTTagCompound tag = new NBTTagCompound();
		entity.writeToNBT(tag);
		WorldGenFragmentTree tree = new WorldGenFragmentTree(world, x, y, z, tag);
		tree.generate();
	}

	public static void generateTokeeTree(World world, int x, int y, int z, Random random) {
		WorldGenTokeeTree tree = new WorldGenTokeeTree(world, x, y, z);
		tree.generate();
	}
}
