package redmagic.items;

import java.util.List;

import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemSoulMachine extends ItemSoul{

	public String[] soulNames = new String[]{
		ItemIndex.MACHINE_SOUL_FILTER_NAME,
		ItemIndex.MACHINE_SOUL_FURNACE_NAME,
		ItemIndex.MACHINE_SOUL_STORAGE_NAME
	};
	
	public ItemSoulMachine(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.MACHINE_SOUL_NAME);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, this.soulNames.length - 1);
        return super.getUnlocalizedName() + "." + this.soulNames[i];
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < this.soulNames.length; ++j)
        {
        	ItemStack stack = new ItemStack(par1, 1, j);
        	this.setIntelligence(stack, LogicIndex.SOUL_MAX_INTELLIGENCE);
        	this.setStrength(stack, LogicIndex.SOUL_MAX_STRENGTH);
        	this.setCapacity(stack, LogicIndex.SOUL_MAX_CAPACITY);
        	this.setIllusion(stack, LogicIndex.SOUL_MAX_ILLUSION);
        	this.setSatisfaction(stack, 0);
        	this.setType(stack, j);
            par3List.add(stack);
        }
    }
	
	

}
