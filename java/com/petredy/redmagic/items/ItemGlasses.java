package com.petredy.redmagic.items;


import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.IKeyBound;
import com.petredy.redmagic.api.glasses.IViewable;
import com.petredy.redmagic.client.render.glasses.RenderGlassesOverlay;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Keys;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.GlassesUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemGlasses extends ItemArmor implements IKeyBound{


	public ItemGlasses(int par1, int par2, int par3) {
		super(par1, Items.glassMaterial, par2, par3);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(ItemIndex.GLASSES_NAME);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY){
		RenderGlassesOverlay.render(stack, player, resolution, partialTicks, hasScreen, mouseX, mouseY);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.GLASSES_NAME);
    }

	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding) {
		if(keyBinding.equals(Keys.KEY_EXTRA_NAME)){
			if(player.isSneaking() && GlassesUtils.getMode(stack) != GlassesUtils.OFFLINE){
				MovingObjectPosition location = this.getMovingObjectPositionFromPlayer(player.worldObj, player, true);
				if(location != null){
					TileEntity entity = player.worldObj.getBlockTileEntity(location.blockX, location.blockY, location.blockZ);
					if(entity instanceof IViewable){
						((IViewable)entity).view(player, stack);
					}
				}
			}else{
				GlassesUtils.switchMode(stack);
			}
		}
		
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
        return Reference.MOD_ID + ":" + Textures.GLASSES;
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		return par1ItemStack;
    }

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		ItemStack glasses = new ItemStack(par1, 1, 0);
		GlassesUtils.initialse(glasses);
        par3List.add(glasses);
    }
	
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		GlassesUtils.initialse(par1ItemStack);
	}
}
