package com.petredy.redmagic.redvalue.element;

import com.petredy.redmagic.lib.Elements;

public class ElementVoid extends Element{

	public ElementVoid(){
		name = Elements.VOID;
	}
	
	public ElementVoid(float influence){
		super(influence);
		name = Elements.VOID;
	}
	
}
