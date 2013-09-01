package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.api.items.IKeyBound;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemRedhole extends Item implements IKeyBound{

	public ItemRedhole(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(ItemIndex.REDHOLE_NAME);
	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDHOLE_NAME);
    }
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		PlayerInformationHelper.setPlayerInformation(PlayerInformationHelper.initialisePlayerInformation(player), player.worldObj);
		if(!world.isRemote){
			MinecraftServer.getServer().worldServers[0].setWorldTime(0);
		}
        return stack;
    }
	
	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding) {
		
	}

}
