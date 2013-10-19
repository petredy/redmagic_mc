package com.petredy.redmagic.redvalue.element;

import java.util.Collection;
import java.util.HashMap;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Composition {

	protected HashMap<String, Element> elements = new HashMap<String, Element>();
	
	public Composition(){
		
	}
	
	public void addElement(Element element){
		elements.put(element.name, element);
	}
	
	public Element getElement(String name){
		return elements.get(name);
	}
	
	public Collection<Element> getAllElements(){
		return elements.values();
	}
	
	public static Composition getStandard(float earth, float nature, float water, float fire, float metal){
		Composition comp = new Composition();
		comp.addElement(new ElementEarth(earth));
		comp.addElement(new ElementNature(nature));
		comp.addElement(new ElementWater(water));
		comp.addElement(new ElementFire(fire));
		comp.addElement(new ElementMetal(metal));
		return comp;
	}
	
	public float getValue(String element){
		Element el = getElement(element);
		if(el != null)return el.getValue();
		return 0;
	}
	
	public void addValue(String element, float influence){
		Element el = getElement(element);
		if(el != null)el.influence += influence;
		else {
			Element newEl = ElementDictionary.getElement(element);
			if(newEl != null){
				newEl.influence = influence;
				addElement(newEl);
			}
		}
	}
	
	public float decreaseValue(String element, float influence){
		Element el = getElement(element);
		if(el != null){
			if(el.influence - influence >= 0){
				el.influence -= influence;
				return influence;
			}else{
				float rtn = el.influence;
				el.influence = 0;
				return rtn;
			}
		}
		return 0;
	}
	
	public float getRedvalue(){
		float value = 0;
		for(Element element: getAllElements()){
			value += element.getValue();
		}
		return value;
	}
	
	public boolean isEqual(Composition comp){
		for(Element element: this.getAllElements()){
			if(comp.elements.containsKey(element.name)){
				Element other = comp.getElement(element.name);
				if(element.influence != other.influence)return false;
			}else{
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty(){
		for(Element element: this.getAllElements()){
			if(element.influence > 0)return false;
		}
		return true;
	}
	
	public Composition merge(Composition comp){
		for(Element element: comp.getAllElements()){
			Element self = this.getElement(element.name);
			if(self != null){
				self.influence += element.influence;
				comp.decreaseValue(self.name, element.influence);
			}else{
				this.addElement(element);
				comp.decreaseValue(element.name, element.influence);
			}
		}
		return comp;
	}
	
	public Composition disjoin(Composition comp){
		for(Element element: comp.getAllElements()){
			Element self = this.getElement(element.name);
			if(self != null){
				if(self.influence - element.influence < 0){
					comp.decreaseValue(element.name, element.influence);
					self.influence = 0;
				}else{
					self.influence -= element.influence;
				}
			}
		}
		return comp;
	}
	
	public Composition divide(float value){
		if(value == 0)return this;
		for(Element element: getAllElements()){
			element.influence /= value;
		}
		return this;
	}
	
	public Composition multiply(float value){
		for(Element element: getAllElements()){
			element.influence *= value;
		}
		return this;
	}
	
	public static Composition divide(Composition comp, float value){
		Composition rtn = Composition.getStandard(0, 0, 0, 0, 0);
		if(value == 0)return comp;
		if(comp != null){
			for(Element element: comp.getAllElements()){
				rtn.addValue(element.name, element.influence / value);
			}
		}
		return rtn;
	}
	
	public Composition minus(Composition composition) {
		for(Element self: getAllElements()){
			Element other = composition.getElement(self.name);
			if(other != null){
				if(self.influence - other.influence < 0){
					composition.decreaseValue(self.name, other.influence);
					self.influence = 0;
				}else self.influence -= other.influence;
			}
		}
		return composition;
	}
	
	public boolean contains(Composition comp){
		for(Element element: comp.getAllElements()){
			Element self = this.getElement(element.name);
			if(self != null && self.influence < element.influence)return false;
			else if(self == null)return false;
		}
		return true;
	}
	
	public Composition copy(){
		Composition rtn = new Composition();
		for(Element element: this.getAllElements()){
			rtn.addElement(element.copy());
		}
		return rtn;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(Element element: getAllElements()){
			NBTTagCompound elementTag = new NBTTagCompound();
			element.writeToNBT(elementTag);
			list.appendTag(elementTag);
		}
		tag.setTag("elements", list);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList("elements");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound elementTag = (NBTTagCompound) list.tagAt(i);
			Element element = Element.loadFromNBT(elementTag);
			if(element != null)addElement(element);
		}
	}
	
	public static Composition loadFromNBT(NBTTagCompound tag){
		Composition comp = new Composition();
		if(tag != null)comp.readFromNBT(tag);
		return comp;
	}
	
	@Override
	public String toString(){
		String name = "Composition: ";
		for(Element element: getAllElements()){
			name += " | ";
			name += element.name + "=" + element.influence; 
		}
		return name;
	}
}
