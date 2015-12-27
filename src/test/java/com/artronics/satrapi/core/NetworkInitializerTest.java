package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.helper.CreateEntities;
import com.artronics.senator.controller.ControllerConfig;
import com.artronics.senator.network.SdwnNetworkConfig;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class NetworkInitializerTest
{
    private String someIp = "127.35.164.233";
    private int numOfCtrls = 5;

    NetworkInitializer networkInitializer;

    @Mock
    AnnotationConfigEmbeddedWebApplicationContext webContext;

    SdwnNetwork sdwnNetwork;

    @Before
    public void setUp() throws Exception
    {
        sdwnNetwork = createSdwnNetwork(someIp, numOfCtrls);
        MockitoAnnotations.initMocks(this);
        networkInitializer = new NetworkInitializer(webContext, sdwnNetwork);
    }

    @Test
    public void it_should_create_a_container_for_whole_network()
    {
        networkInitializer.createContexts();

        assertNotNull(networkInitializer.getNetworkContext());
    }

    @Test
    public void it_should_have_config(){
        networkInitializer.createContexts();

        SdwnNetworkConfig config =networkInitializer.getNetworkContext().getBean(SdwnNetworkConfig.class);

        assertNotNull(config);
    }

    @Test
    public void it_should_create_numOfCtrls_controllerContainer()
    {
        networkInitializer.createContexts();

        assertThat(networkInitializer.getControllerContexts().size(), equalTo(numOfCtrls));
    }

    @Test
    public void controllers_context_map_should_has_a_key_equal_to_ctrl_id(){
        networkInitializer.createContexts();

        Set<Long> ctrlContainersKeySet = networkInitializer.getControllerContexts().keySet();
        for (int i = 0; i < numOfCtrls; i++) {
            assertTrue(ctrlContainersKeySet.contains(Integer.toUnsignedLong(i)));
        }
    }

    @Test
    public void for_all_ctrl_there_is_a_config(){
        networkInitializer.createContexts();

        Map<Long, AnnotationConfigApplicationContext> controllerContexts = networkInitializer
                .getControllerContexts();

        for (int i = 0; i < numOfCtrls; i++) {
            AnnotationConfigApplicationContext ctrlCntx
                    = controllerContexts.get(Integer.toUnsignedLong(i));

            ControllerConfig ctrlConfig = ctrlCntx.getBean(ControllerConfig.class);
            assertNotNull(ctrlConfig);
        }
    }

    @Test
    public void for_all_controllers_we_should_set_env_properties(){
        networkInitializer.createContexts();

        Map<Long, AnnotationConfigApplicationContext> controllerContexts = networkInitializer
                .getControllerContexts();

        for (int i = 0; i < numOfCtrls; i++) {
            AnnotationConfigApplicationContext ctrlCntx
                    = controllerContexts.get(Integer.toUnsignedLong(i));

            ConfigurableEnvironment env = ctrlCntx.getEnvironment();
            MutablePropertySources sources =env.getPropertySources();
            String ctrlPropName = "Controller Properties for id: "+i;
            assertTrue(sources.contains(ctrlPropName));
        }
    }

    @Test
    public void for_each_dev_associated_with_each_ctrl_it_should_add_prop(){
        networkInitializer.createContexts();

        Map<Long, AnnotationConfigApplicationContext> controllerContexts = networkInitializer
                .getControllerContexts();

        for (int i = 0; i < numOfCtrls; i++) {
            AnnotationConfigApplicationContext ctrlCntx
                    = controllerContexts.get(Integer.toUnsignedLong(i));

            ConfigurableEnvironment env = ctrlCntx.getEnvironment();
            MutablePropertySources sources =env.getPropertySources();
            String ctrlPropName = "DeviceConnection Properties for id: "+i;
            assertTrue(sources.contains(ctrlPropName));
        }
    }

    @Test
    public void networkContainer_is_the_parent_of_all_controllers_container(){
        networkInitializer.createContexts();

        AnnotationConfigApplicationContext netContex = networkInitializer.getNetworkContext();

        Map<Long, AnnotationConfigApplicationContext> controllerContexts = networkInitializer
                .getControllerContexts();

        for (int i = 0; i < numOfCtrls; i++) {
            AnnotationConfigApplicationContext ctrlCntx
                    = controllerContexts.get(Integer.toUnsignedLong(i));

            assertThat(ctrlCntx.getParent(),equalTo(netContex));
        }
    }

    @Test
    public void webContainer_is_the_parent_of_networkContainer(){
        networkInitializer.createContexts();

        AnnotationConfigApplicationContext netContainer = networkInitializer.getNetworkContext();

        assertThat(netContainer.getParent(),equalTo(webContext));
    }

    private static SdwnNetwork createSdwnNetwork(String ip, int numCtrl)
    {
        SdwnNetwork net = CreateEntities.createNet(ip);
        net.setId(123L);
        for (int i = 0; i < numCtrl; i++) {
            SdwnController ctrl = new SdwnController();
            ctrl.setSdwnNetwork(net);
            ctrl.setSinkAddress(0L);
            ctrl.setDescription("Some ctrl with id: " + i);
            ctrl.setId(Integer.toUnsignedLong(i));

            DeviceConnection dev = new DeviceConnection("some connection string");
            dev.setId(Integer.toUnsignedLong(i));
            dev.setSdwnController(ctrl);

            ctrl.setDeviceConnection(dev);

            net.addSdwnController(ctrl);
        }

        return net;
    }
}