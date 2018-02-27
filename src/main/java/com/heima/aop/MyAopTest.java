package com.heima.aop;

import com.heima.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * xuan
 * 2018/1/29
 */
@Component
public class MyAopTest<T> {

    public User findUserTest(T...t) {
        System.out.println(t.length);
        System.out.println(t[0]);
        User user = new User();
        user.setName("李四");
        user.setAge(24);
        user.setAddress("广州");
        return user;
    }
}
