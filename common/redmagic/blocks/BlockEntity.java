package redmagic.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.BlockHelper;
import redmagic.tileentities.TileEntityBlockEntity;
import redmagic.tileentities.machines.TileEntityMachineFilter;
import redmagic.tileentities.machines.TileEntityMachineFurnace;
import redmagic.tileentities.machines.TileEntityMachineStorage;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockEntity extends BlockContainer{

	public Icon[] icons = new Icon[ItemBlockEntity.subNames.length];
	
	public BlockEntity(int par1) {
		super(par1, Material.cloth);
		this.setCreativeTab(Redmagic.tabRedMagic);
		
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		int count = 0;
        for(String name: ItemBlockEntity.subNames){
        	this.icons[count] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + name);
        	count++;
        }
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
		return new TileEntityBlockEntity(metadata);
    }
	
	public int idDropped(int metadata, Random par2Random, int par3)
    {
		return this.blockID;
    }
	
	public int damageDropped(int metadata)
    {
		return metadata;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockEntity.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	public void onBlockPlacedBy(World par1World, int par1, int par2, int par3, EntityLiving par4EntityPlayer, ItemStack stack)
    {
    	super.onBlockPlacedBy(par1World, par1, par2, par3, par4EntityPlayer, stack);
    	TileEntityBlockEntity entity = (TileEntityBlockEntity) par1World.getBlockTileEntity(par1, par2, par3);
    	entity.side = BlockHelper.getRotation(par1World, par1, par2, par3, par4EntityPlayer);
    }

}
