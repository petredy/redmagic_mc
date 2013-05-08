package redmagic.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.api.frame.ISoul;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.tileentities.education.TileEntityExtractorBasic;
import redmagic.tileentities.education.TileEntityExtractorCollector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockExtractor extends BlockContainer implements IWrenchable{
	
	private Icon iconActive, iconInactive, collectorActive, collectorInactive;
	
	protected BlockExtractor(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.EXTRACTOR_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtractorBasic();
	}
	@Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		if(metadata == BlockIndex.EXTRACTOR_COLLECTOR_METADATA){
			return new TileEntityExtractorCollector();
		}else{
			return createNewTileEntity(world);
		}
    }
	
	
	public void registerIcons(IconRegister par1IconRegister) {
        this.iconInactive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EXTRACTOR_BASIC_NAME);
        this.iconActive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EXTRACTOR_BASIC_NAME + "_active");
        this.collectorInactive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EXTRACTOR_COLLECTOR_NAME);
        this.collectorActive = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EXTRACTOR_COLLECTOR_NAME + "_active");
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.getMultiIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4), par1IBlockAccess.getBlockTileEntity(par2, par3, par4));
    }
	
	public Icon getMultiIcon(int side, int metadata, TileEntity entity){
		switch(metadata){
		case BlockIndex.EXTRACTOR_BASIC_METADATA:
			if(((IMultiEntity)entity).hasStructure())return this.iconActive;
			return this.iconInactive;
		case BlockIndex.EXTRACTOR_COLLECTOR_METADATA:
			if(((IMultiEntity)entity).hasStructure())return this.collectorActive;
			return this.collectorInactive;
		}
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
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		((IMultiEntity)par1World.getBlockTileEntity(par2, par3, par4)).buildStructure();
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
		if(entity != null)((IMultiEntity)entity).destroyStructure();
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public void onWrench(EntityPlayer player, World world, int x, int y, int z, int metadata) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockExtractor.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

}
