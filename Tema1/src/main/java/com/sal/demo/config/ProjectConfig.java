package com.sal.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.sal.demo.repository", "com.sal.demo.service", "com.sal.demo.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {
}
