package redmagic.api.essence;

import net.minecraftforge.liquids.LiquidStack;

public interface IStorage {

	/**
	 * Stores an amount essences in the storage
	 * @param amount
	 * @param data
	 * @return returns the essence amount, which could be stored
	 */
	public abstract int store(LiquidStack resource, Object...data);
	
	/**
	 * Loads an amount of essence from the storage
	 * @param amount
	 * @param data
	 * @return the amount or zero
	 */
	public abstract LiquidStack extract(int amount, Object...data);
	
	/**
	 * Returns the stored essences.
	 * @param data
	 * @return
	 */
	public abstract int getEssences(Object...data);
	
	/**
	 * Returns the maximum of essences the storage can hold.
	 * @return
	 */
	public abstract int getMaxEssences();
}
