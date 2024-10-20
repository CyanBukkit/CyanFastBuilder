package cubing.utils;

import java.lang.reflect.*;

public class Reflections
{
    public Reflections() {
        super();
    }
    
    public static void packetSetValue(final Object obj, final String name, final Object value) {
        try {
            final Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        }
        catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static Object packetGetValue(final Object obj, final String name) {
        try {
            final Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (final Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
