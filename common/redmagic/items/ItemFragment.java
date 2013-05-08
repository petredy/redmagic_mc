package redmagic.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.FragmentHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemFragment extends Item{

	public String[] subNames = new String[]{
		ItemIndex.FRAGMENT_INTELLIGENCE_NAME,
		ItemIndex.FRAGMENT_STRENGTH_NAME,
		ItemIndex.FRAGMENT_CAPACITY_NAME,
		ItemIndex.FRAGMENT_ILLUSION_NAME,
		ItemIndex.FRAGMENT_SATISFACTION_NAME,
		ItemIndex.FRAGMENT_ALL_NAME
	};
	public Icon[] icons = new Icon[subNames.length];
	
	public ItemFragment(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.FRAGMENT_NAME);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		for(int i = 0; i < subNames.length; i++){
			icons[i] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.FRAGMENT_NAME + "_" + subNames[i]);
		}
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, this.subNames.length - 1);
        return super.getUnlocalizedName() + "." + this.subNames[i];
    }
	
	public Icon getIconFromDamage(int damage)
    {
		int i = MathHelper.clamp_int(damage, 0, this.subNames.length - 1);
    	return icons[i];
    }
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < subNames.length; i++){
        	par3List.add(FragmentHelper.createNewFragment(i));
        }
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par1){
		if(stack.stackTagCompound != null){
			if(FragmentHelper.getIntelligence(stack) > 0)list.add("Intelligence: " + ((Integer)FragmentHelper.getIntelligence(stack)).toString());
			if(FragmentHelper.getStrength(stack) > 0)list.add("Strength: " + ((Integer)FragmentHelper.getStrength(stack)).toString());
			if(FragmentHelper.getCapacity(stack) > 0)list.add("Capacity: " + ((Integer)FragmentHelper.getCapacity(stack)).toString());
			if(FragmentHelper.getIllusion(stack) > 0)list.add("Illusion: " + ((Integer)FragmentHelper.getIllusion(stack)).toString());
			if(FragmentHelper.getSatisfaction(stack) > 0)list.add("Satisfaction: " + ((Integer)FragmentHelper.getSatisfaction(stack)).toString());
		}
	}

}
