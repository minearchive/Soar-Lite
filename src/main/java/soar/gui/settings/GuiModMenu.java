package soar.gui.settings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import soar.Soar;
import soar.gui.GuiEditHUD;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;
import soar.utils.GlUtils;
import soar.utils.RenderUtils;
import soar.utils.mouse.MouseUtils;

public class GuiModMenu extends GuiScreen{

	private FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
	private ModCategory selectedCategory;
	
	public GuiModMenu() {
		selectedCategory = ModCategory.HUD;
	}
	
	@Override
	public void initGui() {
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
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
		
		//Draw categories
		for(ModCategory c : ModCategory.values()) {
			
			String formattedName = c.toString();

			GlUtils.startScale(x, y, fr.getStringWidth(formattedName), categoryOffsetY + fr.FONT_HEIGHT, 1.1F);
			fr.drawString(formattedName, x + 10, y + categoryOffsetY, -1);
			GlUtils.stopScale();
			
			categoryOffsetY +=26;
		}
		
		//Draw mods list
		for(Mod m : Soar.instance.modManager.getModByCategory(selectedCategory)) {
			
			if(!m.isHide()) {
				RenderUtils.drawRect(x + 100, y + modOffsetY, width - 115, 28, Integer.MIN_VALUE);
				fr.drawString(m.getName(), x + 110, y + modOffsetY + 10, -1);
				
				modOffsetY+=35;
			}
		}
		
		//Draw Edit Text
		RenderUtils.drawRect(x, (y + height) - 24, 85, 24, Integer.MIN_VALUE);
		fr.drawString("Edit HUD", x + 23, (y + height) - fr.FONT_HEIGHT - 7, -1);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		int addX = 190;
		int addY = 110;
		
		int x = (width / 2) - addX;
		int y = (height / 2) - addY;
		int width = addX * 2;
		int height = addY * 2;
		
		int categoryOffsetY = 48;
		int modOffsetY = 15;
		
		//Select Category
		for(ModCategory c : ModCategory.values()) {
			
			if(MouseUtils.isInside(mouseX, mouseY, x, (y - 10) + categoryOffsetY, 85, 22) && mouseButton == 0) {
				selectedCategory = c;
			}
			
			categoryOffsetY +=26;
		}
		
		for(Mod m : Soar.instance.modManager.getModByCategory(selectedCategory)) {
			
			if(!m.isHide()) {
				
				if(MouseUtils.isInside(mouseX, mouseY, x + 100, y + modOffsetY, width - 115, 28) && mouseButton == 0) {
					m.toggle();
				} else if(mouseButton == 1) {
					mc.displayGuiScreen(new GuiModSettings(m, this));
				}
				
				modOffsetY+=35;
			}
		}
		
		//Go to edit screen
		if(MouseUtils.isInside(mouseX, mouseY, x, (y + height) - 24, 85, 24) && mouseButton == 0) {
			mc.displayGuiScreen(new GuiEditHUD());
		}
	}
	
	@Override
    public void onGuiClosed() {
		Soar.instance.configManager.save();
    }
}
