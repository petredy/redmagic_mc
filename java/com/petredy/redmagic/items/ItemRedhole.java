package com.petredy.redmagic.items;

import java.util.HashMap;
import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.IKeyBound;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Redholes;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.redhole.Hole;
import com.petredy.redmagic.utils.ItemUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.RedholeUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet11PlayerPosition;
import net.minecraft.network.packet.Packet201PlayerInfo;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class ItemRedhole extends Item implements IKeyBound{

	public HashMap<Integer, Icon>icons = new HashMap<Integer, Icon>();
	
	public ItemRedhole(int id){
		super(id);
		this.setUnlocalizedName(ItemIndex.REDHOLE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDHOLE_NAME);
		
		for(Class cl: Redholes.HOLES){
			try{
				Hole hole = (Hole) cl.newInstance();
				icons.put(hole.id, iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDHOLE_NAME  + "." + hole.name));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public Icon getIconFromDamage(int par1)
    {
		if(par1 > 0)return icons.get(par1);
        return this.itemIcon;
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		Hole hole = RedholeUtils.getHole(par1ItemStack);
		if(hole != null){
			hole.activate(par1ItemStack, par2World, par3EntityPlayer);
			RedholeUtils.saveHole(par1ItemStack, hole);
		}
		return par1ItemStack;
   	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		String name = "";
		Hole hole = RedholeUtils.getHole(par1ItemStack);
		if(hole != null)name = "." + hole.name;
        return this.getUnlocalizedName() + name;
    }
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(new ItemStack(par1, 1, 0));
		for(Class cl: Redholes.HOLES){
			try{
				Hole hole = (Hole) cl.newInstance();
				if(hole != null && hole.id > 0){
					ItemStack redhole = new ItemStack(par1, 1, hole.id);
					RedholeUtils.saveHole(redhole, hole);
					par3List.add(redhole);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		Hole hole = RedholeUtils.getHole(par1ItemStack);
		if(hole != null){
			hole.update(par1ItemStack, par2World, par3Entity, par4, par5);
			RedholeUtils.saveHole(par1ItemStack, hole);
		}
	}

	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyName) {
		Hole hole = RedholeUtils.getHole(stack);
		if(hole != null){
			hole.keyPressed(stack, player, keyName);
			RedholeUtils.saveHole(stack, hole);
		}	
	}
	
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	Hole hole = RedholeUtils.getHole(par1ItemStack);
    	if(hole != null){
    		hole.addInformation(par1ItemStack, par2EntityPlayer, par3List);
    		RedholeUtils.saveHole(par1ItemStack, hole);
    	}
    }


}
