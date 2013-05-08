package redmagic.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockCrystal extends Block{

	public Icon[] icons = new Icon[ItemBlockCrystal.subNames.length];
	
	public BlockCrystal(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.CRYSTAL_NAME);
		this.setLightValue(1.0F);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		int count = 0;
        for(String name: ItemBlockCrystal.subNames){
        	this.icons[count] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + name);
        	count++;
        }
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
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockCrystal.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

}
