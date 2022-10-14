package soar.management.mod.other;

import soar.Soar;
import soar.gui.settings.GuiClientSettings;
import soar.management.event.EventTarget;
import soar.management.event.impl.EventKey;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;

public class ClientSettingsMod extends Mod{

	public ClientSettingsMod() {
		super("Client Settings", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.setToggled(true);
		this.setHide(true);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		this.setToggled(true);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKeyCode() == Soar.instance.keyBindManager.CLIENT_SETTING.getKeyCode()) {
			mc.displayGuiScreen(new GuiClientSettings());
		}
	}
}
