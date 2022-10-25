package soar.gui.settings;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import org.lwjgl.input.*;
import soar.*;
import soar.management.mod.*;
import soar.management.setting.*;
import soar.utils.*;
import soar.utils.mouse.*;

import java.awt.*;
import java.io.*;
import java.text.*;

public final class GuiModSettings extends GuiScreen {
    private final Mod mod;
    private final GuiScreen parentScreen;

    public GuiModSettings(Mod mod, GuiScreen parent) {
        if (mod == null) throw new IllegalArgumentException("Mod is null");

        this.mod = mod;
        this.parentScreen = parent;
    }

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        FontRenderer fr = mc.fontRendererObj;

        int addX = 190;
        int addY = 110;

        int x = (width / 2) - addX;
        int y = (height / 2) - addY;
        int width = addX * 2;
        int height = addY * 2;

        int categoryOffsetY = 42;
        int modOffsetY = 15;

        //Draw background
        RenderUtils.drawRect(x, y, width, height, Integer.MIN_VALUE);
        RenderUtils.drawRect(x, y, 85, height, Integer.MIN_VALUE);

        RenderUtils.drawRect(x, y + 29, 85, 1, Integer.MIN_VALUE);

        //Draw Soar lite Text
        GlUtils.startScale(x, y, fr.getStringWidth("Soar Lite"), fr.FONT_HEIGHT, 1.5F);
        fr.drawString("Soar Lite", x + 13, y + 10, -1);
        GlUtils.stopScale();


        GlUtils.startScale(x + 90, y, fr.getStringWidth(mod.getName()), fr.FONT_HEIGHT, 1.5F);
        fr.drawString(mod.getName(), x + 103, y + 10, -1);
        GlUtils.stopScale();

        int settingY = y + 30;

        String category = "";

        for (Setting setting : Soar.INSTANCE.settingsManager.getSettingByMod(mod)) {
            int settingHeight = fr.FONT_HEIGHT + 5;
            if (!category.equals(setting.getCategory())) {

                settingY += 5;

                GlStateManager.pushMatrix();
                GlStateManager.scale(1.5F, 1.5F, 1.5F);
                GlStateManager.translate((x + 95) / 1.5F, settingY / 1.5F, 0F);
                fr.drawString(setting.getCategory() + ": ", 0, 0, -1);
                GlStateManager.popMatrix();
                settingY += 15f;
                settingY += 5;

                category = setting.getCategory();
            }

            fr.drawString(setting.getName(), x + 97, settingY, -1);
            if (setting.isCheck()) {
                RenderUtils.drawRect(x + 102 + fr.getStringWidth(setting.getName()), settingY - 1, 9, 9, (setting.getValBoolean() ? Color.green : Color.red).getRGB());
            }
            if (setting.isSlider()) {

                int left = x + 102 + fr.getStringWidth(setting.getName());

                RenderUtils.drawRect(left, settingY - 1, 90, 9, Color.black.getRGB());
                RenderUtils.drawRect(left, settingY - 1, 90F * (float) (setting.getValFloat() / setting.getMax()), 9, -1);
                boolean drag = MouseUtils.isInside(mouseX, mouseY, left, settingY - 1, 90, 9) && Mouse.isButtonDown(0);
                if (drag) {
                    double diff = setting.getMax() - setting.getMin();
                    double mouse = MathHelper.clamp_double((mouseX - left) / 90D, 0, 1);
                    double newVal = setting.getMin() + mouse * diff;
                    setting.setValDouble(newVal);
                }

                int textLeft = left + 95;
                fr.drawString(new DecimalFormat("0.00").format(setting.getValDouble()), textLeft, settingY, -1);


            }

            settingY += settingHeight;
        }

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        int addX = 190;
        int addY = 110;

        int x = (width / 2) - addX;
        int y = (height / 2) - addY;
        int width = addX * 2;
        int height = addY * 2;

        int settingY = y + 30;

        String category = "";

        for (Setting setting : Soar.INSTANCE.settingsManager.getSettingByMod(mod)) {
            int settingHeight = fontRendererObj.FONT_HEIGHT + 5;
            if (!category.equals(setting.getCategory())) {

                settingY += 5;
                settingY += 15f;
                settingY += 5;

                category = setting.getCategory();
            }

            if (setting.isCheck()) {
                int settingX = x + 102 + fontRendererObj.getStringWidth(setting.getName());
                int settingX1 = settingX + 9;
                int settingY1 = settingY + 9;
                if(mouseX >= settingX && mouseY >= settingY && mouseX <= settingX1 && mouseY <= settingY1) {
                    setting.setValBoolean(!setting.getValBoolean());
                }

            }

            settingY += settingHeight;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(parentScreen);
        }
    }
}
