package com.petredy.redmagic.redvalue.element;

import java.util.HashMap;

import com.petredy.redmagic.lib.Elements;
import com.petredy.redmagic.utils.LogUtils;

public class ElementDictionary {

	public static HashMap<String, Class> elements = new HashMap<String, Class>();
	
	public static void addElement(String name, Class cl){
		elements.put(name, cl);
	}
	
	public static Element getElement(String name){
		try{
			Class cl = elements.get(name);
			return (Element) cl.newInstance();
		}catch(Exception e){
			LogUtils.log("Couldn't create instance of class " +  elements.get(name) != null ? elements.get(name) : null);
		}
		return null;
	}
	
	static{
		addElement(Elements.EARTH, ElementEarth.class);
		addElement(Elements.AIR, ElementAir.class);
		addElement(Elements.WATER, ElementWater.class);
		addElement(Elements.FIRE, ElementFire.class);
		addElement(Elements.VOID, ElementVoid.class);
	}
	
}
