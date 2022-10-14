package soar.management.mod;

import soar.Soar;
import soar.utils.RenderUtils;

public class HUDMod extends Mod {

	public HUDMod(String name) {
		super(name, ModCategory.HUD);
	}
	
	public void setupHUD() {
		this.addBooleanSetting("Font Shadow", this, true);
		this.addBooleanSetting("Background", this, true);
	}

	public void onRender2D() {
		
		boolean background = Soar.instance.settingsManager.getSettingByName(this, "Font Shadow").getValBoolean();
		boolean fontShadow = Soar.instance.settingsManager.getSettingByName(this, "Font Shadow").getValBoolean();
		
		if(background) {
			RenderUtils.drawRect(this.getX(), this.getY(), fr.getStringWidth(this.getText()) + 8, fr.FONT_HEIGHT + 8, this.getBackgroundColor());
		}
		fr.drawString(this.getText(), this.getX() + 4, this.getY() + 5, this.getFontColor(), fontShadow);
	}
	
	public String getText() {
		return null;
	}
	
	private int getFontColor() {
		return -1;
	}
	
	private int getBackgroundColor() {
		return Integer.MIN_VALUE;
	}
}
