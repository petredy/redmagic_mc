package redmagic.lib.scrolls;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redmagic.blocks.BlockManager;
import redmagic.client.guis.GuiScroll;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerScroll;
import redmagic.helpers.RenderHelper;
import redmagic.items.ItemManager;
import redmagic.slots.SlotScroll;

public class ScrollCraftingWorkTable extends Scroll{

	@Override
	public void createContainer(ContainerScroll container) {
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 110, 40));
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 127, 40));
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 144, 40));
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 110, 57));
		container.addSlot(new SlotScroll(new ItemStack(Item.stick), 127, 57));
		container.addSlot(new SlotScroll(new ItemStack(Item.ingotIron), 144, 57));
		container.addSlot(new SlotScroll(null, 110, 74));
		container.addSlot(new SlotScroll(new ItemStack(Item.stick), 127, 74));
		container.addSlot(new SlotScroll(null, 144, 74));
		
		container.addSlot(new SlotScroll(new ItemStack(ItemManager.hammer), 190, 57));
	}

	@Override
	public String getHeadline() {
		return "Crafting in the Work Table";
	}

	@Override
	public String getIntroduction() {
		return "Craft the Hammer!";
	}

	@Override
	public String getDetails() {
		return "Choose a recipe in the Work Table, put the items in your inventory and right click with the Hammer on the Work Table until the circle gets white again. The items will be sucked out of your inventory.";
	}

	@Override
	public void drawOthers(GuiScroll gui, FontRenderer renderer, RenderEngine engine) {
		GL11.glColor3f(1F, 1F, 1F);
		engine.bindTexture(Texture.SCROLL_CRAFTING);
		gui.drawTexturedModalRect(110, 40, 0, 0, gui.xsize, gui.ysize);
	}

}
