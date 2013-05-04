package redmagic.lib.bank;

import redmagic.configuration.Reference;
import redmagic.core.Logger;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ItemData implements Comparable<ItemData>{
	
	
	public int itemID;
	public int itemDamage;
	public int amount;
	public boolean tradeable;
	public float price;
	public float tax;
	public boolean buying;
	
	
	public ItemData(int itemID, int itemDamage, int amount, boolean tradeable, float price, float tax, boolean buying){
		this.itemID = itemID;
		this.itemDamage = itemDamage;
		this.amount = amount;
		this.tradeable = tradeable;
		this.price = price;
		this.tax = tax;
		this.buying = buying;
	}


	public Property config(Configuration config, int index) {
		return config.get(Reference.BANK_DATA_TYPE, ((Integer)index).toString(), "" + this.itemID + Reference.BANK_DATA_SPLITTER + this.itemDamage + Reference.BANK_DATA_SPLITTER + this.amount + Reference.BANK_DATA_SPLITTER + this.tradeable + Reference.BANK_DATA_SPLITTER + this.price + Reference.BANK_DATA_SPLITTER + this.tax + Reference.BANK_DATA_SPLITTER + this.buying);
	}


	public static ItemData loadFromConfig(Property data) {
		String encData = data.getString();
		if(!encData.isEmpty()){
			String[] decData = encData.split(Reference.BANK_DATA_SPLITTER);
			if(decData.length == 7){
				return new ItemData(Integer.parseInt(decData[0]), Integer.parseInt(decData[1]), Integer.parseInt(decData[2]), Boolean.parseBoolean(decData[3]), Float.parseFloat(decData[4]), Float.parseFloat(decData[5]), Boolean.parseBoolean(decData[6]));
			}
		}
		return null;
	}


	@Override
	public int compareTo(ItemData data) {
		if(this.itemID < data.itemID || (this.itemID == data.itemID && this.itemDamage < data.itemDamage))return -1;
		if(this.itemID == data.itemID && this.itemDamage == data.itemDamage)return 0;
		if(this.itemID > data.itemID || (this.itemID == data.itemID && this.itemDamage > data.itemDamage))return 1;
		return 0;
	}
	
}
