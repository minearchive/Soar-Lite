package soar.management.mod.hud;

import soar.management.event.EventTarget;
import soar.management.event.impl.EventRender2D;
import soar.management.mod.HUDMod;
import soar.utils.PlayerUtils;

public class PotionCounterMod extends HUDMod{

	public PotionCounterMod() {
		super("Potion Counter");
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
		return PlayerUtils.getPotionsFromInventory() + " pot" + (PlayerUtils.getPotionsFromInventory() <= 1 ? "s" : "");
	}
}
