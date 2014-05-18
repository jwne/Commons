package in.parapengu.commons.nms;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleField {

	private SimpleObject parent;
	private Field field;

	public SimpleField(SimpleObject parent, Field field) {
		this.parent = parent;
		this.field = field;
		this.field.setAccessible(true);
	}

	public Class<?> result() {
		return field.getType();
	}

	public Object value() {
		return value(result());
	}

	public <T> T value(Class<T> result) {
		try {
			return (T) field.get(parent.getObject());
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean set(Object value) {
		return set(result(), value);
	}

	public <T> boolean set(Class<T> result, Object value) {
		try {
			field.set(parent.getObject(), (T) value);
			return true;
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		}

		return false;
	}

}
