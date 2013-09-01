package redmagic.lib.potions;

import redmagic.helpers.PotionHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class RedmagicPotion extends Potion{

	public RedmagicPotion() {
		super(PotionHelper.getNextAvailablePotionId(), false, 8171462);
		
	}
	
	public void performEffect(EntityLivingBase living, int par2){
		living.addPotionEffect(new PotionEffect(13, 10, 0));
    }
	
	
	public void affectEntity(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase, int par3, double par4){
		
    }
	
	public boolean isReady(int par1, int par2){
		return true;
    }
}
