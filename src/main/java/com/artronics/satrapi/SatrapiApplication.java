package com.artronics.satrapi;

import com.artronics.satrapi.persistence.PersistenceConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {
        "com.artronics.satrapi.persistence",
        "com.artronics.satrapi.core"
})
@EnableWebMvc
public class SatrapiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .sources(SatrapiApplication.class,PersistenceConfig.class)
                .run(args);
    }
}
