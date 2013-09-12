package com.petredy.redmagic.redvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RedvalueDictionary {

	private static HashMap<String, BasicRedvalueItem> recipes = new HashMap<String, BasicRedvalueItem>();
	private static HashMap<String, NativeRedvalueItem> values = new HashMap<String, NativeRedvalueItem>();
	
	public static void registerNative(ItemStack stack, int value){
		values.put(stack.getUnlocalizedName(), new NativeRedvalueItem(stack, value));
		LogUtils.log("Register " + stack.getUnlocalizedName() + " for a Redvalue of " + value);
	}
	
	public static void register(ItemStack stack, Collection<RedvalueContent> collection){
		recipes.put(stack.getUnlocalizedName(), new BasicRedvalueItem(stack, collection));
		LogUtils.log("RegisterBasic " + stack.getUnlocalizedName() + " with the content " + collection);
	}
	
	public static float getNativeRedvalue(ItemStack item){
		NativeRedvalueItem natItem = values.get(item.getUnlocalizedName());
		if(natItem != null)return natItem.getValue();
		return 0;
	}
	
	public static float getBasicRedvalue(ItemStack item){
		BasicRedvalueItem redItem = recipes.get(item.getUnlocalizedName());
		if(redItem != null)return redItem.getValue();
		return 0;
	}
	
	public static float getRedvalue(ItemStack item){
		float cost = getNativeRedvalue(item);
		if(cost == 0){
			return getBasicRedvalue(item);
		}else{
			return cost;
		}
	}
	
	public static List<RedvalueItem> getAllItems(){
		List<RedvalueItem> list = new ArrayList<RedvalueItem>();
		list.addAll(recipes.values());
		list.addAll(values.values());
		return list;
	}
	
	public static void initialise(){
		List recipes = CraftingManager.getInstance().getRecipeList();
		Map<List<Integer>, ItemStack> burningRecipes = FurnaceRecipes.smelting().getMetaSmeltingList();
		Map vanillaBurningRecipes = FurnaceRecipes.smelting().getSmeltingList();
		int count = 0;
		int runs = 0;
		do{
			count = calculate(recipes, burningRecipes, vanillaBurningRecipes);
			runs++;
		}while(count > 0);
		LogUtils.log("Registered all Redvalues in " + runs + " runs");
		
	}
	
	private static int calculate(List recipes, Map burningRecipes, Map vanillaBurningRecipes) {
		int count = calculateRecipes(recipes);
		count += calculateBurningRecipes(burningRecipes);
		count += calculateVanillaBurningRecipes(vanillaBurningRecipes);
		return count;
	}

	private static int calculateVanillaBurningRecipes(Map vanillaBurningRecipes) {
		int count = 0;
		Iterator entries = vanillaBurningRecipes.entrySet().iterator();
		while(entries.hasNext()){
			Entry entry = (Entry)entries.next();
			int id = (Integer) entry.getKey();
			ItemStack output = (ItemStack) entry.getValue();
			ItemStack input = new ItemStack(id, 1, 0);
			List<ItemStack> fakeList = new ArrayList<ItemStack>();
			fakeList.add(input);
			if(getNativeRedvalue(output) == 0 && getBasicRedvalue(output) == 0 && initialiseRecipe(fakeList, output)){
				vanillaBurningRecipes.remove(output);
				count++;
			}
		}
		return count;
	}

	private static int calculateBurningRecipes(Map burningRecipes) {
		int count = 0;
		Iterator entries = burningRecipes.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			List<Integer> key = (List<Integer>) thisEntry.getKey();
			ItemStack value = (ItemStack) thisEntry.getValue();
			ItemStack input = new ItemStack(key.get(0), 1, key.get(1));
			List<ItemStack> fakeList = new ArrayList<ItemStack>();
			fakeList.add(input);
			if(getNativeRedvalue(value) == 0 && getBasicRedvalue(value) == 0 && initialiseRecipe(fakeList, value)){
				burningRecipes.remove(value);
				count++;
			}
			  
		}
		
		return count;
	}

	private static int calculateRecipes(List recipes) {
		Iterator it = recipes.iterator();
		int count = 0;
		while(it.hasNext()){
			Object obj = it.next();
			ItemStack item = null;
			List items = new ArrayList();
			if(obj instanceof ShapedRecipes){
				items.add(((ShapedRecipes)obj).recipeItems);
				item = ((ShapedRecipes)obj).getRecipeOutput();
			}else if(obj instanceof ShapelessRecipes){
				items = ((ShapelessRecipes)obj).recipeItems;
				item = ((ShapelessRecipes)obj).getRecipeOutput();
			}else if(obj instanceof ShapedOreRecipe){
				items.add(((ShapedOreRecipe)obj).getInput());
				item = ((ShapedOreRecipe)obj).getRecipeOutput();
			}else if(obj instanceof ShapelessOreRecipe){
				items.add(((ShapelessOreRecipe)obj).getInput());
				item = ((ShapelessOreRecipe)obj).getRecipeOutput();
			}else{
				LogUtils.log("Can't use " + obj.getClass().getSimpleName() + " to create a RedvalueItem");
				continue;
			}
			if(getNativeRedvalue(item) == 0 && getBasicRedvalue(item) == 0 && initialiseRecipe(items, item)){
				obj = null;
				
				count++;
			}
		}
		return count;
	}

	private static boolean initialiseRecipe(List items, ItemStack stack){
		Iterator it = items.iterator();
		List<RedvalueContent> content = new ArrayList<RedvalueContent>();
		while(it.hasNext()){
			Object obj = it.next();
			if(obj instanceof ItemStack){
				content = handleItemStack((ItemStack)obj, content, stack);
			}else if(obj instanceof ItemStack[]){
				content = handleItemStackArray((ItemStack[])obj, content, stack);
			}else if(obj instanceof Object[]){
				content = handleObjectArray((Object[])obj, content, stack);
			}else if(obj instanceof Object){
				content = handleObject(obj, content, stack);
			}
		}
		if(content != null && !content.isEmpty()){
			register(stack, RedvalueContent.sort(content));
			return true;
		}
		return false;
	}
	
	
	private static List<RedvalueContent> handleObject(Object obj, List<RedvalueContent> content, ItemStack stack) {
		//TODO
		return content;
	}

	private static List<RedvalueContent> handleObjectArray(Object[] obj, List<RedvalueContent> content, ItemStack stack) {
		for(Object ob: obj){
			if(ob instanceof ItemStack){
				content = handleItemStack((ItemStack)ob, content, stack);
			}else if(ob instanceof ArrayList){
				for(Object o: ((ArrayList)ob)){
					if(o instanceof ItemStack){
						content = handleItemStack((ItemStack)o, content, stack);
						if(content == null)return null;
					}
				}
			}
		}
		return content;
	}

	private static List<RedvalueContent> handleItemStackArray(ItemStack[] obj, List<RedvalueContent> content, ItemStack stack) {
		for(ItemStack item: obj){
			content = handleItemStack(item, content, stack);
			if(content == null)return null;
		}
		return content;
	}

	private static List<RedvalueContent> handleItemStack(ItemStack item, List<RedvalueContent> content, ItemStack stack){
		if(item != null && stack != null && content != null){
			if(getNativeRedvalue(item) == 0){
				if(getBasicRedvalue(item) > 0){
					List<RedvalueContent> values = recipes.get(item.getUnlocalizedName()).getContent();
					for(RedvalueContent value: values){
						value.amount /= stack.stackSize;
						content.add(value);
					}
				}else {
					return null;
				}
			}else{
				content.add(new RedvalueContent(item, 1.0F / (float)stack.stackSize));
				return content;
			}
		}
		return content;
	}
	
	
	
}
