package redmagic.lib.sacrifice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import redmagic.api.frame.ISoul;
import redmagic.core.Logger;
import redmagic.helpers.ModifierHelper;

import net.minecraft.item.ItemStack;

public class SacrificeRegistry {

	private static List<Sacrifice> list = new ArrayList<Sacrifice>();
	
	public static void register(Sacrifice sacrifice){
		list.add(sacrifice);
	}
	
	public static boolean changeSoul(ItemStack input, ItemStack soulStack, List<ModifierHelper> modifiers){
		if(soulStack != null && input != null && soulStack.getItem() instanceof ISoul){
			ISoul soul = (ISoul)soulStack.getItem();
			Iterator<Sacrifice> it = list.iterator();
			while(it.hasNext()){
				Sacrifice sacrifice = it.next();
				Logger.log(input);
				if(input != null && sacrifice.matches(input)){
					sacrifice.sacrifice(soulStack, soul, input, modifiers);
					if(sacrifice.damage(soulStack, soul, input, modifiers))return false;
				}
			}
		}
		return true;
	}
}
