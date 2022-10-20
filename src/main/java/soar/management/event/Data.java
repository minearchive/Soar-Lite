package soar.management.event;

import java.lang.reflect.Method;

public final class Data {

	public final Object source;
	public final Method target;
	public final byte priority;

	public Data(Object source, Method target, byte priority) {
		this.source = source;
		this.target = target;
		this.priority = priority;
	}
}
