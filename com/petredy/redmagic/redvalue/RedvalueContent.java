package com.petredy.redmagic.redvalue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.petredy.redmagic.redvalue.element.Composition;

import net.minecraft.item.ItemStack;

public class RedvalueContent {
	public ItemStack stack;
	public float amount;
	public Composition composition;
	
	public RedvalueContent(ItemStack stack, float amount, Composition composition){
		this.stack = stack;
		this.amount = amount;
		this.composition = composition;
	}
	
	public String toString(){
		return "RedvalueContent: " + amount + "@" + stack + " @" + composition; 
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
