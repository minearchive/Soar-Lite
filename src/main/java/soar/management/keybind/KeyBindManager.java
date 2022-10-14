package soar.management.keybind;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindManager {

	private Minecraft mc = Minecraft.getMinecraft();
	private String category = "Soar Lite";
	
	public KeyBinding CLIENT_SETTING = new KeyBinding("Client Settings", Keyboard.KEY_RSHIFT, category);
	
	public KeyBindManager() {
		this.register(CLIENT_SETTING);
	}
	
    public void register(KeyBinding key) {
        mc.gameSettings.keyBindings = ArrayUtils.add(mc.gameSettings.keyBindings, key);
    }

    public void unregister(KeyBinding key) {
        if (Arrays.asList(mc.gameSettings.keyBindings).contains(key)) {
            mc.gameSettings.keyBindings = ArrayUtils.remove(mc.gameSettings.keyBindings, Arrays.asList(mc.gameSettings.keyBindings).indexOf(key));
            key.setKeyCode(0);
        }
    }
}
