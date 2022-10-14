package soar.management.event.impl;

import soar.management.event.Event;

public class EventKey extends Event{

	private int keyCode;
	
	public EventKey(int keyCode) {
		this.keyCode = keyCode;
	}
	
	public int getKeyCode() {
		return this.keyCode;
	}
	
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
}
