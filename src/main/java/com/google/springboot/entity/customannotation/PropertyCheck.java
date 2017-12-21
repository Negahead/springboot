package com.google.springboot.entity.customannotation;

import com.google.springboot.entity.impl.AnnotationValueCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(AnnotationValueCondition.class)
public @interface PropertyCheck {
    String value();
}
