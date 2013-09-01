package redmagic.helpers;

import redmagic.lib.potions.RedmagicPotion;
import net.minecraft.potion.Potion;

public class PotionHelper {
	
	public static int getNextAvailablePotionId(){
		for(int i = 0; i < Potion.potionTypes.length; i++){
			if(Potion.potionTypes[i] == null)return i;
		}
		return -1;
	}
	
}
