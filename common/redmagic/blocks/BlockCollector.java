package redmagic.blocks;

import java.util.Random;

import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.tileentities.TileEntityCollector;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCollector extends BlockContainer implements IWrenchable{

	public Icon opened, closed;
	
	protected BlockCollector(int par1) {
		super(par1, Material.glass);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.COLLECTOR_NAME);
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		par5EntityPlayer.openGui(Redmagic.instance, GuiIndex.COLLECTOR, par1World, par2, par3, par4);
		return true;
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
        return 0;
    }
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.closed = this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.COLLECTOR_NAME + "_closed");
		this.opened = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.COLLECTOR_NAME + "_opened");
		
    }
	
	public Icon getIcon(int par1, int par2)
    {
		if(par2 > 0)return this.opened;
        return this.closed;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCollector();
	}
	
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
		
    }

	@Override
	public void onWrench(EntityPlayer player, World world, int x, int y, int z, int metadata) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof TileEntityCollector){
			((TileEntityCollector)entity).activate();
			world.setBlockMetadataWithNotify(x, y, z, this.blockID, ((TileEntityCollector)entity).activeCount);
		}
	}
	
	

}
