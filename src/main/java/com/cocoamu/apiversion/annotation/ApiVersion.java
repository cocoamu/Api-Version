package com.cocoamu.apiversion.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    // 版本号格式：x.y.z
    String value() default "1.0.0";
}
