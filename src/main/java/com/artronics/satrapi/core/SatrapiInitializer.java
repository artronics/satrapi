package com.artronics.satrapi.core;

import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.persistence.repositories.SdwnNetworkRepo;
import com.artronics.satrapi.persistence.seed.Seeder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.GenericWebApplicationContext;

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
    GenericWebApplicationContext webContext;

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

    //TODO move to ApplicationRunner see: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-spring-application.html#boot-features-command-line-runner
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (seedDb)
            seeder.seedNetwork(ip, connectionString);

        initSdwnNetwork();

        NetworkInitializer networkInitializer = new NetworkInitializer(webContext,sdwnNetwork);

        networkInitializer.createContexts();
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
