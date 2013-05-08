package redmagic.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemCrafting extends Item{

	public String[] subNames = new String[]{
		ItemIndex.CRAFTING_SOUL_POWDER_NAME,
		ItemIndex.CRAFTING_SOUL_INGOT_NAME,
		ItemIndex.CRAFTING_REDSTONE_SHARD_NAME,
		ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_NAME,
		ItemIndex.CRAFTING_FLINT_IRON_INGOT_NAME
	};
	public Icon[] icons = new Icon[subNames.length];
	
	public ItemCrafting(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.CRAFTING_NAME);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		for(int i = 0; i < subNames.length; i++){
			icons[i] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.CRAFTING_NAME + "_" + subNames[i]);
		}
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, this.subNames.length - 1);
        return super.getUnlocalizedName() + "." + this.subNames[i];
    }
	
	public Icon getIconFromDamage(int damage)
    {
		int i = MathHelper.clamp_int(damage, 0, this.subNames.length - 1);
    	return icons[i];
    }
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < subNames.length; i++){
        	par3List.add(new ItemStack(par1, 1, i));
        }
    }

}
