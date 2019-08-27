package com.fancy.mvvmdemo.util;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class GeneratedClassUtils {
    public static <T> T getInstance(Class<T> clazz) {
        try {
            if (clazz == null)
                throw new RuntimeException("The clazz cannot be null!");
            String name = clazz.getCanonicalName();
            try {
                @SuppressWarnings("unchecked")
                Class<T> result = (Class<T>) Class.forName(name);
                return result.newInstance();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Cannot find class for" + name, e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
