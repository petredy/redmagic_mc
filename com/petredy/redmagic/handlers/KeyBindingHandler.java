package com.petredy.redmagic.handlers;

import java.util.EnumSet;

import com.petredy.redmagic.api.IKeyBound;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketKeyPressed;
import com.petredy.redmagic.utils.KeyBindingUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler{
	
	public KeyBindingHandler() {

        super(KeyBindingUtils.gatherKeyBindings(), KeyBindingUtils.gatherIsRepeating());
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
