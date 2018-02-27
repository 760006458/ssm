package annotation;

import java.lang.annotation.*;

/**
 * xuan
 * 2018/2/27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnno {
    String aaa() default "";
    int bbb() default 10;
    String[] ccc() default {};
}
