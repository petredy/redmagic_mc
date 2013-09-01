package redmagic.client.guis.talent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import redmagic.lib.talent.Talent;

public class GuiTalent extends Gui{
	private static final ResourceLocation field_110331_a = new ResourceLocation("textures/gui/achievement/achievement_background.png");

    /** Holds the instance of the game (Minecraft) */
    private Minecraft theGame;

    /** Holds the latest width scaled to fit the game window. */
    private int talentWindowWidth;

    /** Holds the latest height scaled to fit the game window. */
    private int achievementWindowHeight;
    private String talentGetLocalText;
    private String talentStatName;

    /** Holds the achievement that will be displayed on the GUI. */
    private Talent theTalent;
    private long talentTime;

    /**
     * Holds a instance of RenderItem, used to draw the achievement icons on screen (is based on ItemStack)
     */
    private RenderItem itemRender;
    private boolean haveTalent;

    public GuiTalent(Minecraft par1Minecraft)
    {
        this.theGame = par1Minecraft;
        this.itemRender = new RenderItem();
    }

    /**
     * Queue a taken achievement to be displayed.
     */
    public void queueTakenTalent(Talent talent)
    {
        this.talentGetLocalText = I18n.func_135053_a("talent.get");
        this.talentStatName = I18n.func_135053_a(talent.name);
        this.talentTime = Minecraft.getSystemTime();
        this.theTalent = talent;
        this.haveTalent = false;
    }

    /**
     * Queue a information about a achievement to be displayed.
     */
    public void queueTalentInformation(Talent talent)
    {
        this.talentGetLocalText = I18n.func_135053_a(talent.name);
        this.talentStatName = talent.desc;
        this.talentTime = Minecraft.getSystemTime() - 2500L;
        this.theTalent = talent;
        this.haveTalent = true;
    }

    /**
     * Update the display of the achievement window to match the game window.
     */
    private void updateTalentWindowScale()
    {
        GL11.glViewport(0, 0, this.theGame.displayWidth, this.theGame.displayHeight);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        this.talentWindowWidth = this.theGame.displayWidth;
        this.achievementWindowHeight = this.theGame.displayHeight;
        ScaledResolution scaledresolution = new ScaledResolution(this.theGame.gameSettings, this.theGame.displayWidth, this.theGame.displayHeight);
        this.talentWindowWidth = scaledresolution.getScaledWidth();
        this.achievementWindowHeight = scaledresolution.getScaledHeight();
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, (double)this.talentWindowWidth, (double)this.achievementWindowHeight, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }

    /**
     * Updates the small achievement tooltip window, showing a queued achievement if is needed.
     */
    public void updateTalentWindow()
    {
        if (this.theTalent != null && this.talentTime != 0L)
        {
            double d0 = (double)(Minecraft.getSystemTime() - this.talentTime) / 3000.0D;

            if (!this.haveTalent && (d0 < 0.0D || d0 > 1.0D))
            {
                this.talentTime = 0L;
            }
            else
            {
                this.updateTalentWindowScale();
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                double d1 = d0 * 2.0D;

                if (d1 > 1.0D)
                {
                    d1 = 2.0D - d1;
                }

                d1 *= 4.0D;
                d1 = 1.0D - d1;

                if (d1 < 0.0D)
                {
                    d1 = 0.0D;
                }

                d1 *= d1;
                d1 *= d1;
                int i = this.talentWindowWidth - 160;
                int j = 0 - (int)(d1 * 36.0D);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                this.theGame.func_110434_K().func_110577_a(field_110331_a);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(i, j, 96, 202, 160, 32);

                if (this.haveTalent)
                {
                    this.theGame.fontRenderer.drawSplitString(this.talentStatName, i + 30, j + 7, 120, -1);
                }
                else
                {
                    this.theGame.fontRenderer.drawString(this.talentGetLocalText, i + 30, j + 7, -256);
                    this.theGame.fontRenderer.drawString(this.talentStatName, i + 30, j + 18, -1);
                }

                RenderHelper.enableGUIStandardItemLighting();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glEnable(GL11.GL_COLOR_MATERIAL);
                GL11.glEnable(GL11.GL_LIGHTING);
                this.itemRender.renderItemAndEffectIntoGUI(this.theGame.fontRenderer, this.theGame.func_110434_K(), this.theTalent.itemStack, i + 8, j + 8);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }
        }
    }
}
