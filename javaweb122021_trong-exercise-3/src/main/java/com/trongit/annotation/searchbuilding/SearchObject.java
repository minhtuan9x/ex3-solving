package com.trongit.annotation.searchbuilding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SearchObject {
    String value() default "";
    String alias() default "";
    String groupBy() default "";
}
