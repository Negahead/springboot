package com.google.springboot.entity;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDBDatabaseCondition implements Condition{
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String enabledDBType = conditionContext.getEnvironment().getProperty("dbType");
        return ( enabledDBType != null && enabledDBType.equalsIgnoreCase("MONGODB"));
    }
}
