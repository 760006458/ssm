package com.heima.service;

import com.heima.domain.User;

/**
 * xuan
 * 2018/1/26
 */
public interface MyService {
    public User findUserById(Integer id);
    public void updateUser(User user);
    public void insertUser(User user);
    public void threadLocalMethod();
}
