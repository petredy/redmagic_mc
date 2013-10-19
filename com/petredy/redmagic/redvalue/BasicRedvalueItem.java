package com.petredy.redmagic.redvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.item.ItemStack;

public class BasicRedvalueItem extends NativeRedvalueItem{

	
	public Collection<RedvalueContent> content = new ArrayList<RedvalueContent>();
	
	public BasicRedvalueItem(ItemStack stack, Collection<RedvalueContent> collection){
		super(stack);
		this.stack = stack;
		this.content = collection;
	}
	
	@Override
	public String toString(){
		return "BasicRedvalueItem: " + this.stack + "@" + this.getComposition();
	}
	
	@Override
	public Composition getComposition(){
		Composition comp = super.getComposition().copy();
		for(RedvalueContent cont: content){
			Composition contComp = cont.composition.copy();
			comp.merge(contComp.multiply(cont.amount));
		}
		return comp;
	}
	
	@Override
	public float getValue(){
		return getComposition().getRedvalue();
	}
	
	public List<RedvalueContent> getContent(){
		List<RedvalueContent> newCon = new ArrayList<RedvalueContent>();
		for(RedvalueContent con: content){
			ItemStack stack = con.stack.copy();
			stack.stackSize = 1;
			newCon.add(new RedvalueContent(stack, con.amount / con.stack.stackSize, con.composition.copy()));
		}
		return newCon;
	}

}
