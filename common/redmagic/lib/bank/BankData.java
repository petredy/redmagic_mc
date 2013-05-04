package redmagic.lib.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.handlers.DataHandler;

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
		Logger.log("null");
		return null;
	}
	
	public static void register(int itemID, int itemDamage, int amount, boolean tradeable, float price, float tax, boolean buying){
		data.add(new ItemData(itemID, itemDamage, amount, tradeable, price, tax, buying));
	}
	
	public void save(Configuration config){
		Iterator it = this.data.iterator();
		int count = 0;
		while(it.hasNext()){
			ItemData data = (ItemData) it.next();
			data.config(config, count);
			count++;
		}
		if(count == 0){
			Logger.log("Couldn't save any redbank data");
		}
	}
	
	public void load(Configuration config){
		Collection<Property> data  = config.getCategory(Reference.BANK_DATA_TYPE).values();
		Iterator it = data.iterator();
		int count = 0;
		while(it.hasNext()){
			Property item = (Property) it.next();
			this.data.add(ItemData.loadFromConfig(item));
			count++;
		}
		Logger.log(count);
		if(count == 0){
			DataHandler.loadDefault();
		}
	}

	public static List<ItemData> getItems() {
		return data;
	}
	
	
}
