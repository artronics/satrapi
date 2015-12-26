package com.artronics.satrapi;

import com.artronics.satrapi.persistence.PersistenceConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SatrapiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .sources(SatrapiApplication.class,PersistenceConfig.class)
                .run(args);
    }
}
