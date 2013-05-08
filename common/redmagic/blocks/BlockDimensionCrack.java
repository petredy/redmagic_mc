package redmagic.blocks;

import java.util.Random;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockDimensionCrack extends Block{

	public BlockDimensionCrack(int par1) {
		super(par1, Material.dragonEgg);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.DIMENSION_CRACK_NAME);
	}
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    @Override
    public int getRenderType()
    {
    	return -1;
    }
    
    public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
    	Redmagic.proxy.addEffect("crack", world, x + 0.5, y + 0.5, z + 0.5);
    }

}
