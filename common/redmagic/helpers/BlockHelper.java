package redmagic.helpers;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockHelper {
	public static void breakBlock(World world, int x, int y, int z, int forcedLifespan, boolean drop) {
		int blockId = world.getBlockId(x, y, z);

		if (blockId != 0 && drop && !world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			List<ItemStack> items = Block.blocksList[blockId].getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);

			for (ItemStack item : items) {
				float var = 0.7F;
				double dx = world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
				double dy = world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
				double dz = world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
				EntityItem entityitem = new EntityItem(world, x + dx, y + dy, z + dz, item);

				entityitem.lifespan = forcedLifespan;
				entityitem.delayBeforeCanPickup = 10;

				world.spawnEntityInWorld(entityitem);
			}
		}

		world.setBlockToAir(x, y, z);
	}
	
	
	public static void dropItems(World world, int x, int y, int z){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity instanceof IInventory){
			IInventory inv = (IInventory)entity;
			Random rand = new Random();
			for(int i = 0; i < inv.getSizeInventory(); i++){
				ItemStack stack = inv.getStackInSlot(i);
				if(stack != null){
					double xRand = rand.nextDouble();
					double yRand = rand.nextDouble();
					double zRand = rand.nextDouble();
					
					EntityItem item = new EntityItem(world, x + xRand, y + yRand, z + zRand, stack);
					world.spawnEntityInWorld(item);
				}
			}
		}
	}
	
	
	public static int getRotation(World par0World, int par1, int par2, int par3, EntityLiving par4EntityLiving){
		double d0 = par4EntityLiving.posY + 1.82D - (double)par4EntityLiving.yOffset; 
		
		if (d0 - (double)par2 > 2.0D)
		{
			return 1;
		}
		
		if ((double)par2 - d0 > 0.0D)
		{
		    return 0;
		}
		
		int l = MathHelper.floor_double((double)(par4EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
	}
}
