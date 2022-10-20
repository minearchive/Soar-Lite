package soar.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public interface Utils {
	Minecraft MC = Minecraft.getMinecraft();
	FontRenderer FONT = MC.fontRendererObj;
}
