package com.dolplay.nutzshiro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorization {

	public abstract String requiresPermissions() default "";

	public abstract String requiresRolesAll() default "";

	public abstract String requiresRolesAtLeastOne() default "";

	public abstract boolean requiresUser() default false;

	public abstract boolean requiresGuest() default false;
}