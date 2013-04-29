package redmagic.blocks;

import java.util.Random;

import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.api.frame.ISoul;
import redmagic.api.multiblock.IEducationEntity;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.tileentities.education.TileEntityEducationBasic;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEducation extends BlockContainer implements IWrenchable{
	
	private Icon iconActive;
	private Icon iconInactive;
	
	protected BlockEducation(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.EDUCATION_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityEducationBasic();
	}
	
	public void registerIcons(IconRegister par1IconRegister) {
        this.iconInactive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EDUCATION_BASIC_NAME);
        this.iconActive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EDUCATION_BASIC_NAME + "_active");
    }
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.getMultiIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4), par1IBlockAccess.getBlockTileEntity(par2, par3, par4));
    }
	
	public Icon getMultiIcon(int side, int metadata, TileEntity entity){
		if(((IEducationEntity)entity).hasStructure())return this.iconActive;
		return this.iconInactive;
	}
	
	public Icon getIcon(int side, int metadata)
    {
		return this.iconInactive;
    }
	
	public int idDropped(int metadata, Random par2Random, int par3)
    {
		return this.blockID;
    }
	
	public int damageDropped(int metadata)
    {
		return metadata;
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		ItemStack currentItem = par5EntityPlayer.getCurrentEquippedItem();
		TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
		if(entity == null)return false;
		if(currentItem != null && currentItem.getItem() instanceof ISoul){
			if(((IEducationEntity)entity).hasStructure())((IEducationEntity)entity).saveSoul(currentItem);
			return true;
		}
		if(((IEducationEntity)entity).hasStructure()){
			IMultiBlock data = ((IEducationEntity)entity).getStructure().getDataBlock();
			if(data != null){
				par5EntityPlayer.openGui(Redmagic.instance, GuiIndex.EDUCATION_BASIC, par1World, data.getX(), data.getY(), data.getZ());
				return true;
			}
		}
		return false;
    }
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		((IEducationEntity)par1World.getBlockTileEntity(par2, par3, par4)).buildStructure();
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
		if(entity == null)return;
		((IEducationEntity)entity).dropAll();
		((IEducationEntity)entity).destroyStructure();
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@SuppressWarnings("unused")
	@Override
	public void onWrench(EntityPlayer player, World world, int x, int y, int z, int metadata) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		
	}

}
