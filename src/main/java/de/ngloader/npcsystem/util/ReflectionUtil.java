package de.ngloader.npcsystem.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

	public static void setField(Object instance, String name, Object value) {
		ReflectionUtil.setField(instance.getClass(), instance, name, value);
	}

	public static void setField(Class<?> clazz, Object instance, String name, Object value) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			field.set(instance, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getField(Object instance, String name) {
		return ReflectionUtil.getField(instance.getClass(), instance, name);
	}

	public static Object getField(Class<?> clazz, Object obj, String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Field getField(String className, String name) {
		try {
			return ReflectionUtil.getField(Class.forName(className), name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Field getField(Class<?> clazz, String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Method getMethod(String className, String name, Class<?>... parameterTypes) {
		try {
			return ReflectionUtil.getMethod(Class.forName(className), name, parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
		try {
			Method method = clazz.getDeclaredMethod(name, parameterTypes);
			method.setAccessible(true);
			return method;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... parameterTypes) {
		try {
			return clazz.getConstructor(parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
