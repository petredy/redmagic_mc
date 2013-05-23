package redmagic.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import redmagic.Redmagic;
import redmagic.api.frame.ISoul;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.SoulHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemSoul extends Item implements ISoul{

	public ItemSoul(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.SOUL_NAME);
		this.setMaxDamage(LogicIndex.SOUL_MAX_SATISFACTION);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par1){
		if(stack.stackTagCompound != null){
			list.add("Type: " + LogicIndex.SOUL_TYPES[this.getType(stack)]);
			list.add("Intelligence: " + ((Integer)this.getIntelligence(stack)).toString());
			list.add("Strength: " + ((Integer)this.getStrength(stack)).toString());
			list.add("Capacity: " + ((Integer)this.getCapacity(stack)).toString());
			list.add("Illusion: " + ((Integer)this.getIllusion(stack)).toString());
			list.add("Satisfaction: " + ((Integer)this.getSatisfaction(stack)).toString());
		}
	}
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(SoulHelper.createNewSoul());
		for(int i = 0; i < LogicIndex.SOUL_TYPES.length; i++){
			ItemStack soul = SoulHelper.createSoul(LogicIndex.SOUL_MAX_INTELLIGENCE, LogicIndex.SOUL_MAX_STRENGTH, LogicIndex.SOUL_MAX_CAPACITY, LogicIndex.SOUL_MAX_ILLUSION, LogicIndex.SOUL_MAX_SATISFACTION);
			((ISoul)soul.getItem()).setType(soul, i);
			par3List.add(soul);
		}
		par3List.add(SoulHelper.createSoul(LogicIndex.SOUL_MAX_INTELLIGENCE, LogicIndex.SOUL_MAX_STRENGTH, LogicIndex.SOUL_MAX_CAPACITY, LogicIndex.SOUL_MAX_ILLUSION, LogicIndex.SOUL_MAX_SATISFACTION));
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SOUL_NAME);
    }
	
	@Override
	public void setIntelligence(ItemStack stack, int intelligence) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(Reference.MOD_ID + "_intelligence", intelligence);
	}

	@Override
	public int getIntelligence(ItemStack stack) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(Reference.MOD_ID + "_intelligence");
	}

	@Override
	public void setStrength(ItemStack stack, int strength) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(Reference.MOD_ID + "_strength", strength);
	}

	@Override
	public int getStrength(ItemStack stack) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(Reference.MOD_ID + "_strength");
	}

	@Override
	public void setCapacity(ItemStack stack, int capacity) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(Reference.MOD_ID + "_capacity", capacity);
	}

	@Override
	public int getCapacity(ItemStack stack) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(Reference.MOD_ID + "_capacity");
	}

	@Override
	public void setIllusion(ItemStack stack, int illusion) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(Reference.MOD_ID + "_illusion", illusion);
	}

	@Override
	public int getIllusion(ItemStack stack) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(Reference.MOD_ID + "_illusion");
	}

	@Override
	public int getType(ItemStack stack) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(Reference.MOD_ID + "_type");
	}

	@Override
	public void setType(ItemStack stack, int type) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(Reference.MOD_ID + "_type", type);
	}

	@Override
	public void setSatisfaction(ItemStack stack, int resistence) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger(Reference.MOD_ID + "_satisfaction", resistence);
	}

	@Override
	public int getSatisfaction(ItemStack stack) {
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		return stack.stackTagCompound.getInteger(Reference.MOD_ID + "_satisfaction");
	}

}
