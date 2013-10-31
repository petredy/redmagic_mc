package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.machinery.MachineHandler;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.utils.ItemUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemMachine extends Item implements IMachineItem{
	
	public final static String[] subNames = {
		Machines.BLOCK_NAME,
		Machines.COLLECTOR_NAME,
		Machines.CONTACT_COOLING_NAME,
		Machines.FURNACE_NAME,
		Machines.DISINTEGRATOR_NAME,
		Machines.CHARGER_NAME,
		Machines.REFRIGERATOR_NAME,
		Machines.FREEZER_NAME,
		Machines.COMPACTOR_NAME,
		Machines.RECYCLER_NAME,
		Machines.SIEVE_NAME,
		Machines.CRYSTALIZER_NAME
	};
	
	public Icon[] icons = new Icon[ItemMachine.subNames.length];

	public ItemMachine(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.MACHINE_NAME);
		this.setMaxStackSize(1);
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
			ItemStack machine = new ItemStack(par1, 1, i);
			initialiseTribological(machine);
			par3List.add(machine);
		}
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		Tribological tribological = getTribological(par1ItemStack);
		if(tribological != null){
			par3List.add("Status: " +tribological.getStatus() + "%");
		}
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		initialiseTribological(par1ItemStack);
	}
	
	public void initialiseTribological(ItemStack stack){
		Machine machine = MachineHandler.getMachine(getMetadata(stack));
		if(machine != null){
			setTribological(stack, machine.tribological);
		}
	}
	
	@Override
	public int getMetadata(ItemStack item) {
		return item.getItemDamage();
	}
	
	@Override
	public Tribological getTribological(ItemStack item) {
		return Tribological.loadFromNBT(ItemUtils.getData(item, "redmagic.machine.tribological"));
	}

	@Override
	public void setTribological(ItemStack item, Tribological tribological) {
		NBTTagCompound tag = new NBTTagCompound();
		if(tribological != null){
			tribological.writeToNBT(tag);
			ItemUtils.setData(item, "redmagic.machine.tribological", tag);
		}
	}
	
}
