package redmagic.blocks;

import java.util.Random;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import redmagic.items.ItemManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockSoulCrystalOre extends Block{

	public BlockSoulCrystalOre(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SOUL_CRYSTAL_ORE_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_CRYSTAL_ORE_NAME);
    }

	public int idDropped(int par1, Random par2Random, int par3)
    {
        return ItemManager.soulCrystal.itemID;
    }
	
	public int quantityDropped(Random par1Random)
    {
        return 1 + par1Random.nextInt(4);
    }
}
