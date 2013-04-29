package redmagic.taming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import redmagic.tileentities.TileEntityTaming;

import net.minecraft.item.ItemStack;

public class TamingRegistry {

	public static List<TamingProcess> processes = new ArrayList<TamingProcess>();
	
	public static void registerProcess(ItemStack output, ItemStack[] itemStacks, int need){
		processes.add(new TamingProcess(output, itemStacks, need));
	}

	public static int getProcessAmount(){
		return processes.size();
	}

	public static TamingProcess getProcess(int index) {
		return processes.get(index);
	}


	public static ItemStack getOutput(TileEntityTaming tileEntity) {
		int[] percents = new int[processes.size()];
		int count = 0;
		Iterator<TamingProcess> it = processes.iterator();
		while(it.hasNext()){
			TamingProcess process = it.next();
			percents[count] = process.match(tileEntity.items);
			count++;
		}
		TamingProcess bestProcess = TamingRegistry.getProcess(bestPercent(percents));
		return bestProcess.output.copy();
	}


	private static int bestPercent(int[] percents) {
		int best = 0;
		for(int i = 0; i < percents.length; i++){
			if(percents[i] > percents[best])best = i;
		}
		return best;
	}
	
}
