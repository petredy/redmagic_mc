package redmagic.api.essence;

import net.minecraftforge.common.ForgeDirection;
import redmagic.helpers.PipeHelper;

public interface IPipe extends IStorage{
	
	/**
	 * Searches for connected Pipes
	 * @return The connected pipes
	 */
	public abstract PipeHelper[] getConnectedPipes();
	
	/**
	 * Searches for connected IStorages, which are not IPipes
	 * @return
	 */
	public abstract IStorage[] getConnectedStorages();
	
	/**
	 * Returns whether the pipe is an extractor or not
	 * @return
	 */
	public abstract boolean isExtractor();
	
	/**
	 * Returns whether the pipe is a filler or not
	 * @return
	 */
	public abstract boolean isFiller();
	
	/**
	 * Switches the pipe mode
	 */
	public abstract void switchMode();
	
	/**
	 * Extracts essences from the pipe
	 * @param pipe
	 * @param amount
	 */
	public abstract int extractEssence(IStorage storage, int essencesPerConnection);
	
	/**
	 * Transports essences to the pipe
	 * @param pipe
	 * @param amount
	 */
	public abstract int transportEssence(PipeHelper pipe, int essencesPerConnection);
	
	/**
	 * Transports essences to an IStorage
	 * @param storage
	 * @param essencesPerConnection
	 * @return
	 */
	public abstract int fillEssence(IStorage storage, int essencesPerConnection);
	
	/**
	 * Return whether the side is connected to an IStorage or not
	 * @param side
	 * @return
	 */
	public abstract boolean isConnectedOnSide(ForgeDirection side);
}
