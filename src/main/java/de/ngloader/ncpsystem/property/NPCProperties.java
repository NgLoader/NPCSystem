package de.ngloader.ncpsystem.property;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NPCProperties {

	private final Map<String, Object> metadata = new HashMap<>();

	public Object set(String key, Object value) {
		return this.metadata.put(key, value);
	}

	public Object get(String key) {
		return this.metadata.get(key);
	}

	public <T> T get(String key, T defaultValue, Class<T> clazz) {
		Object value = this.metadata.getOrDefault(key, defaultValue);
		if (value != null && clazz.isInstance(value)) {
			return clazz.cast(value);
		}

		return defaultValue;
	}

	public String getString(String key) {
		return this.get(key, null, String.class);
	}

	public boolean getBoolean(String key) {
		return this.get(key, false, Boolean.class);
	}

	public int getInt(String key) {
		return this.get(key, 0, Integer.class);
	}

	public float getFloat(String key) {
		return this.get(key, 0f, Float.class);
	}

	public double getDouble(String key) {
		return this.get(key, 0d, Double.class);
	}

	public short getShort(String key) {
		return this.get(key, (short) 0, Short.class);
	}

	public byte getByte(String key) {
		return this.get(key, (byte) 0, Byte.class);
	}

	public boolean has(String key) {
		return this.metadata.containsKey(key);
	}

	public Map<String, Object> getMetadata() {
		return Collections.unmodifiableMap(this.metadata);
	}
}