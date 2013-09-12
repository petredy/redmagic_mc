package com.petredy.redmagic.redvalue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RedvalueContent {
	public ItemStack stack;
	public float amount;
	
	public RedvalueContent(ItemStack stack, float amount){
		this.stack = stack;
		this.amount = amount;
	}
	
	public String toString(){
		return "RedvalueContent: " + amount + "@" + stack; 
	}
	
	
	public static Collection<RedvalueContent> sort(List<RedvalueContent> list){
		HashMap<String, RedvalueContent> map = new HashMap<String, RedvalueContent>();
		for(RedvalueContent value: list){
			if(map.containsKey(value.stack.getUnlocalizedName())){
				RedvalueContent val = map.get(value.stack.getUnlocalizedName());
				val.amount += value.amount;
			}else{
				map.put(value.stack.getUnlocalizedName(), value);
			}
		}
		return map.values();
	}
}
