package com.heima.test;

import com.heima.threadlocal.MyThreadLocal;
import org.junit.Before;
import org.junit.Test;

/**
 * xuan
 * 2018/2/4
 */
public class AopThreadLocalTest {
    @Before
    public void myBefore(){
        String param = "abc";
        MyThreadLocal.set(param);
    }

    @Test
    public void method(){
        System.out.println(MyThreadLocal.get());
    }
}
