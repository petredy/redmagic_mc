package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.ItemUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemMachine extends Item implements IMachineItem{
	
	public final static String[] subNames = {
		Machines.BLOCK_NAME,
		Machines.COLLECTOR_NAME,
		Machines.CONTACT_COOLING_NAME,
		Machines.FURNACE_NAME,
		Machines.DEINTEGRATOR_NAME
	};
	
	public Icon[] icons = new Icon[ItemMachine.subNames.length];

	public ItemMachine(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.MACHINE_NAME);
	}
	
	public void registerIcons(IconRegister iconRegister){
		for(int i = 1; i < ItemMachine.subNames.length; i++){
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.MACHINE_NAME + "." + ItemMachine.subNames[i]);
		}
	}
	
	public Icon getIconFromDamage(int metadata){
		metadata = Math.max(1, Math.min(subNames.length - 1, metadata));
		return icons[metadata];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 1; i <subNames.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
    }

	@Override
	public int getMetadata(ItemStack item) {
		return item.getItemDamage();
	}
	
}
