package soar.management.mod.hud;

import net.minecraft.client.Minecraft;
import soar.management.event.EventTarget;
import soar.management.event.impl.EventRender2D;
import soar.management.mod.HUDMod;

public class FPSDisplayMod extends HUDMod {

	public FPSDisplayMod() {
		super("FPS Display");
	}
	
	@Override
	public void setup() {
		super.setupHUD();
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@Override
	public String getText() {
		return Minecraft.getDebugFPS() + " FPS";
	}
}
