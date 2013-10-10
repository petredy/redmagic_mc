package com.petredy.redmagic.client.guis.knowledge;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.knowledge.KnowledgeItem;
import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.lib.KnowledgeIndex;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.network.PacketActivateArtifact;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraftforge.common.AchievementPage;

@SideOnly(Side.CLIENT)
public class GuiKnowledges extends GuiScreen
{
    /** The top x coordinate of the achievement map */
    private static final int guiMapTop = KnowledgeSystem.minDisplayColumn * 24 - 112;

    /** The left y coordinate of the achievement map */
    private static final int guiMapLeft = KnowledgeSystem.minDisplayRow * 24 - 112;

    /** The bottom x coordinate of the achievement map */
    private static final int guiMapBottom = KnowledgeSystem.maxDisplayColumn * 24 - 77;

    /** The right y coordinate of the achievement map */
    private static final int guiMapRight = KnowledgeSystem.maxDisplayRow * 24 - 77;
    private static final ResourceLocation achievementTextures = Textures.KNOWLEDGES;
    protected int achievementsPaneWidth = 256;
    protected int achievementsPaneHeight = 202;

    /** The current mouse x coordinate */
    protected int mouseX;

    /** The current mouse y coordinate */
    protected int mouseY;
    protected double field_74117_m;
    protected double field_74115_n;

    /** The x position of the achievement map */
    protected double guiMapX;

    /** The y position of the achievement map */
    protected double guiMapY;
    protected double field_74124_q;
    protected double field_74123_r;

    /** Whether the Mouse Button is down or not */
    private int isMouseButtonDown;

    private int currentPage = -1;
    private GuiSmallButton button;
    private LinkedList<Knowledge> knowledges = new LinkedList<Knowledge>();
    
    public TileEntityKnowledgeTransceiver entity;

    public GuiKnowledges(TileEntityKnowledgeTransceiver entity)
    {
    	this.entity = entity;
        short short1 = 141;
        short short2 = 141;
        this.field_74117_m = this.guiMapX = this.field_74124_q = (double)(KnowledgeSystem.getKnowledge(KnowledgeIndex.ORE).displayColumn * 24 - short1 / 2 - 12);
        this.field_74115_n = this.guiMapY = this.field_74123_r = (double)(KnowledgeSystem.getKnowledge(KnowledgeIndex.ORE).displayRow * 24 - short2 / 2);
        knowledges.clear();
        for (Knowledge k : KnowledgeSystem.getAll())
        {
        	knowledges.add(k);
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == this.mc.gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
        else
        {
            super.keyTyped(par1, par2);
        }
    }
    
    protected void mouseClicked(int par1, int par2, int par3)
    {
    	super.mouseClicked(par1, par2, par3);
		int left = (this.width - this.achievementsPaneWidth) / 2 + 15;
        int top = (this.height - this.achievementsPaneHeight) / 2 + 17;

        double localMouseX = (par1 - left) + guiMapX;
        double localMouseY = (par2 - top) + guiMapY;
        
		Iterator<Knowledge> it = knowledges.iterator();
		while(it.hasNext()){
			Knowledge knowledge = it.next();
			int col = knowledge.displayColumn;
			int row = knowledge.displayRow;
			int x = knowledge.displayColumn * 24;
			int y = knowledge.displayRow * 24;
			if(localMouseX > x && localMouseX < x + 24 && localMouseY > y && localMouseY < y + 24){
				if(par3 == 0){
					try {
						GuiKnowledgeDescription gui;
						gui = (GuiKnowledgeDescription) knowledge.gui.newInstance();
						gui.knowledge = knowledge;
						this.mc.displayGuiScreen(gui);
					} catch (InstantiationException e) {
						if(Redmagic.DEBUG)e.printStackTrace();
					} catch (IllegalAccessException e) {
						if(Redmagic.DEBUG)e.printStackTrace();
					}
				}else{
					PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketActivateArtifact(entity.xCoord, entity.yCoord, entity.zCoord, knowledge.name)));
				}
			}
		}
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        if (Mouse.isButtonDown(0))
        {
            int k = (this.width - this.achievementsPaneWidth) / 2;
            int l = (this.height - this.achievementsPaneHeight) / 2;
            int i1 = k + 8;
            int j1 = l + 17;

            if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && par1 >= i1 && par1 < i1 + 224 && par2 >= j1 && par2 < j1 + 155)
            {
                if (this.isMouseButtonDown == 0)
                {
                    this.isMouseButtonDown = 1;
                }
                else
                {
                    this.guiMapX -= (double)(par1 - this.mouseX);
                    this.guiMapY -= (double)(par2 - this.mouseY);
                    this.field_74124_q = this.field_74117_m = this.guiMapX;
                    this.field_74123_r = this.field_74115_n = this.guiMapY;
                }

                this.mouseX = par1;
                this.mouseY = par2;
            }

