package alb.util.reflection;


public class ReflectionUtil {

	public static <T> T newInstance(Class<T> clazz) {
		try {
			
			return clazz.newInstance();
			
		} catch (InstantiationException e) {
			throw new RuntimeException( e );
		} catch (IllegalAccessException e) {
			throw new RuntimeException( e );
		}
	}

	public static Class<?> loadClass(String className) {
		try {
			
			return Class.forName(className);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException( e );
		}
	}

}
