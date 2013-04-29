package redmagic.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import redmagic.Redmagic;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;

public class ItemCrystal extends Item{
	
	public String[] subNames = new String[]{
		ItemIndex.CRYSTAL_EMPTY_NAME,
		ItemIndex.CRYSTAL_INTELLIGENCE_NAME,
		ItemIndex.CRYSTAL_STRENGTH_NAME,
		ItemIndex.CRYSTAL_CAPACITY_NAME,
		ItemIndex.CRYSTAL_ILLUSION_NAME,
		ItemIndex.CRYSTAL_SATISFACTION_NAME,
		ItemIndex.CRYSTAL_ALL_NAME
	};
	
	public Icon[] icons = new Icon[subNames.length];
	
	public ItemCrystal(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.CRYSTAL_NAME);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		for(int i = 0; i < subNames.length; i++){
			this.icons[i] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.CRYSTAL_NAME + subNames[i]);
		}
	}
	
	public Icon getIconFromDamage(int par1)
    {
        return this.icons[par1];
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, this.subNames.length - 1);
        return super.getUnlocalizedName() + "." + this.subNames[i];
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < this.subNames.length; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par1){
		if(stack.stackTagCompound != null){
			if(stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_INTELLIGENCE_NAME) > 0)list.add("Intelligence: " + stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_INTELLIGENCE_NAME));
			if(stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_STRENGTH_NAME) > 0)list.add("Strength: " + stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_STRENGTH_NAME));
			if(stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_CAPACITY_NAME) > 0)list.add("Capacity: " + stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_CAPACITY_NAME));
			if(stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_ILLUSION_NAME) > 0)list.add("Illusion: " + stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_ILLUSION_NAME));
			if(stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_SATISFACTION_NAME) > 0)list.add("Satisfaction: " + stack.stackTagCompound.getInteger(ItemIndex.CRYSTAL_SATISFACTION_NAME));
		}
	}

	
}
