package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.helper.CreateEntities;
import com.artronics.senator.controller.ControllerConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DevicePropertiesTest
{
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ControllerConfig.class);

    DeviceConnection device;

    MutablePropertySources sources;

    String propName = "Some DeviceConnection's properties";

    String id = "com.artronics.senator.controller.device.id";
    String startByte ="com.artronics.senator.controller.device.buffer.startByte";
    String stopByte = "com.artronics.senator.controller.device.buffer.stopByte";
    String conStr = "com.artronics.senator.controller.device.connection.connection_string";
    String timeout = "com.artronics.senator.controller.device.connection.timeout";

    @Before
    public void setUp() throws Exception
    {
        device = CreateEntities.createDevCon(78L,"some_connection_string");
        DeviceProperties.addDeviceProp(context,propName,device);
    }

    @Test
    public void it_should_add_a_propertySource_with_name_of_propName(){
        ConfigurableEnvironment env = context.getEnvironment();
        sources =env.getPropertySources();

        assertTrue(sources.contains(propName));
    }

    //put all require prop here
    @Test
    public void it_should_add_all_require_properties_based_on_passed_dev(){
        Environment env = context.getEnvironment();

        assertThat(Long.parseLong(env.getProperty(id)),equalTo(78L));
        assertThat(env.getProperty(conStr),equalTo("some_connection_string"));

        //these are prop that are not part of model but inside properties file: device-defaults.properties
        assertThat(Integer.parseInt(env.getProperty(timeout)),equalTo(2000));
        assertThat(Integer.parseInt(env.getProperty(startByte)),equalTo(122));
        assertThat(Integer.parseInt(env.getProperty(stopByte)),equalTo(126));
    }
}