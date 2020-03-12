package com.wowsanta.scim.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wowsanta.scim.type.RestfulServiceType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SCIM_ENTITY {
	String name();
	String repository() default Constants.DEFATUL_REPOSITORY;
	RestfulServiceType[] restful() default RestfulServiceType.ALL;
}
