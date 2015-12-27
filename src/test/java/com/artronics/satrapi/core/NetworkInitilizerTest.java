package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.helper.CreateEntities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;

import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class NetworkInitilizerTest
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