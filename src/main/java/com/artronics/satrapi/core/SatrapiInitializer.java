package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.persistence.repositories.SdwnNetworkRepo;
import com.artronics.satrapi.persistence.seed.Seeder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SatrapiInitializer implements
                                ApplicationListener<ContextRefreshedEvent>
{
    private final static Logger log = Logger.getLogger(SatrapiInitializer.class);

    private String ip;
    private String connectionString;

    private boolean seedDb = false;

    private SdwnNetwork sdwnNetwork;

    @Autowired
    private Environment env;

    @Autowired
    private SdwnNetworkRepo networkRepo;

    @Autowired
    private Seeder seeder;

    @PostConstruct
    public void initBean()
    {
        String seed = "com.artronics.satrapi.persistence.seed";
        if (!env.containsProperty(seed))
            seedDb = false;
        else {
            seedDb = Boolean.parseBoolean(env.getProperty(seed));
            connectionString = env.getProperty("com.artronics.satrapi.connection" +
                                                       ".connection_string");
        }

        ip = env.getProperty("com.artronics.satrapi.network.ip");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (seedDb)
            seeder.seedNetwork(ip, connectionString);

        initSdwnNetwork();

        AnnotationConfigEmbeddedWebApplicationContext cnx =(AnnotationConfigEmbeddedWebApplicationContext) event.getSource();

    }

    public void initSdwnNetwork()
    {
        log.info("Initializing SDWN Network with IP: " + ip);
        sdwnNetwork = networkRepo.findByIp(ip);

        if (sdwnNetwork == null) {
            log.debug("There is no SDWN-Network on data base. Create on.");

            sdwnNetwork = new SdwnNetwork(ip);
            networkRepo.save(sdwnNetwork);
        }
    }
}
