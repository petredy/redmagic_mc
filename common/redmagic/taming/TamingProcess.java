package redmagic.taming;

import java.util.Iterator;
import java.util.List;

import redmagic.core.Logger;

import net.minecraft.item.ItemStack;

public class TamingProcess {

	public ItemStack output;
	public ItemStack[] items;
	public int need;
	
	public TamingProcess(ItemStack output, ItemStack[] itemStacks, int need){
		this.output = output;
		this.items = itemStacks;
		this.need = need;
	}
	
	public int isCompleted(List<ItemStack> list){
		int count = 0;
		Iterator<ItemStack> it = list.iterator();
		while(it.hasNext()){
			if(this.contains(it.next())) count++;
		}
		return count;
	}
	
	public boolean contains(ItemStack stack){
		for(int i = 0; i<items.length;i++){
			if(items[i] != null && items[i].isItemEqual(stack))return true;
		}
		return false;
	}

	public ItemStack getRandomItemStack() {
		return items[new java.util.Random().nextInt(items.length)];
	}

	public int match(List<ItemStack> list){ 
		int count = isCompleted(list);
		Logger.log(count);
		int percent = (100 * count) / need;
		return percent;
	}
}
