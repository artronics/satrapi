package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.helper.CreateEntities;
import com.artronics.senator.controller.ControllerConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ControllerPropertiesTest
{
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ControllerConfig.class);

    SdwnController controller;

    MutablePropertySources sources;

    String propName = "Some controller's properties";

    String id = "com.artronics.senator.controller.id";
    String sinkAdd = "com.artronics.senator.controller.sink_address";

    @Before
    public void setUp() throws Exception
    {
        controller = CreateEntities.createCtrl(23L,65L);
        ControllerProperties.addCtrlProp(context,propName,controller);
    }

    @Test
    public void it_should_add_a_propertySource_with_name_of_propName(){
        ConfigurableEnvironment env = context.getEnvironment();
        sources =env.getPropertySources();

        assertTrue(sources.contains(propName));
    }

    //put all require prop here
    @Test
    public void it_should_add_all_require_properties_based_on_passed_ctrl(){
        Environment env = context.getEnvironment();

        assertThat(Long.parseLong(env.getProperty(id)),equalTo(23L));
        assertThat(Long.parseLong(env.getProperty(sinkAdd)),equalTo(65L));
    }
}