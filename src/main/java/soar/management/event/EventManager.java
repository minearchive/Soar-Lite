package soar.management.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class EventManager {

	private Map<Class<?>, ArrayHelper<Data>> REGISTRY_MAP = new HashMap<>();

	public void register(Object o) {
		for (Method method : o.getClass().getDeclaredMethods()) {
			if (isApplicable(method)) {
				register(method, o);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void register(Method method, Object o) {
		Class<?> clazz = method.getParameterTypes()[0];
		Data methodData = new Data(o, method, method.getAnnotation(EventTarget.class).value());

		if (!methodData.target.isAccessible()) {
			methodData.target.setAccessible(true);
		}

		if (REGISTRY_MAP.containsKey(clazz)) {
			if (!REGISTRY_MAP.get(clazz).contains(methodData)) {
				REGISTRY_MAP.get(clazz).add(methodData);
				sortListValue((Class<? extends Event>) clazz);
			}
		} else {
			ArrayHelper<Data> data = new ArrayHelper<>();
			data.add(methodData);
			REGISTRY_MAP.put(clazz, data);
		}
	}

	public void unregister(Object o) {

		for (ArrayHelper<Data> list : REGISTRY_MAP.values()) {
			for (Data data : list) {
				if (data.source.equals(o)) {
					list.remove(data);
				}
			}
		}

		cleanMap(true);
	}

	public void unregister(Object o, Class<? extends Event> clazz) {
		if (REGISTRY_MAP.containsKey(clazz)) {
			for (final Data methodData : REGISTRY_MAP.get(clazz)) {
				if (methodData.source.equals(o)) {
					REGISTRY_MAP.get(clazz).remove(methodData);
				}
			}

			cleanMap(true);
		}
	}

	public void cleanMap(boolean b) {
		Iterator<Entry<Class<?>, ArrayHelper<Data>>> iterator = REGISTRY_MAP.entrySet().iterator();

		while (iterator.hasNext()) {
			if (!b || iterator.next().getValue().isEmpty()) {
				iterator.remove();
			}
		}
	}

	public void removeEnty(Class<? extends Event> clazz) {
		Iterator<Entry<Class<?>, ArrayHelper<Data>>> iterator = REGISTRY_MAP.entrySet().iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getKey().equals(clazz)) {
				iterator.remove();
				break;
			}
		}
	}

	private void sortListValue(Class<? extends Event> clazz) {
		ArrayHelper<Data> array = new ArrayHelper<>();

		for (byte b : Priority.VALUE_ARRAY) {
			for (Data methodData : REGISTRY_MAP.get(clazz)) {
				if (methodData.priority == b) {
					array.add(methodData);
				}
			}
		}

		REGISTRY_MAP.put(clazz, array);
	}

	private boolean isApplicable(Method method) {
		return method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class);
	}

	public ArrayHelper<Data> get(Class<? extends Event> clazz) {
		return REGISTRY_MAP.get(clazz);
	}

	public void shutdown() {
		REGISTRY_MAP.clear();
	}
}
