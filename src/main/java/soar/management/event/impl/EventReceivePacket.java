package soar.management.event.impl;

import net.minecraft.network.Packet;
import soar.management.event.Event;

public final class EventReceivePacket extends Event {

	private final Packet<?> packet;

	public EventReceivePacket(Packet<?> packet) {
		this.packet = packet;
	}

	public Packet<?> getPacket() {
		return packet;
	}
}
