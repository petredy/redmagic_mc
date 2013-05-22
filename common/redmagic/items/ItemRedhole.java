package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.api.essence.IStorage;
import redmagic.api.items.IKeyBound;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.handlers.DataHandler;
import redmagic.lib.bank.BankData;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemRedhole extends Item implements IKeyBound{

	public ItemRedhole(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
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
		MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, true);
		if(position != null && !world.isRemote){
			TileEntity entity = world.getBlockTileEntity(position.blockX, position.blockY, position.blockZ);
			if(entity instanceof IStorage){
				IStorage storage = (IStorage)entity;
				player.addChatMessage("Essences: " + storage.getEssences() + "/" + storage.getMaxEssences());
			}
		}
        return stack;
    }
	
	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding) {
		if(keyBinding.equals(Reference.KEY_EXTRA_NAME)){
			Logger.log("reset Bank");
			Redmagic.bankData = new BankData();
			DataHandler.loadDefault();
			
		}
	}

}
