package redmagic.blocks;

import java.util.List;
import java.util.Random;

import redmagic.Redmagic;
import redmagic.api.essence.IPipe;
import redmagic.api.essence.IStorage;
import redmagic.client.renderers.RenderPipe;
import redmagic.configuration.BlockIndex;
import redmagic.tileentities.TileEntityPipe;
import redmagic.items.ItemRedhole;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ITankContainer;

public class BlockPipe extends BlockContainer{

	protected BlockPipe(int par1) {
		super(par1, Material.piston);
		this.setHardness(3.0F);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.PIPE_NAME);
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		ItemStack stack = par5EntityPlayer.getCurrentEquippedItem();
		if(stack != null && stack.getItem() instanceof ItemRedhole){
			TileEntityPipe entity = (TileEntityPipe) par1World.getBlockTileEntity(par2, par3, par4);
			entity.switchMode();
			return true;
		}
		return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPipe();
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
    	return RenderPipe.blockRenderId;
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		this.checkAndSetNeighbors(world, x, y, z);
	}
	
	private void checkAndSetNeighbors(World world, int x, int y, int z){
		TileEntityPipe entity = (TileEntityPipe) world.getBlockTileEntity(x, y, z);
		if(entity != null){
			entity.left = this.canConnect(world, x + 1, y, z);
			entity.right = this.canConnect(world, x - 1, y, z);
			entity.front = this.canConnect(world, x, y, z + 1);
			entity.back = this.canConnect(world, x, y, z - 1);
			entity.top = this.canConnect(world, x, y + 1, z);
			entity.bottom = this.canConnect(world, x, y - 1, z);
		}
	}
	
	private boolean canConnect(World world, int x, int y, int z){
		return world.getBlockTileEntity(x, y, z) instanceof ITankContainer;
	}
	
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random) {
		this.checkAndSetNeighbors(world, x, y, z);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
    	TileEntityPipe entity = (TileEntityPipe) par1World.getBlockTileEntity(par2, par3, par4);
        this.setBlockBounds(0.4F, 0.4F, 0.4F, 0.6F, 0.6F, 0.6F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        if(entity != null){
        	if(entity.front){
        		this.setBlockBounds(0.4F, 0.4F, 0.6F, 0.6F, 0.6F, 1.0F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        	}
        	if(entity.back){
        		this.setBlockBounds(0.4F, 0.4F, 0.0F, 0.6F, 0.6F, 0.4F);
	            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	    	}
        	if(entity.left){
        		this.setBlockBounds(0.6F, 0.4F, 0.4F, 1.0F, 0.6F, 0.6F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        	}
        	if(entity.right){       		
	        	this.setBlockBounds(0.0F, 0.4F, 0.4F, 0.4F, 0.6F, 0.6F);
	            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	    	}
        	if(entity.top){
        		this.setBlockBounds(0.4F, 0.6F, 0.4F, 0.6F, 1.0F, 0.6F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        	}
        	if(entity.bottom){
        		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 0.4F, 0.6F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        	}
        }
        this.setBlockBoundsForItemRender();
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    
    @Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof IPipe) {
			return ((IPipe) tile).isConnectedOnSide(side);
		}
		return false;
	}

}
