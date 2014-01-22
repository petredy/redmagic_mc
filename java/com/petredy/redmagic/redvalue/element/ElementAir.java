package com.petredy.redmagic.redvalue.element;

import com.petredy.redmagic.lib.Elements;

public class ElementAir extends Element {

	public ElementAir(){
		name = Elements.AIR;
	}
	
	public ElementAir(float influence) {
		super(influence);
		name = Elements.AIR;
	}

}
