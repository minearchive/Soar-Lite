package soar;

import java.io.File;

import net.minecraft.client.Minecraft;
import soar.management.config.ConfigManager;
import soar.management.event.EventManager;
import soar.management.keybind.KeyBindManager;
import soar.management.mod.ModManager;
import soar.management.setting.SettingsManager;
import soar.mixin.mixins.client.IMixinMinecraft;

public final class Soar {

    public static final Soar INSTANCE = new Soar();

    private static final String
            NAME = "Soar Lite",
            VERSION = "1.0";

    public KeyBindManager keyBindManager;
    public SettingsManager settingsManager;
    public EventManager eventManager;
    public ModManager modManager;
    public ConfigManager configManager;

    private File soarDir;

    public void startClient() {
        soarDir = new File(Minecraft.getMinecraft().mcDataDir, "soar-lite");

        if (!soarDir.exists()) {
            soarDir.mkdirs();
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
        return NAME;
    }

    public String getVersion() {
        return VERSION;
    }

    public File getSoarDir() {
        return soarDir;
    }
}
