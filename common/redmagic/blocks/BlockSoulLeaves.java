package redmagic.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.helpers.BlockHelper;
import redmagic.helpers.FragmentHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.SoulHelper;
import redmagic.tileentities.tree.TileEntityTreeLeaves;
import redmagic.tileentities.tree.fragment.TileEntityFragmentTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockSoulLeaves extends BlockContainer implements IShearable{

	public Icon[] icons = new Icon[ItemBlockLeaves.subNames.length];
	
	protected BlockSoulLeaves(int par1) {
		super(par1, Material.leaves);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.LEAVES_NAME);
		this.setStepSound(Block.soundGrassFootstep);
		this.setTickRandomly(true);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		int count = 0;
        for(String name: ItemBlockLeaves.subNames){
        	this.icons[count] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.LEAVES_NAME + "_" + name);
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
			case BlockIndex.LEAVES_FRAGMENT_METADATA:
				return new TileEntityFragmentTree();
			case BlockIndex.LEAVES_TOKEE_METADATA:
				return new TileEntityTreeLeaves();
			default: return this.createNewTileEntity(world);
		}
    }
	
	
	public int idDropped(int metadata, Random par2Random, int par3)
    {
		return 0;
    }
	
	public int damageDropped(int metadata)
    {
		return 0;
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		switch(metadata){
		}
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockLeaves.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        return true;
    }
	
	@Override
    public boolean isLeaves(World world, int x, int y, int z)
    {
        return true;
    }

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock(){
		return true;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		return true;
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		int woodBlocks = 0;
		for(int i = -2; i <= 2; i++){
			for(int j = -2; j <= 2; j++){
				for(int k = -2; k <= 2; k++){
					if(par1World.getBlockId(par2 + i, par3 + j, par4 + k) == BlockManager.wood.blockID){
						woodBlocks++;
					}
				}
			}
		}
		if(woodBlocks == 0){
			BlockHelper.breakBlock(par1World, par2, par3, par4, 10, true);
		}
    }
	
	public void breakBlock(World world, int x, int y, int z, int id, int metadata){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && !world.isRemote){
			switch(metadata){
				case BlockIndex.LEAVES_FRAGMENT_METADATA:
					if(new Random().nextFloat() < LogicIndex.SOUL_FRAGMENT_ALL_CHANCE)InventoryHelper.dropInventory((IInventory)entity, world, x, y, z);
					break;
				case BlockIndex.LEAVES_TOKEE_METADATA:
					((TileEntityTreeLeaves)entity).removeBlock();
					break;
			}
		}
		super.breakBlock(world, x, y, z, id, metadata);
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(!world.isRemote){
			switch(metadata){
			case BlockIndex.LEAVES_TOKEE_METADATA:
				((TileEntityTreeLeaves)entity).addBlock();
				break;
			}
		}
	}
	
	//Color
	public int getRenderColor(int par1)
    {
        return 6396257;
    }
	
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        TileEntity entity = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
        switch(metadata){
        case BlockIndex.LEAVES_FRAGMENT_METADATA:
        	return this.getColorForFragmentLeaves((TileEntityFragmentTree)entity);
        case BlockIndex.LEAVES_TOKEE_METADATA:
        	return ((TileEntityTreeLeaves)entity).hasStructure() ? 6400257 : 6300257;
        }
        return 6396257;
    }

	private boolean isConnectedToWood(IBlockAccess par1iBlockAccess, int x, int y, int z, int metadata) {
		for(int i = -2; i <= 2; i++){
			for(int j = -2; j <= 2; j++){
				for(int k = -2; k <= 2; k++){
					if(par1iBlockAccess.getBlockId(x + i, y + j, z + k) == BlockManager.wood.blockID && par1iBlockAccess.getBlockMetadata(x + i, y + j, z + k) == metadata)return true;
				}
			}
		}
		return false;
	}

	private int getColorForFragmentLeaves(TileEntityFragmentTree entity) {
		ItemStack fragment = entity.getStackInSlot(0);
		if(fragment != null){
			int intelligence = FragmentHelper.getIntelligence(fragment);
			intelligence = intelligence == 100 ? 99 : intelligence;
			int strength = FragmentHelper.getStrength(fragment);
			strength = strength == 100 ? 99 : strength;
			int capacity = FragmentHelper.getCapacity(fragment);
			capacity = capacity == 100 ? 99 : capacity;
			int illusion = FragmentHelper.getIllusion(fragment);
			illusion = illusion == 100 ? 99 : illusion;
			int satisfaction = FragmentHelper.getSatisfaction(fragment);
			satisfaction = satisfaction == 100 ? 99 : satisfaction;
			int color = Integer.parseInt("" + intelligence + strength + capacity + (illusion == 0 ? satisfaction : illusion));
			return color;
		}
		return 6396257;
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	int metadata = par1World.getBlockMetadata(par2, par3, par4);
    	TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
    	switch(metadata){

    	}
    }


}
