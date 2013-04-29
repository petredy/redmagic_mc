package redmagic.tileentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import redmagic.helpers.TamingHelper;
import redmagic.items.ItemSoul;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityTaming extends TileEntityInventory{

	public static final int soulSlot = 0;
	public static final int requestSlot1 = 1;
	public static final int requestSlot2 = 2;
	public static final int requestSlot3 = 3;
	public static final int inputSlot = 4;
	
	public List<ItemStack> items;
	
	public TileEntityTaming(){
		super(5, "Taming");
		items = new ArrayList<ItemStack>();
	}
	
	public void updateEntity(){
		if(this.inv[soulSlot] != null && this.inv[soulSlot].getItem() instanceof ItemSoul){
			TamingHelper.changeSoul(this);
			TamingHelper.checkNeeds(this);
			
			if(TamingHelper.getSoulTime(this) >= TamingHelper.getMaxSoulTime()){
				TamingHelper.transformSoul(this);
			}
			
		}else{
			this.inv[requestSlot1] = null;
			this.inv[requestSlot2] = null;
			this.inv[requestSlot3] = null;
		}
		if(this.inv[inputSlot] != null && !this.worldObj.isRemote){
			TamingHelper.feedSoul(this, this.inv[inputSlot]);
			this.inv[inputSlot] = null;
		}
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		
		NBTTagList list = tagCompound.getTagList("items");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound) list.tagAt(i);
			items.add(ItemStack.loadItemStackFromNBT(item));
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		
		NBTTagList list = new NBTTagList();
		Iterator<ItemStack> it = items.iterator();
		while(it.hasNext()){
			ItemStack item = it.next();
			NBTTagCompound itemTag = new NBTTagCompound();
			item.writeToNBT(itemTag);
			list.appendTag(itemTag);
		}
		tagCompound.setTag("items", list);
	}
}
