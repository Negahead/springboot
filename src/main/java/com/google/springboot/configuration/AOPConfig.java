package com.google.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
/**
 * Enables support for handling components marked with AspectJ's @Aspect annotation,
 */
@EnableAspectJAutoProxy
public class AOPConfig {
}
