package redmagic.api.bank;

import java.lang.reflect.Method;

import redmagic.core.Logger;


public class BankData {
	
	public static void register(int itemID, int itemDamage, int amount, boolean tradeable, float price, float tax, boolean buying){
		try{
			Class clas = Class.forName("redmagic.lib.bank.BankData");
			Method method = clas.getMethod("register", int.class, int.class, int.class, boolean.class, float.class, float.class, boolean.class);
			method.invoke(null, itemID, itemDamage, amount, tradeable, price, tax, buying);
		}catch(Throwable err){
			Logger.log("Couldn't register an ItemData");
		}
	}
	
}
