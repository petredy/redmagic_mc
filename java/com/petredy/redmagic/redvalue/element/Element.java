package com.petredy.redmagic.redvalue.element;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.nbt.NBTTagCompound;

public class Element {

	public String name;
	public float influence;
	public Element(){}
	
	public Element(float influence){
		this.influence = influence;
	}
	
	public float getValue(){
		return influence;
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setString("redmagic.element.name", name);
		tag.setFloat("redmagic.element.influence", influence);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.name = tag.getString("redmagic.element.name");
		this.influence = tag.getFloat("redmagic.element.influence");
	}
	
	public static Element loadFromNBT(NBTTagCompound tag){
		String name = tag.getString("redmagic.element.name");
		Element element = ElementDictionary.getElement(name);
		if(element != null)element.readFromNBT(tag);
		return element;
	}

	public Element copy() {
		Element element = ElementDictionary.getElement(name);
		element.influence = this.influence;
		element.name = this.name;
		return element;
	}
	
}
