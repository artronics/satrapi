package com.artronics.senator.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:senator/controller-test-defaults.properties",
        "classpath:senator/device-test-defaults.properties"})
public class ControllerConfigTest
{

}