package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.SdwnController;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class ControllerProperties
{
    private final static Logger log = Logger.getLogger(ControllerProperties.class);

    public static void addCtrlProp(
            AnnotationConfigApplicationContext ctrlContext,
            String propertyName,
            SdwnController controller){

        ConfigurableEnvironment env = ctrlContext.getEnvironment();
        MutablePropertySources sources =env.getPropertySources();

        Map<String,Object> prop = createCtrlProp(controller);

        sources.addFirst(new MapPropertySource(propertyName,prop));
    }

    private static Map<String,Object> createCtrlProp(SdwnController controller){
        Map<String,Object> prop = new HashMap<>();

        return prop;
    }
}
