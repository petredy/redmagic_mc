package redmagic.items;

import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;


public class ItemSoulPickaxe extends ItemPickaxe{

	public ItemSoulPickaxe(int par1) {
		super(par1, EnumToolMaterial.EMERALD);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.PICKAXE_NAME);
		this.efficiencyOnProperMaterial = 15F;
		this.setMaxDamage(500);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.PICKAXE_NAME);
	}
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
    }

}
