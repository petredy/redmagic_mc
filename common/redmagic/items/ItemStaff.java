package redmagic.items;

import redmagic.Redmagic;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.ItemIndex;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStaff extends Item{

	public ItemStaff(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.STAFF_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}

	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
        if(information.pathManager.isTalentUnlocked(Talent.lifeExorcism)){
        	information.pathManager.useAbility(AbilityIndex.EXORCISM, new Object[]{
        		stack, player, entity, information	
        	});
        	return true;
        }
		
		return false;
    }
	
}
