package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.DeviceConnection;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class DeviceProperties
{
    private final static Logger log = Logger.getLogger(DeviceProperties.class);

    public static void addDeviceProp(
            AnnotationConfigApplicationContext ctrlContext,
            String propertyName,
            DeviceConnection device){

        ConfigurableEnvironment env = ctrlContext.getEnvironment();
        MutablePropertySources sources =env.getPropertySources();

        Map<String,Object> prop = createDevProp(device);

        sources.addFirst(new MapPropertySource(propertyName, prop));
    }

    private static Map<String,Object> createDevProp(DeviceConnection device){
        Map<String,Object> prop = new HashMap<>();

        prop.put("com.artronics.senator.controller.device.id",device.getId());
        prop.put("com.artronics.senator.controller.device.connection.connection_string",
                 device.getConnectionString());

        return prop;
    }
}
