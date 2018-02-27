package com.heima.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * xuan
 * 2018/2/4
 */
//@RunWith(value = SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/application-*.xml")
public class AopTest {
    @Before
    public void myBefore(){
        AopTest o = (AopTest) wrapMethod(this);
        o.myTest();
    }

    @Test
    public void myTest(){
        System.out.println("abc");
    }

    public Object wrapMethod(final Object obj){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AopTest.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("-------");
                //方式1
                return method.invoke(obj,args);
                //方式2
//                return methodProxy.invokeSuper(proxy,args);
            }
        });
        return enhancer.create();
    }
}
