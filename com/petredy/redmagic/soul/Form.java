package com.petredy.redmagic.soul;

import java.util.List;

import com.petredy.redmagic.lib.Forms;

public class Form {
	
	public String name;
	public String localizeName;
	public int ratio;
	
	public Form(String name, int ratio){
		this.name = name;
		this.localizeName = Forms.FORMS_PREFIX + name + ".name";
		this.ratio = ratio;
	}

	
	public void addInformation(List<String> list){
		
	}
	
}
