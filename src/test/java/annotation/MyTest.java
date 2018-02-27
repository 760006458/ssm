package annotation;

import java.lang.reflect.Method;

/**
 * xuan
 * 2018/2/27
 */
public class MyTest {
    @MyAnno
    public void test1(){
        System.out.println(123);
    }

    public static void main(String[] args) throws Exception {
        Method test1 = MyTest.class.getMethod("test1");
        MyAnno myAnno = test1.getAnnotation(MyAnno.class);
        System.out.println(myAnno.aaa());
    }
}
