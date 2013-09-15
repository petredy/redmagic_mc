package com.petredy.redmagic.rune;

import java.util.Collection;
import java.util.HashMap;

public class Pattern {

	protected static HashMap<String, Pattern>patterns = new HashMap<String, Pattern>();
	
	public static void register(String name, Pattern pattern){
		pattern.name = name;
		patterns.put(name, pattern);
	}
	
	public static Pattern getPattern(String name){
		return patterns.get(name);
	}
	
	public static Collection<Pattern> getAll(){
		return patterns.values();
	}
	
	public Marker[] markers = new Marker[16];
	
	public int[] directionalMarkerIndexNorth = new int[]{
		0, 1, 2, 3, 
		4, 5, 6, 7,
		8, 9, 10, 11,
		12, 13, 14, 15
	};
	
	public int[] directionalMarkerIndexSouth = new int[]{
		12, 13, 14, 15,
		8, 9, 10, 11,
		4, 5, 6, 7,
		0, 1, 2, 3
	};
	
	public int[] directionalMarkerIndexEast = new int[]{
		0, 4, 8, 12,
		1, 5, 9, 13,
		2, 6, 10, 14,
		3, 7, 11, 15
	};
	
	public int[] directionalMarkerIndexWest = new int[]{
		3, 7, 11, 15, 
		2, 6, 10, 14,
		1, 5, 9, 13,
		0, 4, 8, 12
	};

	public boolean ignoreColors;
	public String name;
	
	public Pattern(Marker[] markers, boolean ignoreColors){
		this.markers = markers;
		this.ignoreColors = ignoreColors;
	}
	
	public static Pattern create(int[] ids, boolean colors){
		Marker[] markers = new Marker[ids.length];
		for(int i = 0; i < ids.length; i++){
			markers[i] = ids[i] > -1 ? new Marker(ids[i]) : null;
		}
		return new Pattern(markers, colors);
	}
	
	public boolean matches(int side, Marker[] markers){
		int[] range = null;
		if(side == 2)range = directionalMarkerIndexNorth;
		if(side == 5)range = directionalMarkerIndexEast;
		if(side == 3)range = directionalMarkerIndexSouth;
		if(side == 2)range = directionalMarkerIndexWest;
		int count = 0;
		for(int index: range){
			if((markers[index] == null && this.markers[count] != null) || (markers[index] != null && this.markers[count] == null) || (markers[index] != null && !markers[index].equals(this.markers[count])))return false;
			count++;
		}
		return true;
	}
	
	
}
