package com.fancy.mvvmdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.fancy.mvvmdemo.HuiFuApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class SPUtils {
    public static final String File_Name = "share_data";
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private static SPUtils instance = new SPUtils();

    private SPUtils() {
        sharedPreferences = HuiFuApplication.getInstance().getSharedPreferences(File_Name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SPUtils getInstance() {
        return instance;
    }

    public void saveData(String key, Object obj) {
        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, (String) obj);
        }
        SharePreferencesCompat.apply(editor);
    }

    public Object getData(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharePreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         */
        private static Method findApplyMethod() {

            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {

            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        public static void apply(SharedPreferences.Editor ed) {

            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(ed);
                    return;
                }
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            ed.commit();
        }
    }
}

