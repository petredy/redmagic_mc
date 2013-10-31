package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.es.IESContainer;
import com.petredy.redmagic.es.Environment;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.ItemUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

public class ItemESStorage extends Item implements IESContainer{
	
	public final static String[] subNames = {
		ItemIndex.ES_STORAGE_SMALL_NAME,
		ItemIndex.ES_STORAGE_MEDIUM_NAME,
		ItemIndex.ES_STORAGE_LARGE_NAME
	};
	
	public Icon[] icons = new Icon[subNames.length];
	
	public ItemESStorage(int id){
		super(id);
		this.setUnlocalizedName(ItemIndex.ES_STORAGE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		for(int i = 0; i < subNames.length; i++){
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.ES_STORAGE_NAME + "." + subNames[i]);
		}
	}
	
	@Override
	public Icon getIconFromDamage(int metadata){
		metadata = Math.max(0, Math.min(subNames.length - 1, metadata));
		return icons[metadata];
	}
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < subNames.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	

	@Override
	public Environment getEnvironment(ItemStack stack) {
		Environment environment = new Environment();
		NBTTagCompound data = ItemUtils.getData(stack, "redmagic.es.data");
		if(data != null)environment.readFromNBT(data);
		return environment;
	}

	@Override
	public void setEnvironment(ItemStack stack, Environment environment) {
		NBTTagCompound data = new NBTTagCompound();
		environment.writeToNBT(data);
		ItemUtils.setData(stack, "redmagic.es.data", data);
	}

	@Override
	public float getMaxEnergy(ItemStack stack) {
		switch(stack.getItemDamage()){
		case ItemIndex.ES_STORAGE_SMALL_METADATA: return 300000;
		case ItemIndex.ES_STORAGE_MEDIUM_METADATA: return 900000;
		case ItemIndex.ES_STORAGE_LARGE_METADATA: return 2700000;
		default: return 0;
		}
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	try{
    	Environment env = getEnvironment(par1ItemStack);
    	//par3List.add("Energy: " + env.energy + "/" + getMaxEnergy(par1ItemStack));
    	if(env != null && env.valide){
    		par3List.add("Stored environment: " + env.width + "x" + env.breadth + "x" + env.height);
    	}else{
    		par3List.add("No environment stored.");
    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }

	
}
