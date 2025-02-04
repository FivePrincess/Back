package com.example.guzip.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Retention(유지 정책): 에너테이션이 얼마나 오래 어디까지 유지 되는지
//Target: 적용 대상 (method)에만!!
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('EMPLOYER')")
public @interface RequireEmployer {

}
