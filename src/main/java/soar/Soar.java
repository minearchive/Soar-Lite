package soar;

import soar.management.event.EventManager;
import soar.management.keybind.KeyBindManager;
import soar.management.mod.ModManager;
import soar.management.setting.SettingsManager;

public class Soar {

	public static Soar instance = new Soar();

	private final String name = "Soar Lite", version = "1.0";
	
	public KeyBindManager keyBindManager;
	public SettingsManager settingsManager;
	public EventManager eventManager;
	public ModManager modManager;
	
	public void startClient() {
		keyBindManager = new KeyBindManager();
		settingsManager = new SettingsManager();
		eventManager = new EventManager();
		modManager = new ModManager();
	}
	
	public void stopClient() {
		
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}
}
