package redmagic.lib.sacrifice;

import java.util.List;

import redmagic.api.frame.ISoul;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.ModifierHelper;
import redmagic.items.ItemManager;
import net.minecraft.item.ItemStack;

public class SacrificeSoul extends Sacrifice{

	public SacrificeSoul() {
		super(new ItemStack(ItemManager.soul));
		this.damage = 10;
	}
	
	@Override
	public void sacrifice(ItemStack soulStack, ISoul soul, ItemStack input, List<ModifierHelper> modifiers){
		ISoul inputSoul = (ISoul)input.getItem();
		for(ModifierHelper modifier: modifiers){
			switch(modifier.type){
			case BlockIndex.CRYSTAL_INTELLIGENCE_METADATA:
				if(inputSoul.getIntelligence(input) >= modifier.amount){
					soul.setIntelligence(soulStack, soul.getIntelligence(soulStack) + modifier.amount);
				}
				break;
			case BlockIndex.CRYSTAL_STRENGTH_METADATA:
				if(inputSoul.getStrength(input) >= modifier.amount){
					soul.setStrength(soulStack, soul.getStrength(soulStack) + modifier.amount);
				}
				break;
			case BlockIndex.CRYSTAL_CAPACITY_METADATA:
				if(inputSoul.getCapacity(input) >= modifier.amount){
					soul.setCapacity(soulStack, soul.getCapacity(soulStack) + modifier.amount);
				}
				break;
			case BlockIndex.CRYSTAL_ILLUSION_METADATA:
				if(inputSoul.getIllusion(input) >= modifier.amount){
					soul.setIllusion(soulStack, soul.getIllusion(soulStack) + modifier.amount);
				}
				break;
			}
		}
	}

}
