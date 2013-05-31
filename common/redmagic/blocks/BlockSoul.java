package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import redmagic.tileentities.TileEntitySoulBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoul extends BlockContainer{

	protected BlockSoul(int par1) {
		super(par1, Material.rock);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SOUL_BLOCK_NAME);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_BLOCK_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulBlock();
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		TileEntitySoulBlock entity = (TileEntitySoulBlock) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		if(entity.blockID[par5] > 0 && Block.blocksList[entity.blockID[par5]].isBlockNormalCube(entity.worldObj, par2, par3, par4)){
			return Block.blocksList[entity.blockID[par5]].getIcon(par5, entity.blockMetadata[par5]);
		}
		return this.blockIcon;
    }
	
	

}
