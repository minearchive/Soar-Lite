package soar.management.event.impl;

import soar.management.event.Event;

public final class EventKey extends Event {

	private final int keyCode;

	public EventKey(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return this.keyCode;
	}
}
