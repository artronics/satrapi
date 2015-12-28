package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.senator.controller.ControllerConfig;
import com.artronics.senator.network.SdwnNetworkConfig;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class NetworkInitializer
{
    private final static Logger log = Logger.getLogger(NetworkInitializer.class);

    private final SdwnNetwork sdwnNetwork;

    private final GenericWebApplicationContext webContext;

    private AnnotationConfigApplicationContext networkContext;

    private final Map<Long,AnnotationConfigApplicationContext>
            controllerContexts = new HashMap<>();

    public NetworkInitializer(
            GenericWebApplicationContext webContext,
            SdwnNetwork sdwnNetwork)
    {
        log.debug("Constructing Network Context.");
        this.webContext = webContext;
        this.sdwnNetwork = sdwnNetwork;
    }

    public void createContexts(){
        networkContext = new AnnotationConfigApplicationContext(SdwnNetworkConfig.class);
        networkContext.setParent(webContext);

        for (SdwnController ctrl: sdwnNetwork.getControllers()){
            AnnotationConfigApplicationContext ctrlContext=
                    new AnnotationConfigApplicationContext(ControllerConfig.class);

            //Set prop for controller
            String idStr=Long.toString(ctrl.getId());
            log.debug("Add properties for controller: "+idStr);
            String ctrlPropName = "Controller Properties for id: "+idStr;
            ControllerProperties.addCtrlProp(ctrlContext,ctrlPropName,ctrl);

            //Set prop for device associated with this controller
            DeviceConnection dev = ctrl.getDeviceConnection();
            String devIdStr = Long.toString(dev.getId());
            log.debug("Add properties for device: "+devIdStr);
            String devPropName = "DeviceConnection Properties for id: "+idStr;
            DeviceProperties.addDeviceProp(ctrlContext,devPropName,dev);

            controllerContexts.put(ctrl.getId(),ctrlContext);

            ctrlContext.setParent(networkContext);
        }
    }

    public AnnotationConfigApplicationContext getNetworkContext()
    {
        return networkContext;
    }

    public Map<Long, AnnotationConfigApplicationContext> getControllerContexts()
    {
        return controllerContexts;
    }
}
