package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStatemeter extends Item{

	public ItemStatemeter(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.STATEMETER_NAME);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.STATEMETER_NAME);
    }
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
		player.openGui(Redmagic.instance, GuiIndex.TAMING, player.worldObj, entity.entityId, 0, 0);
        return true;
    }

	
}
