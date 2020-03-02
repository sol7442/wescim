package com.wowsanta.scim.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ENTITY {
	String name();
	String domain() default Constants.DEFATUL_DOMAIN;
}
