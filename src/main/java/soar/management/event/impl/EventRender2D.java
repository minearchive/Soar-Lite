package soar.management.event.impl;

import soar.management.event.Event;

public final class EventRender2D extends Event {

	private final float partialTicks;

	public EventRender2D(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}
