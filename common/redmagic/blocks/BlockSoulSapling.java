package redmagic.blocks;

import static net.minecraftforge.common.EnumPlantType.Cave;
import static net.minecraftforge.common.EnumPlantType.Crop;
import static net.minecraftforge.common.EnumPlantType.Desert;
import static net.minecraftforge.common.EnumPlantType.Nether;
import static net.minecraftforge.common.EnumPlantType.Plains;
import static net.minecraftforge.common.EnumPlantType.Water;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.api.frame.ISoul;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.GenTreeHelper;
import redmagic.items.ItemFragment;
import redmagic.tileentities.tree.fragment.TileEntityFragmentTree;
import redmagic.items.ItemSoul;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockSoulSapling extends BlockContainer implements IPlantable, IWrenchable{

	
	public Icon[] icons = new Icon[ItemBlockSapling.subNames.length];
	
	protected BlockSoulSapling(int par1) {
		super(par1, Material.leaves);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SAPLING_NAME);
		this.setTickRandomly(true);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		int count = 0;
        for(String name: ItemBlockSapling.subNames){
        	this.icons[count] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SAPLING_NAME + "_" + name);
        	count++;
        }
    }
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		
		return getIcon(par5, metadata);
	}
	
	public Icon getIcon(int side, int metadata)
    {
		return this.icons[metadata];
    }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		switch(metadata){
			case BlockIndex.SAPLING_FRAGMENT_METADATA:
				return new TileEntityFragmentTree();
			default: return this.createNewTileEntity(world);
		}
    }
	
	
	public int idDropped(int metadata, Random par2Random, int par3)
    {
		return this.blockID;
    }
	
	public int damageDropped(int metadata)
    {
		return metadata;
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		int metadata = world.getBlockMetadata(x, y, z);
		ItemStack current = player.getCurrentEquippedItem();
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		switch(metadata){
		case BlockIndex.SAPLING_FRAGMENT_METADATA:
			if(current != null && current.getItem() instanceof ItemFragment){
				((TileEntityFragmentTree)entity).setInventorySlotContents(0, current);
				if(!player.capabilities.isCreativeMode)player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				return true;
			}
			break;
		}
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockSapling.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	private void growTree(World world, int x, int y, int z, Random rand) {
		if(world.isRemote)return;
		int metadata = world.getBlockMetadata(x, y, z);
		switch(metadata){
		case BlockIndex.SAPLING_FRAGMENT_METADATA:
			GenTreeHelper.generateFragmentTree(world, x, y, z, rand);
			break;
		case BlockIndex.SAPLING_TOKEE_METADATA:
			GenTreeHelper.generateTokeeTree(world, x, y, z, new Random());
			break;
		}
	}
	
	
	
	// Plant
	
	/**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.grass.blockID || par1 == Block.dirt.blockID || par1 == Block.tilledField.blockID;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        this.checkFlowerChange(par1World, par2, par3, par4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.checkFlowerChange(par1World, par2, par3, par4);
        
        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
        {
            this.growTree(par1World, par2, par3, par4, par5Random);
        }
    }

	protected final void checkFlowerChange(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && 
                (soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return Plains;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }

	@Override
	public void onWrench(EntityPlayer player, World world, int x, int y, int z, int metadata) {
		this.growTree(world, x, y, z, new Random());
	}
	

}
