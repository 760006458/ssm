package com.heima.service.impl;

import com.heima.domain.User;
import com.heima.repository.UserMapper;
import com.heima.service.MyService;
import com.heima.threadlocal.MyThreadLocal;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * xuan
 * 2018/1/26
 */
@Service
public class MyServiceImpl implements MyService,ApplicationContextAware {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注：验证事务传播时，不能在bean内部service1方法中调用自己的另一个service2方法！
     * 要么从spring容器中将自己的bean重新获取出来，要么@Autowired注入其他的bean
     */
    private ApplicationContext context;

//    注：bean不能自己注入自己，因为spring扫描到MyServiceImpl这个类，发现内部需要注入MyService类型的bean，
//    而此时spring容器还没有这个bean，所以启动报错。一个类只有内部把所有@Autowired的bean注入，且类本身不报错时，
//    这个bean才会被放入spring容器。
//    @Autowired
//    private MyService myService;

    /**
     * 一个事务方法内部，调用另外两个事务方法
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public User findUserById(Integer id) {
        MyService myService = context.getBean(MyService.class);
        //从spring的bean容器中获取MyService
//        MyService myService = context.getBean(MyService.class);
        System.out.println(myService);
        System.out.println("myService:aop---" + AopUtils.isAopProxy(myService));
        System.out.println("myService:jdk---" + AopUtils.isJdkDynamicProxy(myService));
        System.out.println("myService:cglib---" + AopUtils.isCglibProxy(myService));

        User user = new User();
        user.setId(1);
        user.setName("test1");
        //验证事务的传播
        myService.updateUser(user);
        System.out.println("user被修改了");

        System.out.println(this);
        System.out.println("this:aop---" + AopUtils.isAopProxy(this));
        System.out.println("this:jdk---" + AopUtils.isJdkDynamicProxy(this));
        System.out.println("this:cglib---" + AopUtils.isCglibProxy(this));

        User user2 = new User();
        user2.setName("张三");
        user2.setAge(23);
        user2.setAddress("上海");
        //验证事务的传播
        myService.insertUser(user2);
        System.out.println("插入一条数据");
        int i = 10 / 0;


        return userMapper.findUserById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void threadLocalMethod() {
        HashMap hashMap = (HashMap) MyThreadLocal.get();
        System.out.println(hashMap.get("param"));
        HttpServletRequest request = (HttpServletRequest) hashMap.get("request");
        System.out.println(request.getParameter("param"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
