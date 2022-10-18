package soar.management.mod.hud;

import soar.management.event.EventTarget;
import soar.management.event.impl.EventRender2D;
import soar.management.mod.HUDMod;

public class DayCounterMod extends HUDMod{

	public DayCounterMod() {
		super("Day Counter");
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
		if(mc.theWorld != null && mc.theWorld.getWorldInfo() != null) {
			long time = mc.theWorld.getWorldInfo().getWorldTotalTime() / 24000L;
			
			return time + " Day" + (time != 1L ? "s" : "");
		}else {
			return null;
		}
	}
}
