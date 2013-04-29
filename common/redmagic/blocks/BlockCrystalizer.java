package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.tileentities.TileEntityCrystalizer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrystalizer extends BlockContainer{

	protected BlockCrystalizer(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.CRYSTALIZER_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrystalizer();
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
		if(entity == null)return false;
		par5EntityPlayer.openGui(Redmagic.instance, GuiIndex.CRYSTALIZER, par1World, par2, par3, par4);
		return true;
    }
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.CRYSTALIZER_NAME);
    }
	
	

}
