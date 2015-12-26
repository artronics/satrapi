package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnNetwork;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SdwnNetworkRepoTest extends RepoBaseTes
{

    @Test
    public void it(){

    }
    @Test
    public void test_findByIp()
    {
        SdwnNetwork persistedNet = networkRepo.findByIp(someIp);

        assertNotNull(persistedNet.getId());
        assertThat(persistedNet.getIp(), equalTo(someIp));
    }

}