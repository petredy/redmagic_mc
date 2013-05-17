package redmagic.lib.bank;

import redmagic.configuration.Reference;
import redmagic.core.Logger;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	
	
	public ItemData(){}
	
	public ItemData(int itemID, int itemDamage, int amount, boolean tradeable, float price, float tax, boolean buying){
		this.itemID = itemID;
		this.itemDamage = itemDamage;
		this.amount = amount;
		this.tradeable = tradeable;
		this.price = price;
		this.tax = tax;
		this.buying = buying;
	}

	
	public void readFromNBT(NBTTagCompound tag){
		this.itemID = tag.getInteger("ID");
		this.itemDamage = tag.getInteger("Damage");
		this.amount = tag.getInteger("Amount");
		this.tradeable = tag.getBoolean("Tradeable");
		this.price = tag.getFloat("Price");
		this.tax = tag.getFloat("Tax");
		this.buying = tag.getBoolean("Buying");
	}
	
	public static ItemData loadFromNBT(NBTTagCompound tag){
		ItemData data = new ItemData();
		data.readFromNBT(tag);
		return data;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("ID", this.itemID);
		tag.setInteger("Damage", this.itemDamage);
		tag.setInteger("Amount", this.amount);
		tag.setBoolean("Tradeable", this.tradeable);
		tag.setFloat("Price", this.price);
		tag.setFloat("Tax", this.tax);
		tag.setBoolean("Buying", this.buying);
	}

	@Override
	public int compareTo(ItemData data) {
		if(this.itemID < data.itemID || (this.itemID == data.itemID && this.itemDamage < data.itemDamage))return -1;
		if(this.itemID == data.itemID && this.itemDamage == data.itemDamage)return 0;
		if(this.itemID > data.itemID || (this.itemID == data.itemID && this.itemDamage > data.itemDamage))return 1;
		return 0;
	}
	
}
