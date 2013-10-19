package com.petredy.redmagic.redvalue.element;

import com.petredy.redmagic.lib.Elements;

public class ElementMetal extends Element{

	public ElementMetal(){
		name = Elements.METAL;
	}
	
	public ElementMetal(float influence){
		super(influence);
		name = Elements.METAL;
	}
	
}
