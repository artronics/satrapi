package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import org.apache.log4j.Logger;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class NetworkInitializer
{
    private final static Logger log = Logger.getLogger(NetworkInitializer.class);

    private final AnnotationConfigEmbeddedWebApplicationContext webContext;
    private final SdwnNetwork sdwnNetwork;

    private AnnotationConfigApplicationContext networkContext;

    private final Map<Long,AnnotationConfigApplicationContext>
            controllerContexts = new HashMap<>();

    public NetworkInitializer(
            AnnotationConfigEmbeddedWebApplicationContext webContext,
            SdwnNetwork sdwnNetwork)
    {
        log.debug("Constructing Network Context.");
        this.webContext = webContext;
        this.sdwnNetwork = sdwnNetwork;
    }

    public void createContexts(){
        networkContext = new AnnotationConfigApplicationContext();

        for (SdwnController ctrl: sdwnNetwork.getControllers()){
            AnnotationConfigApplicationContext ctrlContext=
                    new AnnotationConfigApplicationContext();
            controllerContexts.put(ctrl.getId(),ctrlContext);
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
