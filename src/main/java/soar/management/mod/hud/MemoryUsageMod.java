package soar.management.mod.hud;

import soar.management.event.EventTarget;
import soar.management.event.impl.EventRender2D;
import soar.management.mod.HUDMod;

public class MemoryUsageMod extends HUDMod{

	public MemoryUsageMod() {
		super("Memory Usage");
	}

	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@Override
	public String getText() {
		
		Runtime runtime = Runtime.getRuntime();
		String mem = "Mem: " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "%";
		
		return mem;
	}
}
