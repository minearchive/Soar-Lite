package soar.gui.settings;

import net.minecraft.client.gui.GuiScreen;
import soar.Soar;

public class GuiClientSettings extends GuiScreen{

	@Override
	public void initGui() {
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		
	}
	
	@Override
    public void onGuiClosed() {
		Soar.instance.configManager.save();
    }
}
