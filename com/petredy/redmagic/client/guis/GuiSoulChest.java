package com.petredy.redmagic.client.guis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerSoulChest;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.soul.SoulStack;
import com.petredy.redmagic.tileentities.TileEntitySoulChest;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiSoulChest extends GuiContainer{
	private static int field_94069_F = 0;
	private static final long returningStackTime = 0;
	private static final Slot returningStackDestSlot = null;
	private static final int field_85049_r = 0;
	private static final int field_85048_s = 0;
	private static final Slot clickedSlot = null;
	private static final int field_94071_C = 0;
	
	public TileEntitySoulChest entity;
	public ContainerSoulChest container;
	
	private float currentScroll = 0.0F;
	public EntityPlayer player;
	
	public GuiTextField searchField;
	
	private boolean wasClicking;
	private boolean isScrolling;
	
	private Slot theSlot;
	private ItemStack returningStack;
	
	public GuiSoulChest(EntityPlayer player, TileEntitySoulChest tileEntity) {
		super(new ContainerSoulChest(player,tileEntity));
		this.entity = tileEntity;
		this.player = player;
		this.allowUserInput = true;
	}
	
	public void initGui()
    {
		this.xSize = 194;
		this.ySize = 192;
		super.initGui();
		this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.searchField = new GuiTextField(this.fontRenderer, this.guiLeft + 81, this.guiTop + 5, 89, this.fontRenderer.FONT_HEIGHT);
        this.searchField.setMaxStringLength(15);
        this.searchField.setEnableBackgroundDrawing(false);
        this.searchField.setVisible(true);
        this.searchField.setTextColor(16777215);
        this.searchField.setVisible(true);
        this.searchField.setCanLoseFocus(false);
        this.searchField.setFocused(true);
        this.searchField.setText("");
    }
	
	public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
	
	protected void actionPerformed(GuiButton button) {
		
		
	}
	
	 public void handleMouseInput()
    {
		super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        
        if (i != 0)
        {
            int j = (entity.soul != null ? entity.soul.inventory.getSizeInventory() : 0) / 9 - 5 + 1;

            if (i > 0)
            {
                i = 1;
            }

            if (i < 0)
            {
                i = -1;
            }

            this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((ContainerSoulChest)this.inventorySlots).scrollTo(this.currentScroll);
        }
        
    }

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){		
		fontRenderer.drawString(StatCollector.translateToLocal(Blocks.soulChest.getUnlocalizedName()), 8, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Textures.TRADING_CHEST_GUI);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		this.searchField.drawTextBox();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int i1 = this.guiLeft + 175;
        int k = this.guiTop + 18;
        int l = k + 112;
		Redmagic.proxy.bindTexture(Textures.ALL_ITEMS);
        this.drawTexturedModalRect(i1, k + (int)((float)(l - k - 17) * this.currentScroll), 232 + 12, 0, 12, 15);
	}
	
	protected void keyTyped(char par1, int par2)
    {
		if (!this.checkHotbarKeys(par2))
        {
            if (this.searchField.textboxKeyTyped(par1, par2))
            {
                this.updateSearch();
            }
            else
            {
                super.keyTyped(par1, par2);
            }
        }   
    }

	private void updateSearch() {
		ContainerSoulChest chest = (ContainerSoulChest)this.inventorySlots;
		Object[] theList;
		if(chest.list.length > 0){
			theList= chest.list;
		}else{
			theList = entity.soul != null ? entity.soul.inventory.stacks.values().toArray() : new Object[0];
		}
		List<ItemStack> newList = new ArrayList<ItemStack>();
		String search = this.searchField.getText().toLowerCase();
		if(!search.equals("")){
			for(Object item: theList){
				ItemStack stack = null;
				if(item instanceof SoulStack){
					SoulStack soulItem = (SoulStack)item;
					stack = (ItemStack)soulItem.stack;
				}else{
					stack = (ItemStack)item;
				}
				String name = StatCollector.translateToLocal(stack.getDisplayName().toLowerCase());
				if(name.matches(".*"+search+".*")){
					newList.add(stack);
				}
			}
			LogUtils.log(newList);
			((ContainerSoulChest)this.inventorySlots).list = newList.toArray();
			((ContainerSoulChest)this.inventorySlots).scrollTo(0.0F);
		}else{
			((ContainerSoulChest)this.inventorySlots).list = entity.soul != null ? entity.soul.inventory.stacks.values().toArray() : new Object[0];
		}
	}
	
	public void drawScreen(int par1, int par2, float par3)
    {
		
		updateSearch();
        boolean flag = Mouse.isButtonDown(0);
        int k = this.guiLeft;
        int l = this.guiTop;
        int i1 = k + 175;
        int j1 = l + 18;
        int k1 = i1 + 14;
        int l1 = j1 + 112;

        if (!this.wasClicking && flag && par1 >= i1 && par2 >= j1 && par1 < k1 && par2 < l1)
        {
            this.isScrolling = true;
        }

        if (!flag)
        {
            this.isScrolling = false;
        }

        this.wasClicking = flag;

        if ((boolean) this.isScrolling)
        {
            this.currentScroll = ((float)(par2 - j1) - 7.5F) / ((float)(l1 - j1) - 15.0F);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((ContainerSoulChest)this.inventorySlots).scrollTo(this.currentScroll);
        }else{
        	((ContainerSoulChest)this.inventorySlots).scrollTo(this.currentScroll);
        }
        
        super.drawScreen(par1, par2, par3);
    }
	
	protected void drawSlotInventory(Slot par1Slot)
    {
		if(par1Slot != null && par1Slot.getStack() != null && par1Slot.getStack().getItem() != null && par1Slot.slotNumber <= 45 && par1Slot.slotNumber > 0){
	        int i = par1Slot.xDisplayPosition;
	        int j = par1Slot.yDisplayPosition;
	        ItemStack itemstack = par1Slot.getStack();
	        
	        ItemStack stack = par1Slot.getStack();
	        boolean flag = false;
	        boolean flag1 = false;
	        ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
	        String s = null;
	
	        this.zLevel = 100.0F;
	        itemRenderer.zLevel = 100.0F;
	
	        if (itemstack == null)
	        {
	            Icon icon = par1Slot.getBackgroundIconIndex();
	
	            if (icon != null)
	            {
	                GL11.glDisable(GL11.GL_LIGHTING);
	                Redmagic.proxy.bindTexture(new ResourceLocation("/gui/items.png"));
	                this.drawTexturedModelRectFromIcon(i, j, icon, 16, 16);
	                GL11.glEnable(GL11.GL_LIGHTING);
	                flag1 = true;
	            }
	        }
	
	        if (!flag1)
	        {
	            if (flag)
	            {
	                drawRect(i, j, i + 16, j + 16, -2130706433);
	            }
	
	            GL11.glEnable(GL11.GL_DEPTH_TEST);
	            if(itemstack != null){
		            itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, itemstack, i, j);
		            if(stack != null){
		            	int amount = stack.stackSize;
		            	s = amount < 1000 ? amount == 1 ? "" : String.valueOf(amount) : String.valueOf(amount / 1000) + "k";
		            }
		            itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, itemstack, i, j, s);
	            }
	        }
	
	        itemRenderer.zLevel = 0.0F;
	        this.zLevel = 0.0F;
		}else{
			super.drawSlotInventory(par1Slot);
		}
    }
}

