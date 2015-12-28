package com.artronics.senator.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {
        "com.artronics.senator.controller"
})
@PropertySource({
        "classpath:senator/controller-defaults.properties",
        "classpath:senator/device-defaults.properties"})
public class ControllerConfig
{
    private final static Logger log = Logger.getLogger(ControllerConfig.class);
}
