package com.heima.controller;

import com.heima.domain.User;
import com.heima.service.MyService;
import com.heima.threadlocal.MyThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * xuan
 * 2018/1/26
 */
@Controller
public class MyController {

    @Autowired
    private MyService myService;

    //注：springmvc每次访问action都必须进行页面跳转（@ResponseBody除外）
    //如果返回null，或者方法返回值为void，那么会默认跳转到WEB-INF/jsp/method.jsp
    @RequestMapping("/method")
    public String method1() {
        HashMap hashMap = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        User user = myService.findUserById(1);
        System.out.println(user);
        return "hello";
    }

    @RequestMapping("/threadLocalMethod")
    public String threadLocalMethod(String param, HttpServletRequest request) {
        MyThreadLocal.set(param);
        myService.threadLocalMethod();
        return "hello";
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        long size = uploadFile.getSize();
        if (size >= 102400) {
            return "error";
        }
        String originalFilename = uploadFile.getOriginalFilename();

        String path = request.getSession().getServletContext().getRealPath("WEB-INF/upload") + "/" + originalFilename;
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }
        uploadFile.transferTo(new File(path));
        return "success";
    }

    /**
     * 注：RequestMapping不写括号时，springmvc中对js和css静态文件的放行不管用了！
     * 访问css也会被拦截到该方法，而且访问所有路径（胡乱写）时都会进入此方法！
     * 所以一定要谨慎！
     * @param request
     * @return
     */
    /*@RequestMapping
    public String index(HttpServletRequest request){
        System.out.println(request.getRequestURL());
        return "index";
    }*/

}
