package redmagic.blocks;

import java.util.Random;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.tileentities.TileEntityLens;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLens extends BlockContainer{

	protected BlockLens(int par1) {
		super(par1, Material.glass);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.LENS_NAME);
	}

	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public int getRenderBlockPass()
    {
        return 1;
    }
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.LENS_NAME);
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLens();
	}
	
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
		if(world.canBlockSeeTheSky(x, y + 1, z)){
			if(world.isDaytime()){
				Redmagic.proxy.addEffect("crack", world, x + 0.5, y + 2, z + 0.5);
			}else{
				Redmagic.proxy.addEffect("crack", world, x, y + 1, z);
			}
		}
    }
	
	

}
