package soar.gui;

import net.minecraft.client.gui.GuiScreen;
import soar.Soar;
import soar.gui.settings.GuiModMenu;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;
import soar.utils.mouse.MouseUtils;

public final class GuiEditHUD extends GuiScreen {

	@Override
	public void initGui() {
		for(Mod m : Soar.INSTANCE.modManager.getMods()) {
			m.setDragging(false);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		for(Mod m : Soar.INSTANCE.modManager.getMods()) {
			if(m.isDragging()) {
				m.setX(mouseX + m.getDraggingX());
				m.setY(mouseY + m.getDraggingY());
			}
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {
		if(keyCode == 1) {
			mc.displayGuiScreen(new GuiModMenu());
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		for(Mod m : Soar.INSTANCE.modManager.getMods()) {
			if(m.isToggled() && m.getCategory().equals(ModCategory.HUD)) {
				if(MouseUtils.isInside(mouseX, mouseY, m.getX(), m.getY(), m.getWidth(), m.getHeight())) {
					m.setDragging(true);
					m.setDraggingX(m.getX() - mouseX);
					m.setDraggingY(m.getY() - mouseY);
				}
			}
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for(Mod m : Soar.INSTANCE.modManager.getMods()) {
			m.setDragging(false);
		}
	}
}
