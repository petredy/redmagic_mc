package redmagic.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.configuration.ScrollIndex;
import redmagic.helpers.GlassesHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemScroll extends Item{
	
	public ItemScroll(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.SCROLL_NAME);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SCROLL_NAME);
    }
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		int id = stack.getItemDamage();
		if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).isItemEqual(new ItemStack(ItemManager.glasses)) && GlassesHelper.getMode(player.getCurrentArmor(3)) != GlassesHelper.OFFLINE){
			player.openGui(Redmagic.instance, GuiIndex.SCROLL, world, id, 1, 0);
		}else{
			player.openGui(Redmagic.instance, GuiIndex.SCROLL, world, id, 0, 0);
		}
		
		
        return stack;
    }
	
}
