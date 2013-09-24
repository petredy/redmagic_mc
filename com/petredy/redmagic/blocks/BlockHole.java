package com.petredy.redmagic.blocks;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.items.ItemRedhole;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.redhole.Hole;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.RedholeUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockHole extends Block{

	public BlockHole(int par1) {
		super(par1, Material.coral);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.HOLE_NAME);
		this.setTickRandomly(true);
	}
	
	public boolean renderAsNormalBlock(){
        return false;
    }
	
	public int getRenderType(){
        return -1;
    }
	
	public boolean isOpaqueCube(){
        return false;
    }
	
	public static boolean isNormalCube(int par0)
    {
		return false;
    }
	
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
		int blockMetadata = world.getBlockMetadata(x, y, z);
		LogUtils.log("create hole effect");
		Redmagic.proxy.addEffect("hole", world, x + 0.5, y + 0.5, z + 0.5, new Object[]{
			Hole.getHole(blockMetadata).getHoleColor()
		});
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
		if(current != null && current.getItem() instanceof ItemRedhole){
			RedholeUtils.saveHole(current, Hole.getHole(meta));
			par1World.setBlockToAir(par2, par3, par4);
		}
        return true;
    }

}
