package redmagic.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.api.blocks.IWrenchable;
import redmagic.api.frame.ISoul;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.helpers.GenTreeHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.SoulHelper;
import redmagic.items.ItemSoul;
import redmagic.tileentities.tree.TileEntityTreeWood;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulWood extends BlockContainer implements IWrenchable{

	public Icon[] icons = new Icon[ItemBlockWood.subNames.length];
	public Icon tokeeWithSoul, tokeeWithFilterSoul, tokeeWithStorageSoul, tokeeWithFurnaceSoul, tokeeTop;
	
	protected BlockSoulWood(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.WOOD_NAME);
		this.setStepSound(Block.soundWoodFootstep);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		int count = 0;
        for(String name: ItemBlockWood.subNames){
        	this.icons[count] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WOOD_NAME + "_" + name);
        	count++;
        }
        this.tokeeWithSoul = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WOOD_NAME + "_" + BlockIndex.WOOD_TOKEE_NAME + "_soul");
        this.tokeeWithFilterSoul = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WOOD_NAME + "_" + BlockIndex.WOOD_TOKEE_NAME + "_soul_filter");
        this.tokeeWithStorageSoul = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WOOD_NAME + "_" + BlockIndex.WOOD_TOKEE_NAME + "_soul_storage");
        this.tokeeWithFurnaceSoul = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WOOD_NAME + "_" + BlockIndex.WOOD_TOKEE_NAME + "_soul_furnace");
        this.tokeeTop = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WOOD_NAME + "_" + BlockIndex.WOOD_TOKEE_NAME + "_top");
    }
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		TileEntity entity = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		switch(metadata){
		case BlockIndex.WOOD_TOKEE_METADATA:
			if(((TileEntityTreeWood)entity).hasStructure() && ((TreeStructure)((TileEntityTreeWood)entity).getStructure()).storage.contains(par2, par3, par4)){
				TileEntityTreeWood tree = (TileEntityTreeWood)entity;
				int type = SoulHelper.getType(((TreeStructure)tree.getStructure()).storage.getBlockAt(par2, par3, par4).soul);
				Logger.log(type);
				switch(type){
				case LogicIndex.SOUL_FILTER:
					return this.tokeeWithFilterSoul;
				case LogicIndex.SOUL_STORAGE:
					return this.tokeeWithFurnaceSoul;
				case LogicIndex.SOUL_FURNACE:
					return this.tokeeWithFurnaceSoul;
				}
				return this.tokeeWithSoul;
			}
			if(par5 == 0 || par5 == 1)return this.tokeeTop;
			break;
		}
		return getIcon(par5, metadata);
	}
	
	public Icon getIcon(int side, int metadata)
    {
		switch(metadata){
		case BlockIndex.WOOD_TOKEE_METADATA:
			switch(side){
			case 1:
			case 0:
				return this.tokeeTop;
			}
			break;
		}
		return this.icons[metadata];
    }
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		switch(metadata){
			case BlockIndex.WOOD_FRAGMENT_METADATA:
				return new TileEntityFragmentTree();
			case BlockIndex.WOOD_TOKEE_METADATA:
				return new TileEntityTreeWood();
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
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
		TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
		switch(metadata){
		case BlockIndex.WOOD_TOKEE_METADATA:
			if(current != null && current.getItem() instanceof ItemSoul){
				((TileEntityTreeWood)entity).addSoul(current);
				if(!par5EntityPlayer.capabilities.isCreativeMode)par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
				par1World.markBlockForRenderUpdate(par2, par3, par4);
				return true;
			}
			break;
		}
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockWood.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
    	TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
    	switch(par6){
    	case BlockIndex.WOOD_TOKEE_METADATA:
    		((TileEntityTreeWood)entity).removeBlock();
    		if(entity instanceof IInventory)InventoryHelper.dropInventory((IInventory) entity, par1World, par2, par3, par4);
    		break;
    	}
    	super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    
    public void onBlockAdded(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		switch(metadata){
		case BlockIndex.WOOD_TOKEE_METADATA:
			((TileEntityTreeWood)entity).addBlock();
			break;
		}
	}
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	int metadata = par1World.getBlockMetadata(par2, par3, par4);
    	TileEntity entity = par1World.getBlockTileEntity(par2, par3, par4);
    	switch(metadata){
    	}
    }

	@Override
	public void onWrench(EntityPlayer player, World world, int x, int y, int z, int metadata) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		switch(metadata){
		case BlockIndex.WOOD_TOKEE_METADATA:
			((TileEntityTreeWood)entity).wrench();
			break;
		}
		
	}


}
