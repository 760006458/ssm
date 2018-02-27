package com.heima.threadlocal;

/**
 * xuan
 * 2018/1/30
 */
public class MyThreadLocal {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void set(Object obj) {
        threadLocal.set(obj);
    }

    public static Object get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
