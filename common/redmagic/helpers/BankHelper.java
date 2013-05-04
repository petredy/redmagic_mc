package redmagic.helpers;


import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BankHelper {
	public static float getMoney(ItemStack stack){
		if(stack.stackTagCompound == null){
			stack.stackTagCompound = new NBTTagCompound();
		}
		return stack.stackTagCompound.getFloat("redmagic_money");
	}
	
	public static void setMoney(ItemStack stack, float amount){
		if(stack.stackTagCompound == null){
			stack.stackTagCompound = new NBTTagCompound();
		}
		stack.stackTagCompound.setFloat("redmagic_money", amount);
	}
}
