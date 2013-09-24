package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.dimension.TeleporterOverworld;
import com.petredy.redmagic.dimension.TeleporterSoul;
import com.petredy.redmagic.lib.Dimensions;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Redholes;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.redhole.Hole;
import com.petredy.redmagic.utils.ItemUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.RedholeUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
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

public class ItemRedhole extends Item{

	public ItemRedhole(int id){
		super(id);
		this.setUnlocalizedName(ItemIndex.REDHOLE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDHOLE_NAME);
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
				ItemStack redhole = new ItemStack(par1, 1, 0);
				RedholeUtils.saveHole(redhole, (Hole) cl.newInstance());
				LogUtils.log("Creative Items: create redhole for hole " + cl.getSimpleName());
				par3List.add(redhole);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
