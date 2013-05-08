package redmagic.lib.sacrifice;

import java.util.List;

import redmagic.api.frame.ISoul;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.FragmentHelper;
import redmagic.helpers.ModifierHelper;
import redmagic.items.ItemManager;
import redmagic.items.ItemFragment;
import net.minecraft.item.ItemStack;

public class SacrificeSoulFragment extends Sacrifice{

	public SacrificeSoulFragment() {
		super(new ItemStack(ItemManager.fragment));
	}
	
	public boolean matches(ItemStack input){
		return this.input.getItem() instanceof ItemFragment;
	}
	
	@Override
	public void sacrifice(ItemStack soulStack, ISoul soul, ItemStack input, List<ModifierHelper> modifiers){
		for(ModifierHelper modifier: modifiers){
			switch(modifier.type){
			case BlockIndex.CRYSTAL_INTELLIGENCE_METADATA:
				if(FragmentHelper.getIntelligence(input) >= modifier.amount){
					soul.setIntelligence(soulStack, soul.getIntelligence(soulStack) + modifier.amount);
				}else{
					soul.setIntelligence(soulStack, soul.getIntelligence(soulStack) + FragmentHelper.getIntelligence(input));
				}
				break;
			case BlockIndex.CRYSTAL_STRENGTH_METADATA:
				if(FragmentHelper.getStrength(input) >= modifier.amount){
					soul.setStrength(soulStack, soul.getStrength(soulStack) + modifier.amount);
				}else{
					soul.setStrength(soulStack, soul.getStrength(soulStack) + FragmentHelper.getStrength(input));
				}
				break;
			case BlockIndex.CRYSTAL_CAPACITY_METADATA:
				if(FragmentHelper.getCapacity(input) >= modifier.amount){
					soul.setCapacity(soulStack, soul.getCapacity(soulStack) + modifier.amount);
				}else{
					soul.setCapacity(soulStack, soul.getCapacity(soulStack) + FragmentHelper.getCapacity(input));
				}
				break;
			case BlockIndex.CRYSTAL_ILLUSION_METADATA:
				if(FragmentHelper.getIllusion(input) >= modifier.amount){
					soul.setIllusion(soulStack, soul.getIllusion(soulStack) + modifier.amount);
				}else{
					soul.setIllusion(soulStack, soul.getIllusion(soulStack) + modifier.amount);
				}
				break;
			}
		}
		Logger.log(FragmentHelper.getSatisfaction(input));
		if(FragmentHelper.getSatisfaction(input) > 0){
			soul.setSatisfaction(soulStack, soul.getSatisfaction(soulStack) + FragmentHelper.getSatisfaction(input));
		}
	}

}
