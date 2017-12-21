package com.google.springboot.entity.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SomeClassPresentCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            Class.forName("com.google.springboot.entity.POJO.Tree");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
