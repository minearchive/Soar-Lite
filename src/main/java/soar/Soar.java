package soar;

import java.io.File;

import net.minecraft.client.Minecraft;
import soar.management.config.ConfigManager;
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
	public ConfigManager configManager;
	
	private File soarDir;
	
	public void startClient() {
		
		soarDir = new File(Minecraft.getMinecraft().mcDataDir, "soar-lite");
		
		if(!soarDir.exists()) {
			soarDir.mkdir();
		}
		
		keyBindManager = new KeyBindManager();
		settingsManager = new SettingsManager();
		eventManager = new EventManager();
		modManager = new ModManager();
		configManager = new ConfigManager();
	}
	
	public void stopClient() {
		
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public File getSoarDir() {
		return soarDir;
	}
}
