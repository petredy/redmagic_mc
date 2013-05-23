package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
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
import net.minecraftforge.common.IArmorTextureProvider;
import redmagic.Redmagic;
import redmagic.api.glasses.IViewable;
import redmagic.api.items.IKeyBound;
import redmagic.client.renderers.glasses.RenderGlassesOverlay;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.configuration.Texture;
import redmagic.core.Logger;
import redmagic.helpers.GlassesHelper;

public class ItemGlasses extends ItemArmor implements IKeyBound{


	public ItemGlasses(int par1, int par2, int par3) {
		super(par1, EnumArmorMaterial.DIAMOND, par2, par2);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.ARMOR_SOUL_NAME);
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
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.ARMOR_SOUL_NAME);
    }

	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding) {
		if(keyBinding.equals(Reference.KEY_EXTRA_NAME)){
			if(player.isSneaking() && GlassesHelper.getMode(stack) != GlassesHelper.OFFLINE){
				MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(player.worldObj, player, true);
				if(position != null){
					TileEntity entity = player.worldObj.getBlockTileEntity(position.blockX, position.blockY, position.blockZ);
					if(entity instanceof IViewable){
						((IViewable)entity).view(player, stack);
					}
				}
			}else{
				GlassesHelper.switchMode(stack);
			}
		}
		
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return Texture.GLASSES;
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		return par1ItemStack;
    }

}
