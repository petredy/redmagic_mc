package com.petredy.redmagic.redvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.ItemStack;

public class BasicRedvalueItem extends NativeRedvalueItem{

	
	public Collection<RedvalueContent> content = new ArrayList<RedvalueContent>();
	
	public BasicRedvalueItem(ItemStack stack, Collection<RedvalueContent> collection){
		super(stack);
		this.stack = stack;
		this.content = collection;
	}
	
	@Override
	public float getValue(){
		float cost = 0;
		for(RedvalueContent con: content){
			cost += RedvalueDictionary.getNativeRedvalue(con.stack) * con.amount;
		}
		return cost;
	}
	
	public List<RedvalueContent> getContent(){
		List<RedvalueContent> newCon = new ArrayList<RedvalueContent>();
		for(RedvalueContent con: content){
			ItemStack stack = con.stack.copy();
			stack.stackSize = 1;
			newCon.add(new RedvalueContent(stack, con.amount / con.stack.stackSize));
		}
		return newCon;
	}

}
