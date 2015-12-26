package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.persistence.PersistenceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(PersistenceConfig.class)
@PropertySource({
//        "classpath:application-test.properties",
        "classpath:application-dev.properties"
})
public class RepositoryConfigTest
{
}
