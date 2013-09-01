package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockRheniumOre extends Block{

	public BlockRheniumOre(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName(BlockIndex.RHENIUM_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RHENIUM_NAME);
	}

}
