package redmagic.lib.scrolls;

import redmagic.configuration.ScrollIndex;

public class ScrollHelper {

	public static Scroll getScroll(int id){
		switch(id){
		case ScrollIndex.START_ID:
			return new ScrollStart();
		case ScrollIndex.WORK_TABLE_ID:
			return new ScrollCraftingWorkTable();
		case ScrollIndex.ORE_ID:
			return new ScrollSoulCrystal();
		}
		return new Scroll();
	}
	
}
