package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSoulNectar extends Item{

	public ItemSoulNectar(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.SOUL_NECTAR_NAME);
		this.setMaxStackSize(64);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SOUL_NECTAR_NAME);
    } 

}
