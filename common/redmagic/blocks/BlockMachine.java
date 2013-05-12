package redmagic.blocks;

import java.util.List;
import java.util.Random;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.helpers.BlockHelper;
import redmagic.helpers.SoulHelper;
import redmagic.tileentities.machines.TileEntityMachineFilter;
import redmagic.tileentities.machines.TileEntityMachineFurnace;
import redmagic.tileentities.machines.TileEntityMachineSlaugther;
import redmagic.tileentities.machines.TileEntityMachineStorage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachine extends BlockContainer{

	public Icon[] icons = new Icon[ItemBlockMachine.subNames.length];
	public Icon furnaceBurnSide, slaughterSide;
	
	protected BlockMachine(int par1) {
		super(par1, Material.iron);
		this.setUnlocalizedName(BlockIndex.MACHINE_NAME);
		this.setHardness(1.0F);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	public void registerIcons(IconRegister par1IconRegister)
    {
		int count = 0;
        for(String name: ItemBlockMachine.subNames){
        	this.icons[count] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + name);
        	count++;
        }
        this.furnaceBurnSide = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_FURNACE_NAME + "_pointer");
        this.slaughterSide = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_SLAUGTHER_NAME + "_pointer");
    }
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		if(metadata == BlockIndex.MACHINE_FURNACE_METADATA){
			TileEntityMachineFurnace entity = (TileEntityMachineFurnace)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
			if(par5 == entity.side){
				return this.furnaceBurnSide;
			}
		}
		if(metadata == BlockIndex.MACHINE_SLAUGTHER_METADATA){
			TileEntityMachineSlaugther entity = (TileEntityMachineSlaugther)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
			if(par5 == entity.side){
				return this.slaughterSide;
			}
		}
		return this.getIcon(par5, metadata);
    }
	
	public Icon getIcon(int side, int metadata)
    {
		return this.icons[metadata];
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		switch(metadata){
			case BlockIndex.MACHINE_FILTER_METADATA:
				return new TileEntityMachineFilter();
			case BlockIndex.MACHINE_FURNACE_METADATA:
				return new TileEntityMachineFurnace();
			case BlockIndex.MACHINE_STORAGE_METADATA:
				return new TileEntityMachineStorage();
			case BlockIndex.MACHINE_SLAUGTHER_METADATA:
				return new TileEntityMachineSlaugther();
			default: return this.createNewTileEntity(world);
		}
    }
	
	public int idDropped(int metadata, Random par2Random, int par3)
    {
		return this.blockID;
    }
	
	public int damageDropped(int metadata)
    {
		return BlockIndex.MACHINE_FRAME_METADATA;
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		switch(metadata){
			case BlockIndex.MACHINE_FRAME_METADATA:
				return SoulHelper.linkSoul(par5EntityPlayer.getCurrentEquippedItem(), par1World, par2, par3, par4, par5EntityPlayer);
		}
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockMachine.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        if (!par1World.isRemote) {
        	if(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))handleRedstoneSignalOn(par1World, par2, par3, par4);
        	else handleRedstoneSignalOff(par1World, par2, par3, par4);
        }
	}

	private void handleRedstoneSignalOff(World world, int x, int y, int z) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		switch(world.getBlockMetadata(x, y, z)){
		case BlockIndex.MACHINE_FURNACE_METADATA:
			((TileEntityMachineSlaugther)entity).activate();
		}
	}

	private void handleRedstoneSignalOn(World world, int x, int y, int z) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		switch(world.getBlockMetadata(x, y, z)){
		case BlockIndex.MACHINE_FURNACE_METADATA:
			((TileEntityMachineSlaugther)entity).deactivate();
		}
	}

}
