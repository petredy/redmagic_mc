package redmagic.items;

import java.util.List;

import redmagic.Redmagic;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.lib.bag.BagHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBag extends Item{
	
	public ItemBag(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(ItemIndex.BAG_NAME);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.BAG_NAME);
	}
	
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
        if(!BagHelper.isInitialised(stack))BagHelper.initBag(world, stack);
        player.openGui(Redmagic.instance, GuiIndex.BAG, world, BagHelper.getID(stack), 0, 0);
		return stack;
    }
	
	

}
