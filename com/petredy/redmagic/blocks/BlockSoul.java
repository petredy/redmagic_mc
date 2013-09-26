package com.petredy.redmagic.blocks;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntitySoul;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoul extends BlockContainer{

	protected BlockSoul(int par1) {
		super(par1, Material.air);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.SOUL_NAME);
		this.setTickRandomly(true);
		this.setBlockUnbreakable();
		this.setBlockBounds(0, 0, 0, 0, 0, 0);
	}

	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_NAME);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoul();
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
		Redmagic.proxy.addEffect("soul", world, x + 0.5, y + 0.5, z + 0.5, new Object[0]);
		
	}
	
	public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return false;
    }

}
