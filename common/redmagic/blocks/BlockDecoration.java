package redmagic.blocks;

import java.util.List;
import java.util.Random;

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
import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDecoration extends BlockContainer{

	public Icon[] icons = new Icon[ItemBlockDecoration.subNames.length];
	
	public BlockDecoration(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.DECORATION_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	@Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		return createNewTileEntity(world);
    }
	
	
	public void registerIcons(IconRegister par1IconRegister) {
        int count = 0;
		for(String name: ItemBlockDecoration.subNames){
        	this.icons[count++] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + name);
        }
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.getMultiIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4), par1IBlockAccess.getBlockTileEntity(par2, par3, par4));
    }
	
	public Icon getMultiIcon(int side, int metadata, TileEntity entity){
		return getIcon(side, metadata);
	}
	
	public Icon getIcon(int side, int metadata)
    {
		return this.icons[metadata];
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
		
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockDecoration.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
}
