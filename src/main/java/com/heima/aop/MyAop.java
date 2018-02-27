package com.heima.aop;

import com.heima.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * xuan
 * 2018/1/29
 */
@Component
@Aspect
public class MyAop {

    //使用@Pointcut统一简化切点表达式
    @Pointcut("execution(* com.heima.aop.MyAopTest.*(..))")
    private void myPointcut1() {
    }

    //@Before("execution(* com.heima.aop.MyAopTest.*(..))")
    @Before("myPointcut1()")
    public void myBefore() {
        System.out.println("before拦截前");
        System.out.println("---------");
    }

    @Around("myPointcut1()")
    public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around前");

        //注：此处必须把返回值返回，否则切点方法的返回值就在此处丢失了，那么AfterReturning就无法获取到切点方法的返回值
        //此处篡改调用方法的参数
        Object[] args = joinPoint.getArgs();
        args[0] = "abcd";
        Object result = joinPoint.proceed(args);

        System.out.println("around后");
        return result;
    }

    @AfterReturning(pointcut = "myPointcut1()", returning = "returnValue")
    public void myAfterReturnning(User returnValue) {
        System.out.println("AfterReturning后");
        System.out.println("方法返回值：" + returnValue);
        System.out.println("---------");
    }

}
