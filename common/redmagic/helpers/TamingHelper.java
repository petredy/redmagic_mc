package redmagic.helpers;

import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.taming.TamingProcess;
import redmagic.taming.TamingRegistry;
import redmagic.tileentities.TileEntityTaming;
import redmagic.api.frame.ISoul;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TamingHelper {

	public static void feedSoul(TileEntityTaming tileEntity, ItemStack stack) {
		tileEntity.items.add(stack);
		if(TamingHelper.entityWantsItem(tileEntity, stack)){
			TamingHelper.changeNeeds(tileEntity);
			ItemStack soulStack = tileEntity.inv[TileEntityTaming.soulSlot];
			((ISoul)soulStack.getItem()).setSatisfaction(soulStack, ((ISoul)soulStack.getItem()).getSatisfaction(soulStack) - LogicIndex.TAMING_REQUEST_PLUS);
			if(((ISoul)soulStack.getItem()).getSatisfaction(soulStack) < 0)((ISoul)soulStack.getItem()).setSatisfaction(soulStack, 0);
		}else{
			ItemStack soulStack = tileEntity.inv[TileEntityTaming.soulSlot];
			((ISoul)soulStack.getItem()).setSatisfaction(soulStack, ((ISoul)soulStack.getItem()).getSatisfaction(soulStack) + LogicIndex.TAMING_REQUEST_MINUS);
			if(((ISoul)soulStack.getItem()).getSatisfaction(soulStack) > LogicIndex.SOUL_MAX_SATISFACTION)((ISoul)soulStack.getItem()).setSatisfaction(soulStack, LogicIndex.SOUL_MAX_SATISFACTION);
		}
	}

	private static boolean entityWantsItem(TileEntityTaming tileEntity, ItemStack stack) {
		if(TamingHelper.entityRequestsItem(tileEntity.inv[TileEntityTaming.requestSlot1], stack))return true;
		if(TamingHelper.entityRequestsItem(tileEntity.inv[TileEntityTaming.requestSlot2], stack))return true;
		if(TamingHelper.entityRequestsItem(tileEntity.inv[TileEntityTaming.requestSlot3], stack))return true;
		return false;
	}

	private static boolean entityRequestsItem(ItemStack request, ItemStack stack){
		if(request != null && request.isItemEqual(stack)){
			if(request.stackSize > stack.stackSize){
				request.stackSize -= stack.stackSize;
				return false;
			}
			request = null;
			return true;
		}
		return false;
	}
	
	public static void changeNeeds(TileEntityTaming tileEntity) {
		tileEntity.setInventorySlotContents(TileEntityTaming.requestSlot1, TamingHelper.getNeed());
		tileEntity.setInventorySlotContents(TileEntityTaming.requestSlot2, TamingHelper.getNeed());
		tileEntity.setInventorySlotContents(TileEntityTaming.requestSlot3, TamingHelper.getNeed());
	}
	
	private static ItemStack getNeed() {
		TamingProcess process = TamingRegistry.getProcess(new java.util.Random().nextInt(TamingRegistry.getProcessAmount()));
		return process.getRandomItemStack();
	}
	
	public static void changeSoul(TileEntityTaming tileEntity){
		long time = TamingHelper.getSoulTimeDelta(tileEntity);
		int damage = (int) (time * LogicIndex.TAMING_DAMAGE_PER_SECOND);
		Logger.log("damage: " +  damage);
		ItemStack soulStack = tileEntity.inv[TileEntityTaming.soulSlot];
		ISoul soul = ((ISoul)soulStack.getItem());
		soul.setSatisfaction(soulStack, soul.getSatisfaction(soulStack) + damage);
		if(soul.getSatisfaction(soulStack) > LogicIndex.SOUL_MAX_SATISFACTION)tileEntity.inv[TileEntityTaming.soulSlot] = null;
	}

	private static long getSoulTimeDelta(TileEntityTaming tileEntity) {
		long time = TamingHelper.getSoulTime(tileEntity);
		ItemStack soulStack = tileEntity.inv[TileEntityTaming.soulSlot];
		long deltaTime = time - soulStack.stackTagCompound.getLong("timeDelta");
		soulStack.stackTagCompound.setLong("timeDelta", time);
		return deltaTime;
	}

	public static void checkNeeds(TileEntityTaming tileEntity) {
			if(!TamingHelper.hasNeeds(tileEntity)){
				TamingHelper.changeNeeds(tileEntity);
			}
	}

	public static void transformSoul(TileEntityTaming tileEntity) {	
		ItemStack nextSoul = TamingRegistry.getOutput(tileEntity);
		nextSoul.stackTagCompound = tileEntity.inv[TileEntityTaming.soulSlot].stackTagCompound;
		((ISoul)nextSoul.getItem()).setType(nextSoul, SoulHelper.getTypeByItem(nextSoul));
		nextSoul.stackTagCompound.setLong("time", 0);
		nextSoul.stackTagCompound.setLong("timeDelta", 0);
		tileEntity.setInventorySlotContents(TileEntityTaming.soulSlot, nextSoul);
	}

	public static long getMaxSoulTime() {
		return LogicIndex.TAMING_MAX_TIME;
	}

	public static long getSoulTime(TileEntityTaming tileEntity) {
		ItemStack itemStack = tileEntity.getStackInSlot(TileEntityTaming.soulSlot);
		if(itemStack == null || itemStack != null && itemStack.stackTagCompound == null)return 0;
		return tileEntity.worldObj.getWorldTime() - itemStack.stackTagCompound.getLong("time");
	}

	private static boolean hasNeeds(TileEntityTaming tileEntity) {
		if(tileEntity.inv[TileEntityTaming.requestSlot1] == null && tileEntity.inv[TileEntityTaming.requestSlot2] == null && tileEntity.inv[TileEntityTaming.requestSlot3] == null)return false;
		return true;
	}

	public static TileEntityTaming getTileEntity(World world, int id) {
		EntityLiving entity = (EntityLiving) world.getEntityByID(id);
		NBTTagCompound tag = entity.getEntityData();
		TileEntityTaming tileEntity = new TileEntityTaming();
		NBTTagCompound redmagic = (NBTTagCompound) tag.getTag(Reference.MOD_ID);
		if(redmagic == null)tag.setTag(Reference.MOD_ID, new NBTTagCompound());
		tileEntity.readFromNBT((NBTTagCompound) tag.getTag(Reference.MOD_ID));
		tileEntity.worldObj = world;
		tileEntity.updateEntity();
		return tileEntity;
	}

	public static void saveTileEntity(EntityLiving living, TileEntityTaming tileEntity) {
		NBTTagCompound tag = living.getEntityData();
		tileEntity.writeToNBT((NBTTagCompound) tag.getTag(Reference.MOD_ID));
	}

	@SuppressWarnings("static-access")
	public static void infectSoul(EntityLiving living, ItemStack stack) {
		TileEntityTaming tileEntity = TamingHelper.getTileEntity(living.worldObj, living.entityId);
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setLong("time", living.worldObj.getWorldTime());
		tileEntity.inv[tileEntity.soulSlot] = stack;
		tileEntity.updateEntity();
		TamingHelper.saveTileEntity(living, tileEntity);
	}
}
