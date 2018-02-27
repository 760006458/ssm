package com.heima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * xuan
 * 2018/2/5
 */
public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * 注1：多个拦截器组合：
     * 第一个拦截器的preHandle返回false，则第一个拦截器的postHandle和afterCompletion都不执行；第二个拦截器3个方法都不执行。
     * 注2：preHandle按拦截器顺序执行，postHandle和afterCompletion都是逆序执行
     *
     * eg：HandlerInterceptor1的preHandler方法返回true，HandlerInterceptor2返回false
     * 则：
     *  HandlerInterceptor1..preHandle..
     *  HandlerInterceptor2..preHandle..
     *  HandlerInterceptor1..afterCompletion..
     * 结论：
     *  只有preHandler都是true时，postHandle才会执行；而afterCompletion只要相关拦截器的preHandle为true即可执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("HandlerInterceptor1..preHandle..");
        return true;
    }

    /**
     * controller执行后但未返回视图前调用此方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor1..postHandle..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("HandlerInterceptor1..afterCompletion..");
    }
}
