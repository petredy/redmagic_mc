package redmagic.lib.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import redmagic.configuration.BankIndex;
import redmagic.core.Logger;

public class BankManager {
	public static int getItemAmount(int itemID, int itemDamage){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null)return item.amount;
		return 0;
	}
	
	public static boolean getItemTradeable(int itemID, int itemDamage){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null)return item.tradeable;
		return false;
	}
	
	public static boolean getItemBuying(int itemID, int itemDamage){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null)return item.buying;
		return false;
	}
	
	public static float getItemTax(int itemID, int itemDamage){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null)return item.tax;
		return BankIndex.TAX;
	}
	
	public static float getItemPrice(int itemID, int itemDamage){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null)return item.price;
		return 0F;
	}
	
	public static boolean addItemAmount(int itemID, int itemDamage, int amount){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null){
			calculateNewPrice(item, false, amount);
			item.amount += amount;
			return true;
		}
		return false;
	}
	
	public static boolean removeItemAmount(int itemID, int itemDamage, int amount){
		ItemData item = BankData.getItemData(itemID, itemDamage);
		if(item != null && item.amount - amount >= 0){
			calculateNewPrice(item, true, amount);
			item.amount -= amount;
			return true;
		}
		return false;
	}
	
	public static void calculateNewPrice(ItemData item, boolean buying, int amount){
		if(item != null){
			float tax = calculateNewTaxAndGetTaxForCalculation(item, buying, amount);
			if(tax > 0){
				item.price *= tax;
			}
		}
	}
	
	public static float calculateNewTaxAndGetTaxForCalculation(ItemData item, boolean buying, int amount){
		if(item != null){
			if(item.buying){
				if(buying){
					item.tax *= 1 + new java.util.Random().nextFloat() * BankIndex.TAX_CHANGE;
					return 1 + amount * item.tax;
				}else{
					item.tax *= 1 - new java.util.Random().nextFloat() * BankIndex.TAX_CHANGE;
					return 1 - amount * item.tax;
				}
			}else{
				if(!buying){
					item.tax *= 1.5 + new java.util.Random().nextFloat() * BankIndex.TAX_CHANGE;
					return 1 - amount * item.tax;
				}else{
					item.tax *= 1 - new java.util.Random().nextFloat() * BankIndex.TAX_CHANGE;
					return 1 + amount * item.tax;
				}
			}
		}
		return 0;	
	}
	
	
	public static ItemStack[] getItemsForRow(int row){
		ItemStack[] stacks = new ItemStack[9];
		int count = 0, index = 0;
		List<ItemData> data = BankData.getItems();
		Collections.sort(data);
		Iterator it = data.iterator();
		while(it.hasNext()){
			ItemData item = (ItemData) it.next();
			Logger.log(new ItemStack(item.itemID, item.amount, item.itemDamage));
			if(count / 9 == row){
				stacks[index++] = new ItemStack(item.itemID, item.amount, item.itemDamage);
			}else if(count / 9 > row)break;
			count++;
		}
		return stacks;
	}
	
	public static List<ItemStack> getAllItems(){
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<ItemData> data = BankData.getItems();
		Collections.sort(data);
		Iterator it = data.iterator();
		while(it.hasNext()){
			ItemData item = (ItemData) it.next();
			items.add(new ItemStack(item.itemID, item.amount, item.itemDamage));
		}
		return items;
	}

	public static ItemStack[] getPreviousAvailableItems(int itemID, int itemDamage) {
		List<ItemData> stacks = new ArrayList<ItemData>();
		List<ItemData> data = BankData.getItems();
		Collections.sort(data);
		Iterator it = data.iterator();
		while(it.hasNext()){
			ItemData item = (ItemData) it.next();
			if((item.itemID < itemID | (item.itemID == itemID && item.itemDamage <= itemDamage)) && item.amount > 0){
				stacks.add(new ItemData(item.itemID, item.itemDamage, 0, false, 0f, 0f, false));
			}
		}
		Collections.sort(stacks);
		ItemStack[] output = new ItemStack[4];
		int count = 3;
		for(int i = stacks.size() - 1; i >= 0 && i > stacks.size() - 5; i--){
			output[count] = new ItemStack(stacks.get(i).itemID, 0, stacks.get(i).itemDamage);
			count--;
		}
		return output;
	}

	public static int getMaxItemDamage(int itemID) {
		int maxItemDamage = 0;
		List<ItemData> data = BankData.getItems();
		Iterator it = data.iterator();
		while(it.hasNext()){
			ItemData item = (ItemData) it.next();
			if(item.itemID == itemID && item.itemDamage > maxItemDamage)maxItemDamage = item.itemDamage;
		}
		return maxItemDamage;
	}

	public static boolean isEnoughCredits(float credits, int itemID, int itemDamage) {
		return credits >= BankManager.getItemPrice(itemID, itemDamage);
	}

	public static boolean hasItemAmount(int itemID, int itemDamage, int amount) {
		return BankManager.getItemAmount(itemID, itemDamage) >= amount;
	};
}
