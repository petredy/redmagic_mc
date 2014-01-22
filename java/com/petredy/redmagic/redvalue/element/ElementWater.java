package com.petredy.redmagic.redvalue.element;

import com.petredy.redmagic.lib.Elements;

public class ElementWater extends Element {

	public ElementWater(){
		name = Elements.WATER;
	}
	
	public ElementWater(float influence) {
		super(influence);
		name = Elements.WATER;
	}

}
