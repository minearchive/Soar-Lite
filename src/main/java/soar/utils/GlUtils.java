package soar.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;

public final class GlUtils implements Utils {

    public static void scissor(float x, float y, float width, float height) {
        int scaleFactor = getScaleFactor();
        GL11.glScissor((int)(x * scaleFactor), (int)(MC.displayHeight - (y + height) * scaleFactor), (int)(((x + width) - x) * scaleFactor), (int)(((y + height) - y) * scaleFactor));
    }

    public static int getScaleFactor() {
        int scaleFactor = 1;
        boolean isUnicode = MC.isUnicode();
        int guiScale = MC.gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }

        while (scaleFactor < guiScale && MC.displayWidth / (scaleFactor + 1) >= 320 && MC.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        if (isUnicode && scaleFactor % 2 != 0 && scaleFactor != 1) {
            --scaleFactor;
        }
        return scaleFactor;
    }

    public static void startScale(float x, float y, float width, float height, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((x + (x + width)) / 2, (y + (y + height)) / 2, 0);
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.translate(-(x + (x + width)) / 2, -(y + (y + height)) / 2, 0);
    }

    public static void stopScale() {
        GlStateManager.popMatrix();
    }

    public static void fixEnchantment() {
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }
}
