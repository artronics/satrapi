package com.artronics.satrapi;

import com.artronics.satrapi.persistence.PersistenceConfig;
import com.artronics.satrapi.persistence.repositories.jpa.SdwnNetworkRepoImpl;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.*;

@Configuration
//exclude all configurations
@ComponentScan(basePackages = { "com.artronics.satrapi" }, excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
//Add persistence config
@Import(PersistenceConfig.class)
@PropertySource({
        "classpath:application.properties",
        "classpath:application-test.properties"})
public class BeanConfigTest
{
    private final static Logger log = Logger.getLogger(BeanConfigTest.class);

    @Bean
    SdwnNetworkRepoImpl getNetRepo(){
        return new SdwnNetworkRepoImpl();
    }
}
