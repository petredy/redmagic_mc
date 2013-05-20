package redmagic.items;

import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;

public class ItemBroom extends ItemTool{

	protected ItemBroom(int par1) {
		super(par1, 0, EnumToolMaterial.EMERALD, new Block[]{Block.dirt});
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
		
		this.efficiencyOnProperMaterial = 2000F;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.BROOM_NAME);
	}

}
