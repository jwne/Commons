package in.parapengu.commons.reflection;

public class SimpleObject {

	private Class<?> type;
	private Object object;

	public <T> SimpleObject(Object object, Class<T> type) {
		this.object = (T) object;
		this.type = type;
	}

	public Object getObject() {
		return object;
	}

	public SimpleMethod method(String name, Class<?>... params) {
		try {
			return new SimpleMethod(this, type.getDeclaredMethod(name, params));
		} catch(NoSuchMethodException e) {
			return null;
		}
	}

	public SimpleField field(String name) {
		try {
			return new SimpleField(this, type.getDeclaredField(name));
		} catch(NoSuchFieldException e) {
			return null;
		}
	}

}
