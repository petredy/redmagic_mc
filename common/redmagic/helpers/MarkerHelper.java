package redmagic.helpers;

import redmagic.configuration.BlockIndex;
import redmagic.lib.rune.Marker;

public class MarkerHelper {

	public static int getHitIndex(float x, float z){
		int count = 0;
		for(int j = 1; j <= 4; j++){
			for(int i = 1; i <= 4; i++){
				if(x < i * 0.25f && x > i * 0.25f - 0.25f && z > j * 0.25f - 0.25f && z < j * 0.25f){
					return count; 
				}
				count++;
			}
		}
		return -1;
	}
}
