package redmagic.lib.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.handlers.DataHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class BankData {
	
	public static List<ItemData> data = new ArrayList<ItemData>(); 
	
	public static ItemData getItemData(int itemID, int itemDamage){
		Iterator it = data.iterator();
		while(it.hasNext()){
			ItemData item = (ItemData)it.next();
			if(item.itemID == itemID && item.itemDamage == itemDamage){
				return item;
			}
		}
		
		return null;
	}
	
	public static void register(int itemID, int itemDamage, int amount, boolean tradeable, float price, float tax, boolean buying){
		ItemData newData = new ItemData(itemID, itemDamage, amount, tradeable, price, tax, buying);
		ItemData momData = getItemData(itemID, itemDamage);
		data.remove(momData);
		if(momData != null && newData.amount == 0)newData.amount = momData.amount;
		data.add(newData);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		Iterator<ItemData> it = data.iterator();
		while(it.hasNext()){
			ItemData item = it.next();
			if(item != null){
				NBTTagCompound tmp = new NBTTagCompound();
				item.writeToNBT(tmp);
				list.appendTag(tmp);
			}
		}
		tag.setTag(Reference.MOD_ID, list);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList(Reference.MOD_ID);
		this.data.clear();
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound data = (NBTTagCompound) list.tagAt(i);
			if(data != null){
				this.data.add(ItemData.loadFromNBT(data));
			}
		}
	}

	public static List<ItemData> getItems() {
		return data;
	}
	
	
}
