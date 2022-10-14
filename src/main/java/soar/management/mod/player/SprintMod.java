package soar.management.mod.player;

import net.minecraft.client.settings.KeyBinding;
import soar.management.event.EventTarget;
import soar.management.event.impl.EventUpdate;
import soar.management.mod.Mod;
import soar.management.mod.ModCategory;

public class SprintMod extends Mod{

	public SprintMod() {
		super("Sprint", ModCategory.PLAYER);
	}

	@EventTarget
	public void onUpdate(EventUpdate event) {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
	}
}
