package com.google.springboot.entity;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySQLDatabaseTypeCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String enabledDBType = context.getEnvironment().getProperty("app.dbType");
        return (enabledDBType != null && enabledDBType.equalsIgnoreCase("MYSQL"));
    }
}
