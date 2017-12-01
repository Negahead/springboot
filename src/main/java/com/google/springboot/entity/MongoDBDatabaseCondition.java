package com.google.springboot.entity;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoDBDatabaseCondition implements Condition{
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String enabedDBType = conditionContext.getEnvironment().getProperty("app.dbType");
        return ( enabedDBType != null && enabedDBType.equalsIgnoreCase("MONGODB"));
    }
}
