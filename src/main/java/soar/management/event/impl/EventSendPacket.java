package soar.management.event.impl;

import net.minecraft.network.Packet;
import soar.management.event.Event;

public class EventSendPacket extends Event{

	private Packet<?> packet;
	
	public EventSendPacket(Packet<?> packet) {
		this.packet = packet;
	}

	public Packet<?> getPacket() {
		return packet;
	}

	public void setPacket(Packet<?> packet) {
		this.packet = packet;
	}
}
