package redmagic.api.talent;

import net.minecraft.entity.player.EntityPlayer;

public interface IUnlockable {
	public void unlock(EntityPlayer player);
	
	public String getHelp();
}
