package in.parapengu.commons.nms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleMethod {

	private SimpleObject parent;
	private Method method;

	public SimpleMethod(SimpleObject parent, Method method) {
		this.parent = parent;
		this.method = method;
		this.method.setAccessible(true);
	}

	public Class<?> result() {
		return method.getReturnType();
	}

	public Object value(Object... params) {
		return value(result(), params);
	}

	public <T> T value(Class<T> result, Object... params) {
		try {
			return (T) method.invoke(parent.getObject(), params);
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

}
