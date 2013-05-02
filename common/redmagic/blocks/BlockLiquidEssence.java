package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockLiquidEssence extends BlockFluid{

	protected BlockLiquidEssence(int par1) {
		super(par1, Material.water);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.LIQUID_ESSENCE_NAME);
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
    	this.theIcon = new Icon[]{par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.LIQUID_ESSENCE_NAME), par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.LIQUID_ESSENCE_NAME + "_flowing")};
    }

}
