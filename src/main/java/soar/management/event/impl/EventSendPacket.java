package soar.management.event.impl;

import net.minecraft.network.Packet;
import soar.management.event.Event;

public final class EventSendPacket extends Event {

	private final Packet<?> packet;

	public EventSendPacket(Packet<?> packet) {
		this.packet = packet;
	}

	public Packet<?> getPacket() {
		return packet;
	}
}
