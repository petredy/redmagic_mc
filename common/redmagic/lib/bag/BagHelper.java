package redmagic.lib.bag;

import redmagic.configuration.Reference;
import redmagic.helpers.ItemHelper;
import redmagic.tileentities.TileEntityBag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BagHelper {
	public static void initBag(World world, ItemStack bag){
		int id = world.getUniqueDataId(Reference.MOD_ID + ".bag.");
		BagData data = new BagData(Reference.MOD_ID + ".bag." + id);
		data.bag = new TileEntityBag();
		data.markDirty();
		ItemHelper.setInteger(bag, Reference.MOD_ID + ".bag" , id);
		world.setItemData(Reference.MOD_ID + ".bag." + id, data);
	}
	
	public static boolean isInitialised(ItemStack bag){
		return bag.stackTagCompound != null && bag.stackTagCompound.hasKey(Reference.MOD_ID + ".bag");
	}
	
	public static int getID(ItemStack stack){
		return isInitialised(stack) ? ItemHelper.getInteger(stack, Reference.MOD_ID + ".bag") : -1;
	}
	
	public static BagData getData(World world, int id){
		BagData data = (BagData) world.loadItemData(BagData.class, Reference.MOD_ID + ".bag." + id);
		return data;
	}
	
	public static TileEntityBag createTileByData(BagData data, World world){
		TileEntityBag bag = data != null && data.bag != null ? data.bag : new TileEntityBag();
		bag.worldObj = world;
		return bag;
	}

	public static void storeData(TileEntityBag entity, int id) {
		BagData data = getData(entity.worldObj,id);
		if(data != null){
			data.bag = entity;
			data.markDirty();
		}else{
			data = new BagData(Reference.MOD_ID + ".bag." + id);
			data.bag = entity;
			data.markDirty();
			entity.worldObj.setItemData(Reference.MOD_ID + ".bag." + id, data);
		}
	}
}
