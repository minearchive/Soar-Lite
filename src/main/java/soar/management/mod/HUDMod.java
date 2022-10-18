package soar.management.mod;

import soar.Soar;
import soar.management.setting.*;
import soar.utils.RenderUtils;

public class HUDMod extends Mod {

	public HUDMod(String name) {
		super(name, ModCategory.HUD);
	}
	
	public void setupHUD() {
		Setting font_shadow = this.addBooleanSetting("Font Shadow", this, true);
		Setting background = this.addBooleanSetting("Background", this, true);

		// TODO: Make it in constructor
		font_shadow.setCategory("Render");
		background.setCategory("Render");

		Setting test = this.addSliderSetting("Test", this, 0, 0, 15, false);
		test.setCategory("Test");
	}

	public void onRender2D() {
		
		boolean background = Soar.instance.settingsManager.getSettingByName(this, "Font Shadow").getValBoolean();
		boolean fontShadow = Soar.instance.settingsManager.getSettingByName(this, "Font Shadow").getValBoolean();
		
		if(background) {
			RenderUtils.drawRect(this.getX(), this.getY(), fr.getStringWidth(this.getText()) + 8, fr.FONT_HEIGHT + 8, this.getBackgroundColor());
		}
		
		fr.drawString(this.getText(), this.getX() + 4, this.getY() + 5, this.getFontColor(), fontShadow);
		
		this.setWidth(fr.getStringWidth(this.getText()) + 8);
		this.setHeight(fr.FONT_HEIGHT + 8);
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
