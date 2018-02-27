package com.heima.repository;

import com.heima.domain.User;

/**
 * 注：mybatis的mapper.xml文件必须放在resources的同目录文件夹下，否则文件在编译过程中丢失
 * xuan
 * 2018/1/26
 */
public interface UserMapper {
    public User findUserById(Integer id);
    public void updateUser(User user);
    public void insertUser(User user);
}
