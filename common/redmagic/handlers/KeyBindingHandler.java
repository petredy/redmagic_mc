package redmagic.handlers;

import java.util.EnumSet;

import redmagic.Redmagic;
import redmagic.api.items.IKeyBound;
import redmagic.configuration.Comments;
import redmagic.configuration.Reference;
import redmagic.helpers.KeyBindingHelper;
import redmagic.network.PacketHandler;
import redmagic.network.PacketKeyPressed;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler{
	
	
	public static void config(Configuration config){
		Property key = config.get("general", Reference.KEY_EXTRA_NAME, Reference.KEY_EXTRA_DEFAULT);
		key.comment = Comments.KEY_EXTRA;
		Reference.KEY_EXTRA_ID = key.getInt(Reference.KEY_EXTRA_DEFAULT);
		
		Redmagic.proxy.setKeyBinding(Reference.KEY_EXTRA_NAME, Reference.KEY_EXTRA_ID);
	}
	
	public KeyBindingHandler() {

        super(KeyBindingHelper.gatherKeyBindings(), KeyBindingHelper.gatherIsRepeating());
    }

	@Override
	public String getLabel() {
		return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		// Only operate at the end of the tick
        if (tickEnd) {
            // If we are not in a GUI of any kind, continue execution
            if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
                if (player != null) {
                    ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();

                    if (currentItem != null) {
                        if (currentItem.getItem() instanceof IKeyBound) {
                            PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
                        }
                    }
                    for(int i = 0; i < player.inventory.armorInventory.length; i++){
        				if(player.inventory.armorInventory[i] != null && player.inventory.armorInventory[i].getItem() instanceof IKeyBound){
        					PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
        					break;
        				}
        			}
                }
            }
        }
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}