            if (this.field_74124_q < (double)guiMapTop)
            {
                this.field_74124_q = (double)guiMapTop;
            }

            if (this.field_74123_r < (double)guiMapLeft)
            {
                this.field_74123_r = (double)guiMapLeft;
            }

            if (this.field_74124_q >= (double)guiMapBottom)
            {
                this.field_74124_q = (double)(guiMapBottom - 1);
            }

            if (this.field_74123_r >= (double)guiMapRight)
            {
                this.field_74123_r = (double)(guiMapRight - 1);
            }
        }
        else
        {
            this.isMouseButtonDown = 0;
        }

        this.drawDefaultBackground();
        this.genKnowledgeBackground(par1, par2, par3);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_74117_m = this.guiMapX;
        this.field_74115_n = this.guiMapY;
        double d0 = this.field_74124_q - this.guiMapX;
        double d1 = this.field_74123_r - this.guiMapY;

        if (d0 * d0 + d1 * d1 < 4.0D)
        {
            this.guiMapX += d0;
            this.guiMapY += d1;
        }
        else
        {
            this.guiMapX += d0 * 0.85D;
            this.guiMapY += d1 * 0.85D;
        }
    }

    protected void genKnowledgeBackground(int par1, int par2, float par3)
    {
        int k = MathHelper.floor_double(this.field_74117_m + (this.guiMapX - this.field_74117_m) * (double)par3);
        int l = MathHelper.floor_double(this.field_74115_n + (this.guiMapY - this.field_74115_n) * (double)par3);

        if (k < guiMapTop)
        {
            k = guiMapTop;
        }

        if (l < guiMapLeft)
        {
            l = guiMapLeft;
        }

        if (k >= guiMapBottom)
        {
            k = guiMapBottom - 1;
        }

        if (l >= guiMapRight)
        {
            l = guiMapRight - 1;
        }

        int i1 = (this.width - this.achievementsPaneWidth) / 2;
        int j1 = (this.height - this.achievementsPaneHeight) / 2;
        int k1 = i1 + 16;
        int l1 = j1 + 17;
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, -200.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int i2 = k + 288 >> 4;
        int j2 = l + 288 >> 4;
        int k2 = (k + 288) % 16;
        int l2 = (l + 288) % 16;
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        Random random = new Random();
        int i3;
        int j3;
        int k3;

        for (i3 = 0; i3 * 16 - l2 < 155; ++i3)
        {
            float f1 = 0.6F - (float)(j2 + i3) / 25.0F * 0.3F;
            GL11.glColor4f(f1, f1, f1, 1.0F);

            for (k3 = 0; k3 * 16 - k2 < 224; ++k3)
            {
                random.setSeed((long)(1234 + i2 + k3));
                random.nextInt();
                j3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
                Icon icon = Block.sand.getIcon(0, 0);

                if (j3 <= 37 && j2 + i3 != 35)
                {
                    if (j3 == 22)
                    {
                        if (random.nextInt(2) == 0)
                        {
                            icon = Block.oreDiamond.getIcon(0, 0);
                        }
                        else
                        {
                            icon = Block.oreRedstone.getIcon(0, 0);
                        }
                    }
                    else if (j3 == 10)
                    {
                        icon = Block.oreIron.getIcon(0, 0);
                    }
                    else if (j3 == 8)
                    {
                        icon = Block.oreCoal.getIcon(0, 0);
                    }
                    else if (j3 > 4)
                    {
                        icon = Block.stone.getIcon(0, 0);
                    }
                    else if (j3 > 0)
                    {
                        icon = Block.dirt.getIcon(0, 0);
                    }
                }
                else
                {
                    icon = Block.bedrock.getIcon(0, 0);
                }

                this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
                this.drawTexturedModelRectFromIcon(k1 + k3 * 16 - k2, l1 + i3 * 16 - l2, icon, 16, 16);
            }
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        int l3;
        int i4;
        int j4;

        for (i3 = 0; i3 < knowledges.size(); ++i3)
        {
            Knowledge knowledge = KnowledgeSystem.getKnowledge(knowledges.get(i3).name);

            if (knowledge.parent != null)
            {
                k3 = knowledge.displayColumn * 24 - k + 11 + k1;
                j3 = knowledge.displayRow * 24 - l + 11 + l1;
                j4 = knowledge.parent.displayColumn * 24 - k + 11 + k1;
                l3 = knowledge.parent.displayRow * 24 - l + 11 + l1;
                boolean flag5 = KnowledgeSystem.hasKnowledgeUnlocked(knowledge);
                boolean flag6 = KnowledgeSystem.canUnlockKnowledge(knowledge);
                i4 = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D ? 255 : 130;
                int k4 = -16777216;

                if (flag5)
                {
                    k4 = -9408400;
                }
                else if (flag6)
                {
                    k4 = 65280 + (i4 << 24);
                }

                this.drawHorizontalLine(k3, j4, j3, k4);
                this.drawVerticalLine(j4, j3, l3, k4);
            }
        }

        Knowledge knowledge1 = null;
        RenderItem renderitem = new RenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int l4;
        int i5;

        for (Knowledge knowledge2: KnowledgeSystem.getAll())
        {
            j4 = knowledge2.displayColumn * 24 - k;
            l3 = knowledge2.displayRow * 24 - l;

            if (j4 >= -24 && l3 >= -24 && j4 <= 224 && l3 <= 155)
            {
                float f2;

                if (KnowledgeSystem.hasKnowledgeUnlocked(knowledge2))
                {
                    f2 = 1.0F;
                    GL11.glColor4f(f2, f2, f2, 1.0F);
                }
                else if (KnowledgeSystem.canUnlockKnowledge(knowledge2))
                {
                    f2 = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) < 0.6D ? 0.6F : 0.8F;
                    GL11.glColor4f(f2, f2, f2, 1.0F);
                }
                else
                {
                    f2 = 0.3F;
                    GL11.glColor4f(f2, f2, f2, 1.0F);
                }

                this.mc.getTextureManager().bindTexture(achievementTextures);
                i5 = k1 + j4;
                l4 = l1 + l3;

                if (KnowledgeSystem.isActive(knowledge2, entity))
                {
                    this.drawTexturedModalRect(i5 - 2, l4 - 2, 26, 202, 26, 26);
                }
                else
                {
                    this.drawTexturedModalRect(i5 - 2, l4 - 2, 0, 202, 26, 26);
                }

                if (!KnowledgeSystem.canUnlockKnowledge(knowledge2))
                {
                    float f3 = 0.1F;
                    GL11.glColor4f(f3, f3, f3, 1.0F);
                    renderitem.renderWithColor = false;
                }

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
                if(knowledge2 instanceof KnowledgeItem){
                	renderitem.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), ((KnowledgeItem)knowledge2).item, i5 + 3, l4 + 3);
                }else{
                	Redmagic.proxy.bindTexture(knowledge2.texture);
                }
                this.drawTexturedModalRect(i5 + 3, l4 + 3, 0, 0, 16, 16);
                GL11.glDisable(GL11.GL_LIGHTING);

                if (!KnowledgeSystem.canUnlockKnowledge(knowledge2))
                {
                    renderitem.renderWithColor = true;
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                if (par1 >= k1 && par2 >= l1 && par1 < k1 + 224 && par2 < l1 + 155 && par1 >= i5 && par1 <= i5 + 22 && par2 >= l4 && par2 <= l4 + 22)
                {
                    knowledge1 = knowledge2;
                }
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(achievementTextures);
        this.drawTexturedModalRect(i1, j1, 0, 0, this.achievementsPaneWidth, this.achievementsPaneHeight);
        GL11.glPopMatrix();
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        super.drawScreen(par1, par2, par3);

        if (knowledge1 != null)
        {
            String s = I18n.getString(knowledge1.getName());
            String s1 = knowledge1.getDescription();
            j4 = par1 + 12;
            l3 = par2 - 4;

            if (KnowledgeSystem.canUnlockKnowledge(knowledge1))
            {
                i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
                l4 = this.fontRenderer.splitStringWidth(s1, i5);

                if (KnowledgeSystem.hasKnowledgeUnlocked(knowledge1))
                {
                    l4 += 12;
                }

                this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + l4 + 3 + 12, -1073741824, -1073741824);
                this.fontRenderer.drawSplitString(s1, j4, l3 + 12, i5, -6250336);

                if (KnowledgeSystem.hasKnowledgeUnlocked(knowledge1))
                {
                    this.fontRenderer.drawStringWithShadow(I18n.getString("achievement.taken"), j4, l3 + l4 + 4, -7302913);
                }
            }
            else
            {
                i5 = Math.max(this.fontRenderer.getStringWidth(s), 120);
                String s2 = I18n.getStringParams("achievement.requires", new Object[] {I18n.getString(knowledge1.parent.getName())});
                i4 = this.fontRenderer.splitStringWidth(s2, i5);
                this.drawGradientRect(j4 - 3, l3 - 3, j4 + i5 + 3, l3 + i4 + 12 + 3, -1073741824, -1073741824);
                this.fontRenderer.drawSplitString(s2, j4, l3 + 12, i5, -9416624);
            }

            this.fontRenderer.drawStringWithShadow(s, j4, l3, KnowledgeSystem.canUnlockKnowledge(knowledge1) ? (KnowledgeSystem.isActive(knowledge1, entity) ? -128 : -1) : (KnowledgeSystem.isActive(knowledge1, entity) ? -8355776 : -8355712));
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        RenderHelper.disableStandardItemLighting();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
