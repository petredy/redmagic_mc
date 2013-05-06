package redmagic.items;

import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBlockifier extends Item{

	public ItemBlockifier(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.BLOCKIFIER_NAME);
		this.setMaxStackSize(1);
		this.setMaxDamage(10);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.BLOCKIFIER_NAME);
	}
	
	
	public boolean itemInteractionForEntity(ItemStack stack, EntityLiving living){
		if(living instanceof EntityPig){
			
		}
        return true;
    }
	
	

}
