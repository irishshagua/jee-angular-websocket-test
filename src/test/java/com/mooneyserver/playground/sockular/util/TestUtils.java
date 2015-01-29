package com.mooneyserver.playground.sockular.util;

import java.lang.reflect.Field;

public class TestUtils {

	@SuppressWarnings("unchecked")
	public static <T> T getPrivateFieldFromClass(Object obj, String fieldName, Class<T> fieldType) {		
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}