package com.artronics.satrapi.persistence.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfigTest.class,
//        SatrapiApplication.class
})
@ActiveProfiles("test")
public class SdwnNetworkRepoTest
{
    @Autowired
    SdwnNetworkRepo networkRepo;

    @Test
    public void it(){

    }

}